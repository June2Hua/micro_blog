package com.junehua.blog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{

    private static final Logger log = LoggerFactory.getLogger(RedisConfig.class);

    /**
     * 生成key的策略:根据类名+方法名+所有参数的值生成唯一的一个key
     * @return
     */
    @Bean("myKeyGenerator")
    @Override
    public KeyGenerator keyGenerator() {
        return (Object target, Method method, Object... params) -> {
            //使用stringBuilder创建，减少空间浪费
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());//类名
            sb.append(method.getName());//方法名
            //所有的参数
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }


    /**
     *     原Bean
     *     @Bean
     *     @ConditionalOnMissingBean(name = "redisTemplate")
     *     public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
     *             throws UnknownHostException {
     *         RedisTemplate<Object, Object> template = new RedisTemplate<>();
     *         template.setConnectionFactory(redisConnectionFactory);
     *         return template;
     *     }
     * 重写redisTemplate，使用json方式
     * @param redisConnectionFactory
     * @return
     * @throws UnknownHostException
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> serializer=new Jackson2JsonRedisSerializer<Object>(Object.class);
        template.setDefaultSerializer(serializer);
        return template;
    }


    /**
     * 重写cacheManager
     * @param factory
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheConfiguration cacheConfiguration =RedisCacheConfiguration.defaultCacheConfig()
                                                    .entryTtl(Duration.ofDays(1))
                                                    .disableCachingNullValues()
                                                    .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new
                                                                         GenericJackson2JsonRedisSerializer()));
        return RedisCacheManager.builder(factory).cacheDefaults(cacheConfiguration).build();
    }

}
