package com.hendisantika.rediscache.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-redis-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/14/23
 * Time: 07:29
 * To change this template use File | Settings | File Templates.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KabkotaRequest {
    private Integer cityId;

    private String cityName;

    private Integer cityProvId;

    private Integer citySeq;

    private String cityBpsCode;
}
