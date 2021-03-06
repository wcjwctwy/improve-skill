package cn.sf80.study.spring.application.context.lifecycle;

import org.springframework.context.Lifecycle;

/**
 * 自定义 {@link Lifecycle} 实现
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
public class MyLifecycle implements Lifecycle {

    private boolean running = false;

    @Override
    public void start() {
        running = true;
        System.out.println("MyLifecycle 启动...");
    }

    @Override
    public void stop() {
        running = false;
        System.out.println("MyLifecycle 停止...");
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}