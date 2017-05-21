package com.laidu.bishe.backstage.mapper.custom;

import com.laidu.bishe.backstage.MainApp;
import com.laidu.bishe.backstage.domain.SeqInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by laidu on 2017/5/21.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(MainApp.class)
public class SeqInfoCustMapperTest {

    @Autowired(required = false)
    private SeqInfoCustMapper seqInfoCustMapper;

    @Test
    public void insertReKey() throws Exception {

        SeqInfo seqInfo = new SeqInfo();
        seqInfo.setCourseId(1233412L);
        seqInfo.setStartTime(new Date());
        seqInfo.setTeacherId(123123213L);

        seqInfoCustMapper.insertReKey(seqInfo);

        log.info("seqInfo = {}",seqInfo);

    }

}