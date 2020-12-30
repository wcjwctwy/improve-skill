package cn.sh80.study.dstrubute.cache.ehcache;

import org.ehcache.config.CacheRuntimeConfiguration;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.core.config.DefaultConfiguration;
import org.ehcache.impl.config.persistence.DefaultPersistenceConfiguration;
import org.ehcache.impl.config.store.heap.DefaultSizeOfEngineProviderConfiguration;
import org.ehcache.jsr107.Eh107Configuration;
import org.ehcache.jsr107.EhcacheCachingProvider;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.*;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.Caching;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;
import java.io.File;

/**
 * The Ehcache 3.x JSR-107 ProviderCURRENT
 *
 * @author wangcongjun
 * @date 2020/12/30 15:06
 */
public class JavaxCacheDemo {
    public static void main(String[] args) {
//        started();
    }

    public static void startWithEhConfig(){
        CachingProvider cachingProvider = Caching.getCachingProvider();
        EhcacheCachingProvider ehcacheProvider = (EhcacheCachingProvider) cachingProvider;

        DefaultPersistenceConfiguration defaultPersistenceConfiguration = new DefaultPersistenceConfiguration(new File("/opt/test"));
        DefaultSizeOfEngineProviderConfiguration defaultSizeOfEngineProviderConfiguration = new DefaultSizeOfEngineProviderConfiguration(10, MemoryUnit.MB, 10000);
        DefaultConfiguration configuration = new DefaultConfiguration(ehcacheProvider.getDefaultClassLoader(),
                defaultPersistenceConfiguration,defaultSizeOfEngineProviderConfiguration);


        CacheManager cacheManager = ehcacheProvider.getCacheManager(ehcacheProvider.getDefaultURI(), configuration);
    }

    /**
     * Getting Started with Ehcache and JCache (JSR-107)
     */
    public static void started() {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
        MutableConfiguration<Long, String> configuration =
                new MutableConfiguration<Long, String>()
                        .setTypes(Long.class, String.class)
                        .setStoreByValue(false)
                        .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(Duration.ONE_MINUTE));
        Cache<Long, String> cache = cacheManager.createCache("jCache", configuration);
        cache.put(1L, "one");
        String value = cache.get(1L);
        System.out.println(value);
    }
}
