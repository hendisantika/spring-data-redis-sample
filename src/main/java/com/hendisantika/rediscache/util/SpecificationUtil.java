package com.hendisantika.rediscache.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-redis-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/14/23
 * Time: 07:31
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Component
public class SpecificationUtil {
    public Specification<V> buildSpecification(List<String> filters) {
        return buildSpecification(filters, null);
    }
}
