package cn.sh80.study.dstrubute.cache.spring.ranking.list;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wangcongjun
 * @date 2021/1/7 15:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class RankingServiceTest {

    @Autowired
    private RankingService rankingService;
    @Test
    void batchAdd() {
        rankingService.batchAdd();
    }

    @Test
    void top10() {
        rankingService.top10();
    }

    @Test
    void add() {
        rankingService.add();
    }

    @Test
    void count() {
        rankingService.count();
    }
}