package cn.sh80.study.dstrubute.cache.ehcache2;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager ;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.net.URL;

/**
 * @author wangcongjun
 * @date 2020/12/30 16:42
 */
public class SpringEhcacheDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(SpringEhcacheDemo.class);

        context.refresh();

        CacheManager cacheManager = context.getBean(CacheManager.class);
        Cache springCache = cacheManager.getCache("springCache");
        springCache.put(1L,"ttttt");
        Cache.ValueWrapper valueWrapper = springCache.get(1L);
        System.out.println(valueWrapper.get());

        EhCacheCacheManager ehCacheCacheManager =  (EhCacheCacheManager)cacheManager;
        ehCacheCacheManager.getCacheManager().shutdown();
        context.close();
    }

    @Bean
    public CacheManager ehCacheCacheManager(){
        URL resource = XmlEhcacheDemo.class.getClassLoader().getResource("ehcache-spring.xml");
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        net.sf.ehcache.CacheManager cacheManager = net.sf.ehcache.CacheManager.create(resource);
        ehCacheCacheManager.setCacheManager(cacheManager);
        return ehCacheCacheManager;
    }
}
