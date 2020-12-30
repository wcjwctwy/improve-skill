package cn.sf80.study.spring.application.context.lifecycle;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.Lifecycle;
import org.springframework.context.support.GenericApplicationContext;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.rootBeanDefinition;

/**
 * 自定义 {@link Lifecycle} Bean 示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see Lifecycle
 * @since
 */
public class LifecycleDemo {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        // 注解 MyLifecycle 成为一个 Spring Bean
        context.registerBeanDefinition("myLifecycle", rootBeanDefinition(MyLifecycle.class).getBeanDefinition());

        // 刷新 Spring 应用上下文
        context.refresh();

        // 启动 Spring 应用上下文
        context.start();

        // 停止 Spring 应用上下文
        context.stop();

        // 关闭 Spring 应用
        context.close();
    }
}
