package com.hendisantika.rediscache.repository;

import com.hendisantika.rediscache.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-redis-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/14/23
 * Time: 07:26
 * To change this template use File | Settings | File Templates.
 */
public interface ProvinceRepository extends JpaRepository<Province, Integer>, JpaSpecificationExecutor<Province> {
    Integer countByProvinceBpsCode(String code);

    boolean existsByProvinceBpsCode(String code);
}
