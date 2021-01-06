package cn.sh80.study.dstrubute.cache.ehcache2;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author wangcongjun
 * @date 2020/12/31 16:13
 */
public class Main
{
//    public static void main(String[] args) {
//        System.out.println();
//    }

    public static boolean compileFile(String sFullFileName, String sOutputPath) throws IOException {
        boolean bRet = false;
        // get compiler
        JavaCompiler oJavaCompiler = ToolProvider.getSystemJavaCompiler();

        // define the diagnostic object, which will be used to save the
        // diagnostic information
        DiagnosticCollector<JavaFileObject> oDiagnosticCollector = new DiagnosticCollector<JavaFileObject>();

        // get StandardJavaFileManager object, and set the diagnostic for the
        // object
        StandardJavaFileManager oStandardJavaFileManager = oJavaCompiler
                .getStandardFileManager(oDiagnosticCollector, null, null);

        // set class output location
        JavaFileManager.Location oLocation = StandardLocation.CLASS_OUTPUT;
        try {
            oStandardJavaFileManager.setLocation(oLocation, Arrays
                    .asList(new File[]{new File(sOutputPath)}));

            // get JavaFileObject object, it will specify the java source file.
            Iterable<? extends JavaFileObject> oItJavaFileObject = oStandardJavaFileManager
                    .getJavaFileObjectsFromFiles(Arrays.asList(new File(
                            sFullFileName)));

            // compile the java source code by using CompilationTask's call
            // method
            bRet = oJavaCompiler.getTask(null, oStandardJavaFileManager,
                    oDiagnosticCollector, null, null, oItJavaFileObject).call();

            //print the Diagnostic's information
            for (Diagnostic oDiagnostic : oDiagnosticCollector
                    .getDiagnostics()) {
                System.out.println("Error on line: "
                        + oDiagnostic.getLineNumber() + "; URI: "
                        + oDiagnostic.getSource().toString());
            }
        } catch (IOException e) {
            //exception process
            System.out.println("IO Exception: " + e);
            throw e;
        } finally {
            //close file manager
            if (null != oStandardJavaFileManager) {
                oStandardJavaFileManager.close();
            }
        }
        return bRet;
    }


    public static void main(String[] args) {
        try {
            compileFile("E:\\IdeaProjects\\InfinityWar\\study\\spring-study\\distribute-cache\\ehcache2-course\\src" +
                    "\\main\\java\\cn\\sh80\\study\\dstrubute\\cache\\ehcache2\\ProgrammaticEhcacheDemo.java", "\\opt");
        } catch (Exception e) {
        }
    }
}
