package com.laidu.bishe.wechat.controller;

import com.laidu.bishe.backstage.dto.StudentInfoDto;
import com.laidu.bishe.backstage.dto.TeacherInfoDto;
import com.laidu.bishe.backstage.service.StudentService;
import com.laidu.bishe.backstage.service.TeacherService;
import com.laidu.bishe.common.web.result.JSONResult;
import com.laidu.bishe.wechat.enums.UserTypeEnum;
import com.laidu.bishe.wechat.enums.WechatUserTagEnum;
import com.laidu.bishe.wechat.service.WechatTagService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 微信公众号页面
 * <p>
 * Created by laidu on 2017/5/17.
 */
@Slf4j
@Controller
@CrossOrigin(maxAge = 3600)
@RequestMapping("/wechat")
public class WechatPageController {


    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private WechatTagService wechatTagService;

    /**
     * 注意跨域问题
     *
     * @param userType
     * @return
     */
    @CrossOrigin("https://open.weixin.qq.com")
    @GetMapping("/auth2/{userType}")
    public String auth2(@PathVariable(value = "userType") String userType) {

        String url = wxMpService.
                oauth2buildAuthorizationUrl("http://laidu823.natapp4.cc/wechat/register",
                        "snsapi_userinfo", userType);

        return "redirect:" + url;
    }

    @GetMapping("/register")
    public String register(ModelMap map,
                           @RequestParam String code,
                           @RequestParam String state) {

        WxMpUser wxMpUser = null;
        try {
            wxMpUser = wxMpService.oauth2getUserInfo(wxMpService.oauth2getAccessToken(code), "zh_CN");
            log.info("成功获取到用户信息: {}",wxMpUser);
        } catch (WxErrorException e) {
            log.info("获取用户信息异常！code={}", code);
        }

        if (wxMpUser == null && !state.equals(UserTypeEnum.STUDENT.getName())
                && !state.equals(UserTypeEnum.TEACHER.getName())) {
            return "error";
        }

        map.put("wechatId", wxMpUser.getOpenId());

        return state.equals(UserTypeEnum.STUDENT.getName()) ? UserTypeEnum.STUDENT.getName() : UserTypeEnum.TEACHER.getName();

    }

    @PostMapping("/register/teacher")
    public @ResponseBody JSONResult teacherRegister(@Valid @ModelAttribute TeacherInfoDto infoDto) {

        log.info("教师注册信息 infoDto, {}",infoDto);
        //绑定教师微信号
        teacherService.bindingWechatId(infoDto.getTeacherId(),infoDto.getWechatId());
        wechatTagService.addUserTag(WechatUserTagEnum.TEACHER,infoDto.getWechatId());

        return JSONResult.ok();
    }

    @PostMapping("/register/student")
    public @ResponseBody JSONResult studentRegister(@Valid @ModelAttribute StudentInfoDto infoDto) {

        log.info("学生注册信息 infoDto, {}",infoDto);
        //绑定教师微信号
        studentService.bindingWechatId(infoDto.getStudentId(),infoDto.getWechatId());
        wechatTagService.addUserTag(WechatUserTagEnum.STUDENT,infoDto.getWechatId());

        return JSONResult.ok();
    }
}
