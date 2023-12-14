package com.hendisantika.rediscache.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-redis-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/13/23
 * Time: 09:30
 * To change this template use File | Settings | File Templates.
 */
@Data
@NoArgsConstructor
public class ProvinceRequest {
    private Integer provinceId;

    private String provinceName;

    private Integer provinceSeq;

    private String provinceBpsCode;
}
