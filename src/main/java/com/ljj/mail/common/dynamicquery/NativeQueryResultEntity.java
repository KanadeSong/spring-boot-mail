package com.ljj.mail.common.dynamicquery;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * NativeQueryResultEntity
 * </p>
 *
 * @author LeeJack
 * @date Created in 2020/4/29 0:34
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NativeQueryResultEntity {
}
