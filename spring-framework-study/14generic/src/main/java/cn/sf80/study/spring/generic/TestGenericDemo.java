package cn.sf80.study.spring.generic;

import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ResolvableType;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangcongjun
 * @date 2020/12/18 14:27
 */
public class TestGenericDemo {
    public static void main(String[] args) throws NoSuchMethodException {
        testClassMethodParams();
    }

    public static void testClassMethodParams(){
        Test<List<String>> s = new Test<List<String>>(){};
        ResolvableType resolvableType = ResolvableType.forMethodParameter(ReflectionUtils.findMethod(s.getClass(),
                "test",Map.class),0);
        System.out.println("利用Spring 中的ResolvableType获取："+resolvableType.resolveGeneric(0));
    }
    public static void testClassMethod(){
        Test<List<String>> s = new Test<List<String>>(){};
        ResolvableType resolvableType = ResolvableType.forMethodReturnType(ReflectionUtils.findMethod(s.getClass(),
                "test"));
        System.out.println("利用Spring 中的ResolvableType获取："+resolvableType.resolveGeneric(0));
    }
    public static void testClassField(){
        Test<List<String>> s = new Test<List<String>>(){};
        ResolvableType resolvableType = ResolvableType.forField(ReflectionUtils.findField(s.getClass(), "test1"));

        System.out.println("利用Spring 中的ResolvableType获取："+resolvableType.resolveGeneric(0));
    }
    public static void testClass(){
        Test<List<String>> s = new Test<List<String>>(){};
        ResolvableType resolvableType = ResolvableType.forInstance(s);
        System.out.println("利用Spring 中的ResolvableType获取："+resolvableType.resolveGeneric(0));


        // 泛型参数类型 parameterized type
        Type genericSuperclass = s.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        System.out.println("类外获取：" + parameterizedType.getActualTypeArguments()[0]);

        System.out.println("类提供方法获取：" + s.getTestClass());
    }
    static abstract class Test<T>{
        private List<Integer> test1;
        public List<String> test(Map<String,Integer> params){
            return null;
        }
        public List<String> test(Integer params){
            return null;
        }


        public String getTestClass(){
            Type genericSuperclass = getClass().getGenericSuperclass();
            if(genericSuperclass instanceof ParameterizedType){
                ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
                return parameterizedType.getActualTypeArguments()[0].getTypeName();
            }
            return null;
        }
    }

}
