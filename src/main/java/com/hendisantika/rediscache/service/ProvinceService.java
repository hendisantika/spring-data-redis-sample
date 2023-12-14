package com.hendisantika.rediscache.service;

import com.hendisantika.rediscache.entity.Province;
import com.hendisantika.rediscache.repository.KabkotaRepository;
import com.hendisantika.rediscache.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
