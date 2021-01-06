package cn.sh80.study.dstrubute.cache.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author wangcongjun
 * @date 2021/1/6 10:02
 */
public class GuavaCacheDemo {
    static Cache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(2)
            .expireAfterWrite(3, TimeUnit.SECONDS)
            .build();

    public static String getValue(String key) throws ExecutionException {
        return cache.get(key,()->{
            System.out.printf("%s对应的值不存在\n",key);
            return "default";
        });
    }

    public static void main(String[] args) throws ExecutionException{
        cache.put("1","abc");
        System.out.println(getValue("1"));
        // 清空过期的缓存
        cache.cleanUp();
        // 清空所有缓存
        System.out.println(getValue("1"));
        cache.invalidateAll();
        System.out.println(getValue("1"));
        System.out.println(getValue("2"));
    }
}
