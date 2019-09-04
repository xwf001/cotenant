package com.youyu.cotenant;

import com.youyu.cotenant.utils.SpringRedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CotenantApplicationTests {

    @Autowired
    private SpringRedisUtils redisUtils;

    @Test
    public void contextLoads() {
        System.out.println("测试啊");
        redisUtils.lRightPush("list1", "{第1条消息}");
        redisUtils.lRightPush("list1", "{第2条消息}");
        redisUtils.lRightPush("list1", "{第3条消息}");
        redisUtils.lRightPush("list1", "{第4条消息}");
    }

    @Test
    public void x1() {
        List<String> x1 = redisUtils.lRange("list1", 0L, redisUtils.lLen("list1")-1);
        System.out.println(x1);
    }

}
