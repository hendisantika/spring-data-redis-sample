package com.hendisantika.rediscache.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-redis-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/14/23
 * Time: 07:44
 * To change this template use File | Settings | File Templates.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponse<T> implements Serializable {
    private Date timestamp;
    private Integer statusCode;
    private String error;
    private Object message;
    private int ttlRecords;
    private int ttlPages;
    private int pageNo;
    private int pageRecords;
    private T data;
}
