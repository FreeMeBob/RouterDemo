package com.example.my_annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Created by xinmei on 2018/1/30.
 */
@Documented
@Retention(CLASS)
@Target({METHOD,CONSTRUCTOR,TYPE})
public @interface AutoWire {
}
