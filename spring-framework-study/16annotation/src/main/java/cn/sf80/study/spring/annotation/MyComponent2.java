package cn.sf80.study.spring.annotation;

import java.lang.annotation.*;

/**
 * {@link MyComponent} "派生"注解
 *
 * @author wangcongjun
 * @since
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent
public @interface MyComponent2 {
}
