package com.hendisantika.rediscache.entity;

import com.hendisantika.rediscache.request.ProvinceRequest;
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

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-redis-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/13/23
 * Time: 09:29
 * To change this template use File | Settings | File Templates.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "provinces")
public class Province implements Serializable {
    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "province_id", unique = false, nullable = false, insertable = true, updatable = true)
    private Integer provinceId;

    @Column(name = "province_name", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
    private String provinceName;

    @Column(name = "province_seq", unique = false, nullable = false, insertable = true, updatable = true)
    private Integer provinceSeq;

    @Column(name = "province_bps_code", unique = true, nullable = true, insertable = true, updatable = true, length = 5)
    private String provinceBpsCode;

    @CreatedBy
    @Column(name = "province_created_by", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
    private String provinceCreatedBy;

    @CreatedDate
    @Column(name = "province_created_date", unique = false, nullable = false, insertable = true, updatable = true)
    private Date provinceCreatedDate;

    @LastModifiedBy
    @Column(name = "province_updated_by", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
    private String provinceUpdatedBy;

    @LastModifiedDate
    @Column(name = "province_updated_date", unique = false, nullable = true, insertable = true, updatable = true)
    private Date provinceUpdatedDate;

    public Province(ProvinceRequest model) {
        BeanUtils.copyProperties(model, this);
    }
}
