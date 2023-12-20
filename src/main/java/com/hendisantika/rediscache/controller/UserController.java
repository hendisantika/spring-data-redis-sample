package com.hendisantika.rediscache.controller;

import com.hendisantika.rediscache.entity.User;
import com.hendisantika.rediscache.repository.UserRedisRepository;
import com.hendisantika.rediscache.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

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

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    private final UserRedisRepository userRedisRepository;

    @GetMapping(value = "{userId}")
    public User getUser(@PathVariable("userId") Long userId) throws Exception {
        LOGGER.debug("Get User Request...");
        return userService.getUser(userId);
    }

    @GetMapping
    public List<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "100") int size,
                                  @RequestParam(defaultValue = "id,desc") String[] sort) {
        LOGGER.debug("Get All Users Request...");
        PageRequest pageRequest = PageRequest.of(page, size);
        //pass it to repos
//        Page<User> pagingUser = userService.getAllUsers(pageRequest);
        return userService.getAllUsers(pageRequest);
        //pagingUser.hasContent(); -- to check pages are there or not
//        return pagingUser.getContent();
    }

//    @PostMapping("/redis/add")
//    public User addNewUser(@RequestBody User user){
//        UserRepository userRepository = new UserRepository(redisTemplate());
//
//        userRedisRepository.create(new User("1", "username", "emailid"));
//        User newUser = userRedisRepository.get("1");
//        userRedisRepository.update(user);
//        userRedisRepository.delete(user.getUserId());
//    }

}
