package com.hendisantika.rediscache.controller;

import com.hendisantika.rediscache.response.BaseResponse;
import com.hendisantika.rediscache.response.ProvinceResponse;
import com.hendisantika.rediscache.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/provinces")
public class ProvinceController {
    private final ProvinceService provinceService;

    @GetMapping
    public BaseResponse<List<ProvinceResponse>> getAll(
            @RequestParam(value = "filter", required = false) List<String> filters,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "20") Integer size
    ) {
        return this.provinceService.getAll(filters, page, size);
    }

    @GetMapping("/v2")
    public ResponseEntity<BaseResponse> getAllV2(
            @RequestParam(value = "filter", required = false) List<String> filters,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "20") Integer size
    ) {
//        return ResponseEntity.ok(this.provinceService.getAll(filters, page, size).toString());
        return ResponseEntity.ok(this.provinceService.getAll(filters, page, size));
    }
}
