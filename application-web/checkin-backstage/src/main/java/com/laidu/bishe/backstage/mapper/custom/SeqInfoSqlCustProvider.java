package com.laidu.bishe.backstage.mapper.custom;

import com.laidu.bishe.backstage.mapper.SeqInfoSqlProvider;
import org.apache.ibatis.jdbc.SQL;

/**
 *
 * Created by laidu on 2017/5/21.
 */
public class SeqInfoSqlCustProvider extends SeqInfoSqlProvider {

    public String insertReKey(){

        SQL sql = new SQL();
        sql.INSERT_INTO("seq_info");

        sql.SELECT("");

        return sql.toString();
    }
}
