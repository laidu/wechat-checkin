package com.laidu.bishe.wechat.mapper;

import com.laidu.bishe.wechat.domain.SeqInfo;
import com.laidu.bishe.wechat.domain.SeqInfoKey;

public interface SeqInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seqInfo
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    int deleteByPrimaryKey(SeqInfoKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seqInfo
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    int insert(SeqInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seqInfo
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    int insertSelective(SeqInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seqInfo
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    SeqInfo selectByPrimaryKey(SeqInfoKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seqInfo
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    int updateByPrimaryKeySelective(SeqInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seqInfo
     *
     * @mbg.generated Mon May 08 16:09:59 CST 2017
     */
    int updateByPrimaryKey(SeqInfo record);
}