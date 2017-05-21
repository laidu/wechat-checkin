package com.laidu.bishe.backstage.mapper.custom;

import com.laidu.bishe.backstage.domain.SeqInfo;
import com.laidu.bishe.backstage.mapper.SeqInfoMapper;
import com.laidu.bishe.backstage.mapper.SeqInfoSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

/**
 *
 * Created by laidu on 2017/5/21.
 */
public interface SeqInfoCustMapper extends SeqInfoMapper{

    /**
     * 插入记录返回主键值
     * @param record
     * @return
     */
//    @Insert({
//            "insert into seq_info (teacher_id, ",
//            "course_id, start_time)",
//            "values (#{teacherId,jdbcType=BIGINT}, ",
//            " #{courseId,jdbcType=BIGINT}, #{startTime,jdbcType=TIMESTAMP})"
//    })
//    @SelectKey(statement = "nextval('seq_info_id_seq'::regclass)",
//            before = false,
//            keyProperty = "id",
//            resultType = Long.class)
    @InsertProvider(type=SeqInfoSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Long insertReKey(SeqInfo record);
}
