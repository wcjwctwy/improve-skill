package cn.sh80.study.dstrubute.cache.ehcache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

import java.net.URL;

/**
 * XML配置
 *
 * @author wangcongjun
 * @date 2020/12/30 11:34
 */
public class XMLEhchacheDemo {
    public static void main(String[] args) {
        URL myUrl = XMLEhchacheDemo.class.getResource("/my-config.xml");
        Configuration xmlConfig = new XmlConfiguration(myUrl);
        CacheManager myCacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
        Cache<Long, String> xmlConfigured =
                myCacheManager.getCache("bar", Long.class, String.class);
        xmlConfigured.put(1L,"hello world");
        System.out.println(xmlConfigured.get(1L));
    }
}
