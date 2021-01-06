package cn.sh80.study.dstrubute.cache.spring.cache;

import cn.sh80.study.dstrubute.cache.spring.cache.entity.Account;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wangcongjun
 * @date 2021/1/6 15:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringCacheDemo.class)
class SpringCacheDemoTest {

    @Autowired
    SimpleCacheManager simpleCacheManager;
    @Autowired
    private SpringCacheDemo.AccountService accountService;

    @Test
    void saveAccount() {
        Account account = new Account("123");
        accountService.saveAccount(account);

        ConcurrentMapCache accountCache = (ConcurrentMapCache)simpleCacheManager.getCache("accountCache");


        Account _123 = accountService.getAccount("123");

        System.out.println(_123);
    }
    @Test
    void getAccount() {
        Account abc = accountService.getAccount("abc");
        System.out.println(abc);
        Account abc1 = accountService.getAccount("abc");
        System.out.println(abc);
    }
}