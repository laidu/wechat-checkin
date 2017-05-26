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
    @InsertProvider(type=SeqInfoSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Long insertReKey(SeqInfo record);
}
