package com.laidu.bishe.backstage.mapper;

import com.laidu.bishe.backstage.domain.CheckinDetailInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;

public interface CheckinDetailInfoMapper {
    @Insert({
        "insert into checkin_detail_info (stu_id, checkin_time, ",
        "proof_path, checkin_type, ",
        "is_succ, checkin_result, ",
        "course_id, seq_id, id)",
        "values (#{stuId,jdbcType=BIGINT}, #{checkinTime,jdbcType=TIMESTAMP}, ",
        "#{proofPath,jdbcType=VARCHAR}, #{checkinType,jdbcType=SMALLINT}, ",
        "#{isSucc,jdbcType=BIT}, #{checkinResult,jdbcType=SMALLINT}, ",
        "#{courseId,jdbcType=BIGINT}, #{seqId,jdbcType=BIGINT}, #{id,jdbcType=BIGINT})"
    })
    int insert(CheckinDetailInfo record);

    @InsertProvider(type=CheckinDetailInfoSqlProvider.class, method="insertSelective")
    int insertSelective(CheckinDetailInfo record);
}