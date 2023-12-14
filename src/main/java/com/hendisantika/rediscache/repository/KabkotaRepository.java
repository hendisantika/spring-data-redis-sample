package com.hendisantika.rediscache.repository;

import com.hendisantika.rediscache.entity.Kabkota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-redis-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/14/23
 * Time: 07:30
 * To change this template use File | Settings | File Templates.
 */
public interface KabkotaRepository extends JpaRepository<Kabkota, Integer>, JpaSpecificationExecutor<Kabkota> {
    List<Kabkota> findByCityProvId(Integer id);

    boolean existsByCityBpsCode(String code);
}
