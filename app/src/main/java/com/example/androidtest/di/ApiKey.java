package com.example.androidtest.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiKey {
    String value() default "c9856d0cb57c3f14bf75bdc6c063b8f3" ;
}
