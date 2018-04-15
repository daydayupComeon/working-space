package com.song.annotation;

import java.lang.annotation.*;

/**
 * Created by admin on 2018/4/1.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyLog {
    String requestUrl();
}
