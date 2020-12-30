package cn.sh80.study.dstrubute.cache.ehcache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;

import java.time.Duration;

/**
 * 代码配置
 *
 * @author wangcongjun
 * @date 2020/12/30 11:28
 */
public class ProgrammaticEhchacheDemo {
    public static void main(String[] args) {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                // 随cacheManager一起初始化一个Cache
                .withCache("preConfigured",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
                                ResourcePoolsBuilder.heap(10)))
                .build();
        cacheManager.init();

        Cache<Long, String> preConfigured =
                cacheManager.getCache("preConfigured", Long.class, String.class);

        //自定义追加一个Cahce到manager中
        Cache<Long, String> myCache = cacheManager.createCache("myCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)));

        // 添加过期时间
        Cache<Long, String> myCache2 = cacheManager.createCache("myCache2",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
                        ResourcePoolsBuilder.heap(10)).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(20))));
        // 添加堆大小
        Cache<Long, String> myCache3 = cacheManager.createCache("myCache3",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
                        ResourcePoolsBuilder.newResourcePoolsBuilder()
                                .heap(10, MemoryUnit.MB)));

        myCache.put(1L, "da one!");
        String value = myCache.get(1L);
        System.out.println(value);

        cacheManager.removeCache("preConfigured");

        cacheManager.close();
    }
}
