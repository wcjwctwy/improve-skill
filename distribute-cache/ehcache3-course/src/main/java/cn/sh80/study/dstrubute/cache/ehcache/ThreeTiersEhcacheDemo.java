package cn.sh80.study.dstrubute.cache.ehcache;

import org.ehcache.Cache;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;

import java.io.File;

/**
 * @author wangcongjun
 * @date 2020/12/30 14:33
 */
public class ThreeTiersEhcacheDemo {
    public static void main(String[] args) {
        PersistentCacheManager persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence(new File("/opt", "myData")))
                .withCache("threeTieredCache",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
                                ResourcePoolsBuilder.newResourcePoolsBuilder()
                                        .heap(10, EntryUnit.ENTRIES)
                                        .offheap(1, MemoryUnit.MB)
                                        .disk(20, MemoryUnit.MB, true)
                        )
                ).build(true);

        Cache<Long, String> threeTieredCache = persistentCacheManager.getCache("threeTieredCache", Long.class, String.class);
        threeTieredCache.put(1L, "stillAvailableAfterRestart");

        persistentCacheManager.close();
    }
}
