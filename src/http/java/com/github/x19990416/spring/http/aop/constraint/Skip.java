package com.github.x19990416.spring.http.aop.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
/**
 * 跳过{@code SessionCheck}
 * @author starseeker.limin@gmail.com(X-Forever)
 *
 */
public @interface Skip {

}