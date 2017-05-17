package com.laidu.bishe.utils.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by laidu on 2017/5/16.
 */

@Slf4j
public class CsvUtilTest {
    @Test
    public void readObjestsList() throws Exception {

        String csv = "/Users/laidu/IdeaProjects/wechat-checkin/common-utils/src/test/resources/course.csv";

        CsvUtil.writeObjests2Csv(CsvUtil.readCsv2ObjestsList(CourseInfo.class,csv),"./text.csv");
    }

}