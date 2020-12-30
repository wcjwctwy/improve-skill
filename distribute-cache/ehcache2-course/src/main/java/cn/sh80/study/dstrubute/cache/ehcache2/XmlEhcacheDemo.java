package cn.sh80.study.dstrubute.cache.ehcache2;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.DiskStoreConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.config.generator.ConfigurationSource;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author wangcongjun
 * @date 2020/12/30 15:50
 */
public class XmlEhcacheDemo {
    public static void main(String[] args) throws URISyntaxException {
        URL resource = XmlEhcacheDemo.class.getClassLoader().getResource("ehcache.xml");
        CacheManager cacheManager = CacheManager.create(resource);

        CacheConfiguration cacheConfiguration = new CacheConfiguration("test",10);
        PersistenceConfiguration persistenceConfiguration = new PersistenceConfiguration();
        persistenceConfiguration.strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP);
        cacheConfiguration.persistence(persistenceConfiguration);
        Cache cache = new Cache(cacheConfiguration);
        cacheManager.addCache(cache);


        Cache test = cacheManager.getCache("sampleCache");

        test.put(new Element(10L,"Hello world"));

        System.out.println(test.get(10L));
    }
}
