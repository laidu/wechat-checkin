package com.laidu.bishe.utils.annotation;

import java.lang.annotation.*;

/**
 * Created by chenwen on 16/11/18.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JSONGroup {
    String[] value() default {"default"};
}
