package cn.sh80.study.dstrubute.cache.ehcache2;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.DiskStorePathManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.DiskStoreConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;

/**
 * @author wangcongjun
 * @date 2020/12/30 15:50
 */
public class ProgrammaticEhcacheDemo {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        DiskStoreConfiguration diskStoreConfigurationParameter = new DiskStoreConfiguration();
        diskStoreConfigurationParameter.setPath("/opt/test");
        configuration.addDiskStore(diskStoreConfigurationParameter);
        CacheManager cacheManager = CacheManager.create(configuration);

        CacheConfiguration cacheConfiguration = new CacheConfiguration("test",10);
        PersistenceConfiguration persistenceConfiguration = new PersistenceConfiguration();
        persistenceConfiguration.strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP);
        cacheConfiguration.persistence(persistenceConfiguration);
        Cache cache = new Cache(cacheConfiguration);
        cacheManager.addCache(cache);


        Cache test = cacheManager.getCache("test");

        test.put(new Element(1L,"Hello world"));

        System.out.println(test.get(1L));
        cacheManager.shutdown();
    }
}
