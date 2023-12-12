package com.hendisantika.rediscache.repository;

import com.hendisantika.rediscache.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : redis-cache
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/10/18
 * Time: 09.19
 * To change this template use File | Settings | File Templates.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();

    List<User> findAll(Pageable pageable);
}
