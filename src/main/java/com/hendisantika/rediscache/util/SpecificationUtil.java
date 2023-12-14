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

    public Specification<V> buildSpecification(List<String> filters, Class classEntity) {
        Specification<V> spec = Specification.where(null);
        for (String filter : filters) {
            String[] parts = splitFilter(filter);
            if (parts.length == 3) {
                String field = parts[0];
                String operator = parts[1];
                String value = parts[2];
                String entityKey = getEntityKey(classEntity, field);
                spec = spec.and((root, query, cb) -> createPredicate(root, cb, field, operator, value, entityKey));
            }
        }
        return spec;
    }
}
