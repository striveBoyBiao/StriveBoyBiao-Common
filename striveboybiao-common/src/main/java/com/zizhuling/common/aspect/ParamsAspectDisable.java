package com.zizhuling.common.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 描述：不需要参数校验的方法添加此注解
 * </p>
 *
 * @author lishang Created on 2019/12/2310:38
 * @version 1.0
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamsAspectDisable {


}
