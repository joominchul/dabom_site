package com.example.apt;

import com.example.apt.repository.MybatisAptRepository;
import com.example.apt.service.AptService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@EnableTransactionManagement
@MapperScan("com.example.apt.repository")
public class SpringConfig {
    private final MybatisAptRepository aptRepository;

    public SpringConfig(MybatisAptRepository aptRepository){
        this.aptRepository = aptRepository;
    }

    @Bean
    public AptService aptService(){
        return new AptService(aptRepository);
    }
}
