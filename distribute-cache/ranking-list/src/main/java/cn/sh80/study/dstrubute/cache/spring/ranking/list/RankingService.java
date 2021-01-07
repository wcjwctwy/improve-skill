package cn.sh80.study.dstrubute.cache.spring.ranking.list;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wangcongjun
 * @date 2021/1/7 14:52
 */
@Service
@Slf4j
public class RankingService {

    private final static String SCORE_RANK = "score_rank";

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void batchAdd() {
        Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<>();
        long startTime = System.currentTimeMillis();

        for(int i=0; i<10000; i++) {
            DefaultTypedTuple<String> tuple = new DefaultTypedTuple<>("张三" + i, 1D+1);
            tuples.add(tuple);
        }

        long runTime = System.currentTimeMillis() - startTime;
        log.info("runTime: " + runTime);
        redisTemplate.opsForZSet().add(SCORE_RANK, tuples);
    }

    public void top10(){
        Set<String> range = redisTemplate.opsForZSet().reverseRange(SCORE_RANK, 0, 10);
        log.info("获取到的排行列表：" + range);
        redisTemplate.opsForZSet().removeRange(SCORE_RANK,0,10);

        Set<ZSetOperations.TypedTuple<String>> rangeWithScores = redisTemplate.opsForZSet().reverseRangeWithScores(SCORE_RANK, 0, 10);
        log.info("获取到的排行和分数列表：" + rangeWithScores);
    }

    public void add() {
        redisTemplate.opsForZSet().add(SCORE_RANK, "李四", 9000);
    }

    public void count() {
        long count = redisTemplate.opsForZSet().count(SCORE_RANK, 8001, 9000);
        log.info("统计8001-9000之间的人数： "+ count);
    }

}
