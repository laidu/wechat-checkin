package com.laidu.bishe.utils.utils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by chenwen on 17/1/12.
 * 业务工具
 */
@Slf4j
public class BusinessUtil {
    public final static IdGenerator PRODUCT_TEMPLATE_ID = new IdGenerator("PT",10);

    public final static IdGenerator PRODUCT_TEMPLATE_TYPE_ID = new IdGenerator("PTT",10);

    public final static IdGenerator PRODUCT_ID = new IdGenerator("P",10);

    public final static IdGenerator APPLY_ID = new IdGenerator("O",32);

    public final static IdGenerator CUSTOMER_ID = new IdGenerator("C",32);

    public final static IdGenerator REPORT_ID = new IdGenerator("R",32);

    public final static IdGenerator INSURANCE_ID = new IdGenerator("I",32);

    @AllArgsConstructor
    public static class IdGenerator{
        /**
         * 前缀
         */
        protected String prefix;

        /**
         * 长度
         */
        protected int length;

        /**
         * 生成方式
         */
        public String gen(){
            return String.format("%s%s%s",prefix, System.currentTimeMillis() % 900 + 100, RandomUtil.getRandomLong(length - prefix.length() - 2));
        }

        /**
         * 生成方式
         */
        public String genBySource(Long source){
            return String.format("%s%s%s",prefix, RandomUtil.generateRandom(length - 17 - String.valueOf(source).length()), source);
        }

        /**
         * 校验方式
         */
        public boolean validate(String id){
            return FieldUtil.regex(id,"^" + prefix + "[0-9]" + "{" + (length - prefix.length()) + "}$");
        }
    }


    public static void main(String[] args) {
        log.info(BusinessUtil.PRODUCT_TEMPLATE_TYPE_ID.gen());
        log.info(BusinessUtil.PRODUCT_TEMPLATE_ID.gen());
        log.info(BusinessUtil.PRODUCT_ID.gen());
        log.info(BusinessUtil.APPLY_ID.genBySource(100000L));
        log.info(BusinessUtil.REPORT_ID.genBySource(100000L));
        log.info(BusinessUtil.REPORT_ID.genBySource(0L));
    }


}
