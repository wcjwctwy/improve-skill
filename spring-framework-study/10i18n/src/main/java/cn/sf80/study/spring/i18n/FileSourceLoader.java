package cn.sf80.study.spring.i18n;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author wangcongjun
 * @date 2020/12/10 16:32
 */
public class FileSourceLoader {
    private static ClassPathResource classPathResource = new ClassPathResource("test1.properties");
    private static ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) throws IOException {

        Properties properties = new Properties();
        properties.load(classPathResource.getInputStream());

        System.out.println(properties);

        // 创建监听服务
        FileSystem aDefault = FileSystems.getDefault();
        WatchService watchService = aDefault.newWatchService();
        // 注册监听事件
        Path parent = classPathResource.getFile().toPath().getParent();
        System.out.println(parent.toAbsolutePath());
        parent.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
        try {
            while (true) {
                System.out.println("siteListener开始监听");
                // 获取下一个文件改动事件
                WatchKey take = watchService.take();
                // 获取文件改动步骤
                for (WatchEvent<?> event : take.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    System.out.println("kind.name(): " + kind.name());

                    Path path = (Path) take.watchable();
                    System.out.println("path.toString(): " + path.toString());

                    Path filename = (Path) event.context();
                    System.out.println("filename: " + filename);
                }
                // 重设WatchKey
                boolean reset = take.reset();
                System.out.println("key reset:" + reset);
                System.out.println();
            }
        } catch (InterruptedException e) {
        }

    }
}
