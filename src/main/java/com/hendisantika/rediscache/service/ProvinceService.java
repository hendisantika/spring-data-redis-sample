package com.hendisantika.rediscache.service;

import com.hendisantika.rediscache.entity.Province;
import com.hendisantika.rediscache.repository.KabkotaRepository;
import com.hendisantika.rediscache.repository.ProvinceRepository;
import com.hendisantika.rediscache.response.ProvinceResponse;
import com.hendisantika.rediscache.util.SpecificationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-redis-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/13/23
 * Time: 09:33
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class ProvinceService {
    private final ProvinceRepository mstProvincerepository;
    private final KabkotaRepository mstKabkotaRepository;
    private final SpecificationUtil<Province> specificationUtil;

    @Transactional(readOnly = true)
//    @Cacheable(value = "provinceListCache",cacheNames = "provinceListCache", key="#response?.body.id")
//    @Cacheable(value = "provinceListCache",cacheNames = "provinceListCache", key="#mstProvinceService")
    public ResponseEntity<BaseResponse<List<ProvinceResponse>>> getAll(
            List<String> filters,
            Integer pageIndex,
            Integer pageSize
    ) {
        Page<Province> pageResult = null;
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        if (filters != null && !filters.isEmpty()) {
            Specification<Province> spec = specificationUtil.buildSpecification(filters);
            pageResult = mstProvincerepository.findAll(spec, pageable);
        } else {
            pageResult = mstProvincerepository.findAll(pageable);
        }
        List<ProvinceResponse> dataList;
        if (pageResult.getContent().isEmpty()) {
            dataList = null;
        } else {
            dataList = pageResult.getContent().stream()
                    .map(ProvinceResponse::new)
                    .toList();
        }
        var response = BaseResponse.<List<ProvinceResponse>>builder()
                .timestamp(new Date())
                .data(dataList)
                .statusCode(HttpStatus.OK.value())
                .message("Get Success")
                .pageNo(pageIndex)
                .pageRecords(pageResult.getSize())
                .ttlPages(pageResult.getTotalPages())
                .ttlRecords((int) pageResult.getTotalElements())
                .build();
        return ResponseEntity.ok(response);
    }
}
