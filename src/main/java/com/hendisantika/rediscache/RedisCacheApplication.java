package com.hendisantika.rediscache;

import com.hendisantika.rediscache.entity.User;
import com.hendisantika.rediscache.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RedisCacheApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RedisCacheApplication.class);
    }


    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(RedisCacheApplication.class, args);
    }

    @Bean
    CommandLineRunner initData() {
        return args -> {
            userRepository.save(new User(1L, "Uzumaki Naruto", "123456"));
            userRepository.save(new User(2L, "Uchiha Sasuke", "123456"));
            userRepository.save(new User(3L, "Haruno Sakura", "123456"));
            userRepository.save(new User(4L, "Hatake Kakashi", "123456"));
            userRepository.save(new User(5L, "Sarutobi Hiruzen", "123456"));
            userRepository.save(new User(6L, "Sarutobi Asuma", "123456"));
            userRepository.save(new User(7L, "Sarutobi Asuma", "123456"));
            userRepository.save(new User(8L, "Minato Namikaze", "123456"));
            userRepository.save(new User(9L, "Uzumaki Kushina", "123456"));
            userRepository.save(new User(10L, "Yamanaka Ino", "123456"));

            for (int i = 0; i < 1_000; i++) {
                userRepository.save(new User((long) i, "User" + i, "123456"));
            }
        };
    }
}
