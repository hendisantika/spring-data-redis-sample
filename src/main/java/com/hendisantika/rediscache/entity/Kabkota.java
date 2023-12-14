package com.hendisantika.rediscache.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-redis-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/14/23
 * Time: 07:27
 * To change this template use File | Settings | File Templates.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "cities")
public class Kabkota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id", unique = false, nullable = false, insertable = true, updatable = true)
    private Integer cityId;

    @Column(name = "city_name", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
    private String cityName;

    @Column(name = "city_prov_id", unique = false, nullable = false, insertable = true, updatable = true)
    private Integer cityProvId;

    @Column(name = "city_seq", unique = false, nullable = false, insertable = true, updatable = true)
    private Integer citySeq;

    @Column(name = "city_bps_code", unique = true, nullable = true, insertable = true, updatable = true, length = 5)
    private String cityBpsCode;

    @Column(name = "city_created_by", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
    @CreatedBy
    private String cityCreatedBy;

    @Column(name = "city_created_date", unique = false, nullable = false, insertable = true, updatable = true)
    @CreatedDate
    private Date cityCreatedDate;

    @LastModifiedBy
    @Column(name = "city_updated_by", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
    private String cityUpdatedBy;

    @LastModifiedDate
    @Column(name = "city_updated_date", unique = false, nullable = true, insertable = true, updatable = true)
    private Date cityUpdatedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_prov_id", insertable = false, updatable = false)
    private Province province;

    public KabkotaEntity(KabkotaRequest model) {
        BeanUtils.copyProperties(model, this);
    }

    public KabkotaEntity(KabkotaRequest model, Integer provId) {
        BeanUtils.copyProperties(model, this);
        this.cityProvId = provId;
    }
}
