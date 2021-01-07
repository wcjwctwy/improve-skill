package cn.sh80.study.dstrubute.cache.ehcache;

import javax.tools.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * @author wangcongjun
 * @date 2021/1/7 10:46
 */
public class Main {

    public static void main(String[] args) throws IOException {

        compile();
    }
    public static void compiles() throws IOException {

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> compilationUnits1 =
                fileManager.getJavaFileObjectsFromFiles(Arrays.asList(new File("\\opt\\javasrc\\cn\\B.java")));
        compiler.getTask(null, fileManager, null, Arrays.asList("-sourcepath", "/opt/javasrc","-d","/opt/javaClass",
                "-cp","/opt/javaClass"),
                null,
                compilationUnits1).call();
    }


    public static void compile() throws IOException {
        Properties properties = System.getProperties();
        System.out.println(File.pathSeparator);
        System.out.println(File.separator);
        properties.forEach((k,v)->{
            System.out.println(k+"---->"+v);
        });
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
       StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> compilationUnits1 =
            fileManager.getJavaFileObjectsFromFiles(Arrays.asList(new File("\\opt\\javasrc\\cn\\B.java")));
//        compiler.getTask(null, fileManager, null, Arrays.asList("-d","/opt/javaClass",
//                "-cp","/opt/javaClass"), null,
//                compilationUnits1).call();
        String classpath = System.getProperty("java.class.path");
        compiler.getTask(null, fileManager, null, Arrays.asList("-d","/opt/javaClass",
                "-cp",classpath+";/opt/javaClass"), null,
                compilationUnits1).call();

    }
}
