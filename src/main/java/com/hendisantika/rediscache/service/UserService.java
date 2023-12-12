package com.hendisantika.rediscache.service;

import com.hendisantika.rediscache.entity.User;
import com.hendisantika.rediscache.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
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
@RequiredArgsConstructor
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Cacheable(value = "userCache")
    public User getUser(Long id) throws Exception {
        LOGGER.debug("No cache,find from db...id=" + id);
        return userRepository.findById(id).orElseThrow(() -> new Exception("User not found - with: " + id));
    }

    @Cacheable(value = "userListCache")
    public List<User> getAllUsers(PageRequest pageRequest) {
        List<User> userList = userRepository.findAll(pageRequest);
        LOGGER.debug("No cache, find from db... user data = " + userList);
        return userList;
    }

}
