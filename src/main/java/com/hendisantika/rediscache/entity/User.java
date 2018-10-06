package com.hendisantika.rediscache.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Project : redis-cache
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 24/12/17
 * Time: 21.11
 * To change this template use File | Settings | File Templates.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 4714012106558852332L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
}

