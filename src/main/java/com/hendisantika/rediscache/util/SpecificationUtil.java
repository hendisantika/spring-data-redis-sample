package com.hendisantika.rediscache.util;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    private String[] splitFilter(String filter) {
        try {
            if (filter.contains(CommonConstant.FilterOperators.LESS_THAN_OR_EQUALS)) {
                String[] splitStr = filter.split(CommonConstant.FilterOperators.LESS_THAN_OR_EQUALS);
                String field = splitStr[0];
                String value = splitStr[1];
                String[] result = new String[splitStr.length + 1];
                result[0] = field;
                result[1] = CommonConstant.FilterOperators.LESS_THAN_OR_EQUALS;
                result[2] = value;
                return result;
            } else if (filter.contains(CommonConstant.FilterOperators.GREATER_THAN_OR_EQUALS)) {
                String[] splitStr = filter.split(CommonConstant.FilterOperators.GREATER_THAN_OR_EQUALS);
                String field = splitStr[0];
                String value = splitStr[1];
                String[] result = new String[splitStr.length + 1];
                result[0] = field;
                result[1] = CommonConstant.FilterOperators.GREATER_THAN_OR_EQUALS;
                result[2] = value;
                return result;
            } else if (filter.contains(CommonConstant.FilterOperators.NOT_EQUALS)) {
                String[] splitStr = filter.split(CommonConstant.FilterOperators.NOT_EQUALS);
                String field = splitStr[0];
                String value = splitStr[1];
                String[] result = new String[splitStr.length + 1];
                result[0] = field;
                result[1] = CommonConstant.FilterOperators.NOT_EQUALS;
                result[2] = value;
                return result;
            } else if (filter.contains(CommonConstant.FilterOperators.FILTER_EQUALS)) {
                String[] splitStr = filter.split(CommonConstant.FilterOperators.FILTER_EQUALS);
                String field = splitStr[0];
                String value = splitStr[1];
                String[] result = new String[splitStr.length + 1];
                result[0] = field;
                result[1] = value.startsWith("[") ?
                        CommonConstant.FilterOperators.IN :
                        value.contains("%") ?
                                CommonConstant.FilterOperators.LIKE :
                                CommonConstant.FilterOperators.FILTER_EQUALS;
                result[2] = value;
                return result;
            } else if (filter.contains(CommonConstant.FilterOperators.LESS_THAN)) {
                String[] splitStr = filter.split(CommonConstant.FilterOperators.LESS_THAN);
                String field = splitStr[0];
                String value = splitStr[1];
                String[] result = new String[splitStr.length + 1];
                result[0] = field;
                result[1] = CommonConstant.FilterOperators.LESS_THAN;
                result[2] = value;
                return result;
            } else if (filter.contains(CommonConstant.FilterOperators.GREATER_THAN)) {
                String[] splitStr = filter.split(CommonConstant.FilterOperators.GREATER_THAN);
                String field = splitStr[0];
                String value = splitStr[1];
                String[] result = new String[splitStr.length + 1];
                result[0] = field;
                result[1] = CommonConstant.FilterOperators.GREATER_THAN;
                result[2] = value;
                return result;
            } else {
                throw new CommonApiException(CommonConstant.CommonExceptionMessage.FILTER_INVALID_FORMAT, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            throw new CommonApiException(CommonConstant.CommonExceptionMessage.FILTER_INVALID_FORMAT, HttpStatus.BAD_REQUEST);
        }
    }

    private Object parseValue(Root<V> root, String field, Object value, String entityKey) {
        try {
            String javaType;
            if (Objects.nonNull(entityKey)) {
                javaType = root.get(entityKey).get(field).getJavaType().getSimpleName();
            } else {
                javaType = root.get(field).getJavaType().getSimpleName();
            }
            if (javaType.equalsIgnoreCase("Integer") && !isNotNullOrIsNull(value) && !isValueIn(value)) {
                return Integer.parseInt((String) value);
            } else if (javaType.equalsIgnoreCase("Decimal") && !isNotNullOrIsNull(value) && !isValueIn(value)) {
                return new BigDecimal(String.valueOf(value));
            } else if (
                    (javaType.equalsIgnoreCase("Date")
                            || javaType.equalsIgnoreCase("Timestamp"))
                            && !isNotNullOrIsNull(value)
                            && !isValueIn(value)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return sdf.parse(String.valueOf(value));
            } else {
                return String.valueOf(value);
            }
        } catch (Exception ex) {
            throw new CommonApiException(CommonConstant.CommonExceptionMessage.FILTER_INVALID_FORMAT, HttpStatus.BAD_REQUEST);
        }
    }

    private Predicate createPredicate(Root<V> root, CriteriaBuilder cb, String field, String operator, String val, String entityKey) {
        try {
            Object value = parseValue(root, field, val, entityKey);
            Expression fieldEntity;
            if (Objects.nonNull(entityKey)) {
                fieldEntity = root.get(entityKey).get(field);
            } else {
                fieldEntity = root.get(field);
            }
            switch (operator) {
                case CommonConstant.FilterOperators.FILTER_EQUALS:
                    if (val.toLowerCase().contains("isnull")) {
                        return cb.isNull(fieldEntity);
                    } else if (val.toLowerCase().contains("isnotnull")) {
                        return cb.isNotNull(fieldEntity);
                    } else {
                        return (value instanceof Date)
                                ? cb.equal(fieldEntity.as(Date.class), value)
                                : cb.equal(cb.toString(fieldEntity), String.valueOf(value));
                    }
                case CommonConstant.FilterOperators.LIKE:
                    return cb.like(cb.upper(fieldEntity), String.valueOf(value).toUpperCase());
                case CommonConstant.FilterOperators.IN:
                    int startIndex = val.indexOf("[");
                    int endIndex = val.indexOf("]");
                    String valuesString = val.substring(startIndex + 1, endIndex);
                    String[] array = valuesString.split(",\\s*");
                    return fieldEntity.in(array);
                case CommonConstant.FilterOperators.NOT_EQUALS:
                    return cb.notEqual(fieldEntity, value);
                case CommonConstant.FilterOperators.GREATER_THAN:
                    return cb.greaterThan(fieldEntity, (Comparable) value);
                case CommonConstant.FilterOperators.LESS_THAN:
                    return cb.lessThan(fieldEntity, (Comparable) value);
                case CommonConstant.FilterOperators.GREATER_THAN_OR_EQUALS:
                    return cb.greaterThanOrEqualTo(fieldEntity, (Comparable) value);
                case CommonConstant.FilterOperators.LESS_THAN_OR_EQUALS:
                    return cb.lessThanOrEqualTo(fieldEntity, (Comparable) value);
                default:
                    throw new IllegalArgumentException("Operator not supported: " + operator);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CommonApiException(CommonConstant.CommonExceptionMessage.FILTER_INVALID_FORMAT, HttpStatus.BAD_REQUEST);
        }
    }

    private String getEntityKey(Class classEntity, String fieldParam) {
        String name = null;
        if (Objects.nonNull(classEntity)) {
            Field[] fields = classEntity.getDeclaredFields();
            for (Field field : fields) {
                if (fieldParam.equalsIgnoreCase(field.getName())) {
                    return null;
                }
                name = field.isAnnotationPresent(EmbeddedId.class) ? field.getName() : name;
            }
        }
        return name;
    }

    private boolean isNotNullOrIsNull(Object value) {
        try {
            String val = String.valueOf(value);
            return val.equalsIgnoreCase("isnull") || val.equalsIgnoreCase("isnotnull");
        } catch (Exception e) {
            return false;
        }
    }
}
