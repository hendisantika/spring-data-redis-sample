package com.hendisantika.rediscache.response;

import com.hendisantika.rediscache.entity.Province;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-redis-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/14/23
 * Time: 07:42
 * To change this template use File | Settings | File Templates.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer provinceId;

    private String provinceName;

    private Integer provinceSeq;

    private String provinceBpsCode;

    public ProvinceResponse(Province entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
