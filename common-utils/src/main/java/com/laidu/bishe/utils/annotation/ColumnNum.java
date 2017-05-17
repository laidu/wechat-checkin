package com.laidu.bishe.utils.annotation;

import java.lang.annotation.*;

/**
 * Created by chenwen on 16/12/14.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface ColumnNum {
    int value();

    /**
     * 列表名
     * @return
     */
    String title() default "其他";
}
