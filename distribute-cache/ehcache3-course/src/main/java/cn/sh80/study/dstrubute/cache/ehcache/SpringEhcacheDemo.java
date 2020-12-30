package cn.sh80.study.dstrubute.cache.ehcache;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author wangcongjun
 * @date 2020/12/30 14:50
 */

public class SpringEhcacheDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringEhcacheDemo.class);



        context.refresh();

        context.close();
    }

    @Bean
    public EhCacheCacheManager cacheManager(){
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        CacheManager testCache = CacheManagerBuilder.newCacheManagerBuilder().withCache("testCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, String.class,
                        ResourcePoolsBuilder.heap(10))).build();
//        ehCacheCacheManager.setCacheManager(testCache);

        return ehCacheCacheManager;
    }
}
