package com.junehua.blog;

import com.junehua.blog.dao.BlogDao;
import com.junehua.blog.dto.ShowBlog;
import com.junehua.blog.pojo.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    BlogDao blogDao;

    //测试redis
    @Test
    void contextLoads() {
        stringRedisTemplate.opsForValue().append("key1", "value1");
//        String key1 = stringRedisTemplate.opsForValue().get("key1");
//        System.out.println(key1);
    }

    @Test
    void testRedisHash(){
        ShowBlog blogById = blogDao.getBlogById((long) 3);
        System.out.println(blogById);
        redisTemplate.opsForValue().set("blog", blogById);
    }



}
