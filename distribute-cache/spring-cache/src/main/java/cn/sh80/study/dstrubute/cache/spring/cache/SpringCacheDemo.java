package cn.sh80.study.dstrubute.cache.spring.cache;

import cn.sh80.study.dstrubute.cache.spring.cache.entity.Account;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author wangcongjun
 * @date 2021/1/6 15:45
 */
@Configuration
@EnableCaching
public class SpringCacheDemo {
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("accountCache")));
        return simpleCacheManager;
    }

    public static String accountKey(Account account){
        return account.getName();
    }



    @Service
    @CacheConfig(cacheNames = "accountCache")
    public static class AccountService {
        @Cacheable(key = "#accountName")
        public Account getAccount(String accountName) {
            System.out.printf("获取账户名为%s的账户信息\n",accountName);
            Account account = getFormDb(accountName);

            if (account == null) {
                throw new RuntimeException("account can't be null");
            }
            return account;
        }

        private Account getFormDb(String accountName) {
            System.out.printf("查询账户名为%s的账户信息\n",accountName);
            return new Account(accountName);
        }

        /**
         * 通过静态方法设置key
         *
         * @param account
         * @return
         */
        @CachePut(key = "T(cn.sh80.study.dstrubute.cache.spring.cache.SpringCacheDemo)" +
                ".accountKey(#account)")
        public Account saveAccount(Account account){
            System.out.printf("存储账户%s信息\n",account.getName());
            return account;
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringCacheDemo.class);
        context.refresh();
        AccountService bean = context.getBean(AccountService.class);
        Account abc = bean.getAccount("abc");
        System.out.println(abc);

        Account abc1 = bean.getAccount("abc");
        System.out.println(abc1);

        bean.saveAccount(new Account("123"));

        Account account = bean.getAccount("123");
        System.out.println(account);

        context.close();
    }
}
