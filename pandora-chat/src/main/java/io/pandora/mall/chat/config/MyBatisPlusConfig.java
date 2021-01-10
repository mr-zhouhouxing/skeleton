package io.pandora.mall.chat.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Created by mr_zhou on 2021/1/6
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"io.pandora.mall.mapper.**"})
public class MyBatisPlusConfig {
    /** 乐观锁 插件 */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){ return new OptimisticLockerInterceptor(); }

    /** 分页插件 */
    @Bean
    public PaginationInterceptor paginationInterceptor(){ return new PaginationInterceptor(); }
}
