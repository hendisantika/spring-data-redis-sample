package com.hendisantika.rediscache.controller;

import com.hendisantika.rediscache.response.BaseResponse;
import com.hendisantika.rediscache.response.ProvinceResponse;
import com.hendisantika.rediscache.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
@RequestMapping("/api/v1/provinces")
public class ProvinceController {
    private final ProvinceService provinceService;

    @GetMapping
//    @Cacheable(value = "provinceListCache", cacheNames = "provinceListCache", key="#p0")
    public ResponseEntity<BaseResponse<List<ProvinceResponse>>> getAll(
            @RequestParam(value = "filter", required = false) List<String> filters,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "20") Integer size
    ) {
        return this.provinceService.getAll(filters, page, size);
    }
}
