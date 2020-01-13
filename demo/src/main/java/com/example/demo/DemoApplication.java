package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/*
     * SqlSessionFactory 설정 
     */
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
//        
//        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        
//        sessionFactory.setDataSource(dataSource);
//        return sessionFactory.getObject();
//        
//    }


}
