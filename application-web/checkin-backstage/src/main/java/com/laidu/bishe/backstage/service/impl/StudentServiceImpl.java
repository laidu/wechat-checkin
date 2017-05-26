package com.laidu.bishe.backstage.service.impl;

import com.laidu.bishe.backstage.config.RedisPropertis;
import com.laidu.bishe.backstage.domain.CheckinCalculateResult;
import com.laidu.bishe.backstage.domain.CheckinDetailInfo;
import com.laidu.bishe.backstage.domain.StudentInfo;
import com.laidu.bishe.backstage.enums.CheckInResultEnum;
import com.laidu.bishe.backstage.enums.FeatureTypeEnum;
import com.laidu.bishe.backstage.exception.BackstageException;
import com.laidu.bishe.backstage.mapper.WechatUserInfoMapper;
import com.laidu.bishe.backstage.mapper.custom.CheckinCalculateResultCustMapper;
import com.laidu.bishe.backstage.mapper.custom.CheckinDetailCustInfoMapper;
import com.laidu.bishe.backstage.mapper.custom.StudentInfoCustMapper;
import com.laidu.bishe.backstage.model.*;
import com.laidu.bishe.backstage.service.AdminService;
import com.laidu.bishe.backstage.service.QueueSender;
import com.laidu.bishe.backstage.service.StudentService;
import com.laidu.bishe.backstage.utils.RandomUtil;
import com.laidu.bishe.common.web.exception.WebException;
import com.laidu.bishe.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by laidu on 2017/5/17.
 */
@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    @Lazy
    @Autowired(required = false)
    private StudentInfoCustMapper studentInfoCustMapper;

    @Lazy
    @Autowired(required = false)
    private WechatUserInfoMapper userInfoMapper;

    @Lazy
    @Autowired(required = false)
    private CheckinDetailCustInfoMapper checkinDetailCustInfoMapper;

    @Lazy
    @Autowired(required = false)
    private CheckinCalculateResultCustMapper checkinCalculateResultCustMapper;


    @Autowired
    private AdminService adminService;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisPropertis redisPropertis;

    @Autowired
    private QueueSender queueSender;


    @Override
//    @Cacheable(value = "usercache",keyGenerator = "wiselyKeyGenerator")
    public CheckinContentInfo requestCheckin(String wechatId) {

        StudentInfo studentInfo = studentInfoCustMapper.selectByWechatId(wechatId);

        RMap<Long,CheckinQueueInfo> queueInfos = adminService.getCheckinQueue();

        for (CheckinQueueInfo checkinQueueInfo : queueInfos.values()){

            if(checkinQueueInfo.getClassIds().contains(studentInfo.getClassId())){

                RMap<Long,CheckinContentInfo> checkinTypeInfoRMap = redissonClient.getMap(checkinQueueInfo.getCourseName());

                if (checkinTypeInfoRMap.containsKey(studentInfo.getStudentId())){

                    CheckinContentInfo checkinContentInfo =checkinTypeInfoRMap.get(studentInfo.getStudentId());

                    return checkinContentInfo;

                }else {

                    //如果存在当前学生班级的考勤对列则生成一条该学生的考勤信息
                    CheckinDetailInfo checkinDetailInfo = new CheckinDetailInfo();

                    FeatureTypeEnum featureTypeEnum = RandomUtil.randomFeatureType();

                    checkinDetailInfo.setCheckinTime(new Date());
                    checkinDetailInfo.setCourseId(checkinQueueInfo.getCourseId());
                    checkinDetailInfo.setCheckinResult(CheckInResultEnum.ABSENCE.getCode());
                    checkinDetailInfo.setIsSucc(false);
                    checkinDetailInfo.setStudentId(studentInfo.getStudentId());
                    checkinDetailInfo.setSeqId(checkinQueueInfo.getSeqId());
                    checkinDetailInfo.setCheckinType(featureTypeEnum.getName());

                    checkinDetailCustInfoMapper.insertSelective(checkinDetailInfo);

                    CheckinContentInfo checkinContentInfo = null;

                    if (featureTypeEnum.equals(FeatureTypeEnum.FACE)){

                        checkinContentInfo = CheckinContentInfo.builder()
                                .typeEnum(featureTypeEnum)
                                .teaWechatId(checkinQueueInfo.getTeaWechatId())
                                .action(RandomUtil.randomFaceAction().getName())
                                .build();

                    }else  if (featureTypeEnum.equals(FeatureTypeEnum.VOICE)){

                        checkinContentInfo =  CheckinContentInfo.builder()
                                .typeEnum(featureTypeEnum)
                                .teaWechatId(checkinQueueInfo.getTeaWechatId())
                                .action(RandomUtil.randomVoiceText().getName())
                                .build();
                    }else if (featureTypeEnum.equals(FeatureTypeEnum.LEAVE)){

                        checkinContentInfo =  CheckinContentInfo.builder()
                                .typeEnum(featureTypeEnum)
                                .teaWechatId(checkinQueueInfo.getTeaWechatId())
                                .build();

                    }
                    checkinTypeInfoRMap.put(studentInfo.getStudentId(), checkinContentInfo);
                    return checkinContentInfo;
                }
            }
        }

        return null;
    }

    @Override
    public ResultMessage stuCheckInByVoice(String stuWechatID, File voiceFile) {

        StudentInfo studentInfo = studentInfoCustMapper.selectByWechatId(stuWechatID);

        RecognitionInfo recognitionInfo = RecognitionInfo.builder()
                .createTime(new Date())
                .wechatId(stuWechatID)
                .filePath(voiceFile.getPath())
                .typeEnum(FeatureTypeEnum.VOICE)
                .studentId(studentInfo.getStudentId())
                .build();

        queueSender.sendRecognitionInfo(recognitionInfo);
        log.info("成功发送语音识别信息Q ：{}", JsonUtil.toJson(recognitionInfo));

        waitRecoResult(stuWechatID);

        return null;
    }

    @Override
    public ResultMessage stuCheckInByFace(String stuWechatID, File pictureFile) {

        StudentInfo studentInfo = studentInfoCustMapper.selectByWechatId(stuWechatID);

        RecognitionInfo recognitionInfo = RecognitionInfo.builder()
                .createTime(new Date())
                .wechatId(stuWechatID)
                .filePath(pictureFile.getPath())
                .typeEnum(FeatureTypeEnum.FACE)
                .studentId(studentInfo.getStudentId())
                .build();

        queueSender.sendRecognitionInfo(recognitionInfo);
        log.info("成功发送人脸识别消息Q ：{}", JsonUtil.toJson(recognitionInfo));

        waitRecoResult(stuWechatID);

        return null;
    }

    @Override
    public ResultMessage stuLeave(String stuWechatID, File pictureFile) {

        StudentInfo studentInfo = studentInfoCustMapper.selectByWechatId(stuWechatID);


        waitRecoResult(stuWechatID);

        return null;
    }

    @Override
    public boolean bingPicMd5Sum(String wechatId, String picMd5Sum) {


        StudentInfo studentInfo = studentInfoCustMapper.selectByWechatId(wechatId);

        RMap<Long,CheckinQueueInfo> queueInfos = adminService.getCheckinQueue();

        for (CheckinQueueInfo checkinQueueInfo : queueInfos.values()){

            if(checkinQueueInfo.getClassIds().contains(studentInfo.getClassId())){

                RMap<Long,CheckinContentInfo> checkinTypeInfoRMap = redissonClient.getMap(checkinQueueInfo.getCourseName());

                if (checkinTypeInfoRMap.containsKey(studentInfo.getStudentId())){

                    CheckinContentInfo checkinContentInfo =checkinTypeInfoRMap.get(studentInfo.getStudentId());

                    checkinContentInfo.setPicMd5Sum(picMd5Sum);

                    checkinTypeInfoRMap.replace(studentInfo.getStudentId(),checkinContentInfo);

                    return true;
                }
            }
        }

        return false;
    }


    @Override
    public CheckinContentInfo stuRequestLeave(String stuWechatID) {

        StudentInfo studentInfo = studentInfoCustMapper.selectByWechatId(stuWechatID);

        RMap<Long,CheckinQueueInfo> queueInfos = adminService.getCheckinQueue();

        for (CheckinQueueInfo checkinQueueInfo : queueInfos.values()){

            if(checkinQueueInfo.getClassIds().contains(studentInfo.getClassId())){

                RMap<Long,CheckinContentInfo> checkinTypeInfoRMap = redissonClient.getMap(checkinQueueInfo.getCourseName());

                if (checkinTypeInfoRMap.containsKey(studentInfo.getStudentId())){

                    CheckinContentInfo checkinContentInfo =checkinTypeInfoRMap.get(studentInfo.getStudentId());

                    return checkinContentInfo;

                }else {

                    //如果存在当前学生班级的考勤对列则生成一条该学生的考勤信息
                    CheckinDetailInfo checkinDetailInfo = new CheckinDetailInfo();

                    checkinDetailInfo.setCheckinTime(new Date());
                    checkinDetailInfo.setCourseId(checkinQueueInfo.getCourseId());
                    checkinDetailInfo.setCheckinResult(CheckInResultEnum.LEAVE.getCode());
                    checkinDetailInfo.setIsSucc(false);
                    checkinDetailInfo.setStudentId(studentInfo.getStudentId());
                    checkinDetailInfo.setSeqId(checkinQueueInfo.getSeqId());
                    checkinDetailInfo.setCheckinType(CheckInResultEnum.LEAVE.getMessage());

                    checkinDetailCustInfoMapper.insertSelective(checkinDetailInfo);

                    CheckinContentInfo checkinContentInfo = null;

                    checkinContentInfo = CheckinContentInfo.builder()
                            .typeEnum(FeatureTypeEnum.LEAVE)
                            .teaWechatId(checkinQueueInfo.getTeaWechatId())
                            .action(RandomUtil.randomFaceAction().getName())
                            .build();

                    checkinTypeInfoRMap.put(studentInfo.getStudentId(), checkinContentInfo);

                    return checkinContentInfo;
                }
            }
        }

        return null;

    }

    @Override
    public boolean bindingWechatId(Long stuId, String wechatId) {

        StudentInfo studentInfo = studentInfoCustMapper.selectByPrimaryKey(stuId);

        if (studentInfo==null){
            throw new WebException(BackstageException.STUDENT_INFO_NOT_EXIST);
        }

        studentInfo.setWechatId(wechatId);
        studentInfo.setRegisterTime(new Date());

        try {
            // TODO: 2017/5/17 添加微信号是否在已关注表中判断
            studentInfoCustMapper.updateByPrimaryKeySelective(studentInfo);
        }catch (Exception e){
            log.error("更新绑定学生微信号失败! {}",studentInfo);
            throw new WebException(BackstageException.UPDATE_STUDENT_INFO_FAILD);
        }

        return true;
    }

    @Override
    public boolean alterWechatId(Long stuId, String newWechatId) {

        StudentInfo studentInfo = studentInfoCustMapper.selectByPrimaryKey(stuId);

        if (studentInfo.getWechatId()==null){
            log.info("该学生尚未绑定微信号,请先绑定微信号{}",studentInfo);
            return false;
        }

        return bindingWechatId(stuId,newWechatId);
    }

    @Override
    public List<StudentInfo> getStudentsBySeqId(Long seqId, CheckInResultEnum result) {

        List<CheckinDetailInfo> checkinDetailInfos = checkinDetailCustInfoMapper.selectStudentBySeqIdAndStatus(seqId,result.getMessage());

        List<StudentInfo> studentInfos = new ArrayList<>();

        if (checkinDetailInfos!=null&&checkinDetailInfos.size()==0){
            checkinDetailInfos.forEach(checkinDetailInfo -> {

                StudentInfo studentInfo = studentInfoCustMapper.selectByPrimaryKey(checkinDetailInfo.getStudentId());

                if (studentInfo!=null){
                    studentInfos.add(studentInfo);
                }
            });
        }
        return studentInfos;
    }

    @Override
    @Cacheable(value = "get_my_info_ccache",keyGenerator = "wiselyKeyGenerator")
    public StudentDetialInfo getMyInfo(String wechatId) {

        StudentInfo studentInfo = studentInfoCustMapper.selectByWechatId(wechatId);

        if (studentInfo==null){
            throw new WebException(BackstageException.STUDENT_INFO_NOT_EXIST);
        }

        return StudentDetialInfo.builder()
                .studentId(studentInfo.getStudentId())
                .studentName(studentInfo.getStudentName())
                .classId(studentInfo.getClassId())
                .registerTime(studentInfo.getRegisterTime())
                .build();
    }

    @Override
//    @Cacheable(value = "mycheckin_re_ccache",keyGenerator = "wiselyKeyGenerator")
    public ResultMessage getMycheckinRec(String wechatId) {

        String message = "";

        StudentInfo studentInfo = studentInfoCustMapper.selectByWechatId(wechatId);

        if (studentInfo!=null){

            List<CheckinCalculateResult> checkinCalculateResults =
                    checkinCalculateResultCustMapper.selectByStudentId(studentInfo.getStudentId());

            message = adminService.parseCheckinResult(checkinCalculateResults);

        }

        return ResultMessage.builder()
                .message(message)
                .build();
    }

    private void waitRecoResult(String wechatId){

        StudentInfo studentInfo = studentInfoCustMapper.selectByWechatId(wechatId);

        RMap<Long,CheckinQueueInfo> queueInfos = adminService.getCheckinQueue();



        for (CheckinQueueInfo checkinQueueInfo : queueInfos.values()){

            if(checkinQueueInfo.getClassIds().contains(studentInfo.getClassId())){

                RMap<Long,CheckinContentInfo> checkinContentInfoRMap = redissonClient.getMap(checkinQueueInfo.getCourseName());

                CheckinContentInfo checkinContentInfo = checkinContentInfoRMap.get(studentInfo.getStudentId());

                checkinContentInfo.setWait(true);

                checkinContentInfoRMap.replace(studentInfo.getStudentId(),checkinContentInfo);
            }
        }


    }

    private RMap<Long,CheckinContentInfo> getCheckinContentInfoRMap(String wechatId) {

        StudentInfo studentInfo = studentInfoCustMapper.selectByWechatId(wechatId);

        RMap<Long,CheckinQueueInfo> queueInfos = adminService.getCheckinQueue();



        for (CheckinQueueInfo checkinQueueInfo : queueInfos.values()){

            if(checkinQueueInfo.getClassIds().contains(studentInfo.getClassId())){

                RMap<Long,CheckinContentInfo> checkinContentInfoRMap = redissonClient.getMap(checkinQueueInfo.getCourseName());

                return checkinContentInfoRMap;
            }
        }

        return null;
    }



}
