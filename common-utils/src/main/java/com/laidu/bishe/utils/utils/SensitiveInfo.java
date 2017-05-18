package com.laidu.bishe.utils.utils;


import java.lang.annotation.*;

/**
 * Created by huangpin on 16/9/5.
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SensitiveInfo {

    public SensitiveInfoUtils.SensitiveType type() ;
}
