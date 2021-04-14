package com.schoolms.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.github.pagehelper.PageInterceptor;
@Configuration
public class MybatisPlusPageHelperConfig {
	 //分页拦截器



    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }


}
