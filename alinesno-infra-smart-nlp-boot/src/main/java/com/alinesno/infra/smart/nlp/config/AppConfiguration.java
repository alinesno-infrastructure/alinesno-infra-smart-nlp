package com.alinesno.infra.smart.nlp.config;

import com.alinesno.infra.common.facade.enable.EnableActable;
import com.alinesno.infra.common.web.adapter.sso.enable.EnableInfraSsoApi;
import com.alinesno.infra.common.web.log.aspect.LogAspect;
import com.dtflys.forest.springboot.annotation.ForestScan;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 */
@Slf4j
@EnableActable
@EnableInfraSsoApi
@MapperScan("com.alinesno.infra.smart.nlp.mapper")
@ForestScan({
        "com.alinesno.infra.common.web.adapter.base.consumer"
})
@Configuration
public class AppConfiguration implements CommandLineRunner  {

    @Override
    public void run(String... args) throws Exception {
        log.debug("AppConfiguration.run");
    }

    @Bean
    public LogAspect getLogAspect(){
        return new LogAspect() ;
    }

}
