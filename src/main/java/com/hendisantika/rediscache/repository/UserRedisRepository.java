package com.hendisantika.rediscache.repository;

import com.hendisantika.rediscache.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-redis-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/18/23
 * Time: 09:29
 * To change this template use File | Settings | File Templates.
 */
@Repository
@Slf4j
public class UserRedisRepository {
    private final HashOperations hashOperations;

    public UserRedisRepository(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void create(User user) {
        hashOperations.put("USER", user.getId(), user);
        log.info(String.format("User with ID %s saved", user.getId()));
    }

    public User get(String userId) {
        return (User) hashOperations.get("USER", userId);
    }

    public Map<String, User> getAll() {
        return hashOperations.entries("USER");
    }

    public void update(User user) {
        hashOperations.put("USER", user.getId(), user);
        log.info(String.format("User with ID %s updated", user.getId()));
    }

    public void delete(String userId) {
        hashOperations.delete("USER", userId);
        log.info(String.format("User with ID %s deleted", userId));
    }
}
