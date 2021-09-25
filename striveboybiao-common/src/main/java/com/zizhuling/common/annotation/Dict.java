package com.zizhuling.common.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Retention ：用来说明该注解类的生命周期。它有以下三个参数：
 * RetentionPolicy.SOURCE : 注解只保留在源文件中
 * RetentionPolicy.CLASS : 注解保留在class文件中，在加载到JVM虚拟机时丢弃
 * RetentionPolicy.RUNTIME : 注解保留在程序运行期间，此时可以通过反射获得定义在某个类上的所有注解
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DictValidator.class)  //用于限制的注解，验证类为MoneyValidator
public @interface Dict {
    String message() default "${validatedValue}输入值与数据字典不匹配.";

    String value() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
/**
 * @Target : 用来说明该注解可以被声明在那些元素之前。
 * ElementType.TYPE：说明该注解只能被声明在一个类前。
 * ElementType.FIELD：说明该注解只能被声明在一个类的字段前。
 * ElementType.METHOD：说明该注解只能被声明在一个类的方法前。
 * ElementType.PARAMETER：说明该注解只能被声明在一个方法参数前。
 * ElementType.CONSTRUCTOR：说明该注解只能声明在一个类的构造方法前。
 * ElementType.LOCAL_VARIABLE：说明该注解只能声明在一个局部变量前。
 * ElementType.ANNOTATION_TYPE：说明该注解只能声明在一个注解类型前。
 * ElementType.PACKAGE：说明该注解只能声明在一个包名前。
 * @Constraint来限定自定义注解的方法 这里写的类是自定义的类
 **/