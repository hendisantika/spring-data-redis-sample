package com.hendisantika.rediscache.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

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

    public PageRequest setPageableWithSort(
            String orderAsc,
            String orderDesc,
            Integer pageIndex,
            Integer size
    ) {
        pageIndex = Objects.nonNull(pageIndex) ? pageIndex : 0;
        size = Objects.nonNull(size) ? size : 20;
        if (Objects.nonNull(orderAsc)) {
            String[] ordersSplitAsc = orderAsc.contains(",") ? orderAsc.split(",") : new String[]{orderAsc};
            return PageRequest.of(pageIndex, size, Sort.by(ordersSplitAsc).ascending());
        } else if (Objects.nonNull(orderDesc)) {
            String[] ordersSplitDesc = orderDesc.contains(",") ? orderDesc.split(",") : new String[]{orderDesc};
            return PageRequest.of(pageIndex, size, Sort.by(ordersSplitDesc).descending());
        } else {
            return PageRequest.of(pageIndex, size);
        }
    }
}
