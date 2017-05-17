package com.laidu.bishe.utils.annotation;

import java.lang.annotation.*;

/**
 * Created by chenwen on 16/12/14.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface ColumnRowValue {
    /**
     * 行,从1开始
     * @return
     */
    int row();

    /**
     * 列,从1开始
     * @return
     */
    int col();
}
