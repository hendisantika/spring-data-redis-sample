package com.hendisantika.rediscache.controller;

import com.hendisantika.rediscache.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-redis-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/14/23
 * Time: 07:55
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequiredArgsConstructor
public class ProvinceController {
    private final ProvinceService provinceService;
}
