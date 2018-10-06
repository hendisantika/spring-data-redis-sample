package com.hendisantika.rediscache.service;

import com.hendisantika.rediscache.entity.User;
import com.hendisantika.rediscache.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : redis-cache
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 24/12/17
 * Time: 21.10
 * To change this template use File | Settings | File Templates.
 */

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Cacheable(value = "userCache")
    public User getUser(Long id, String username, String password) {
        LOGGER.debug("No cache,find from db...id=" + id);
        return new User(id, username, password);
    }

    @Cacheable(value = "userListCache")
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        LOGGER.debug("No cache, find from db... user data = " + userList);
        return userList;
    }



}
