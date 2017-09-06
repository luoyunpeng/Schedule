package org.schedule;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScheduleApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ScheduleApplication.class, args);
    }

    // this will configure tomcat jdbc in java code
    // @Bean
    public DataSource dataSource() {
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setUrl("jdbc:mysql://47.94.140.6:3306/testload?characterEncoding=utf8");
        poolProperties.setUsername("root");
        poolProperties.setPassword("123");
        poolProperties.setDriverClassName("com.mysql.jdbc.Driver");
        poolProperties.setDefaultAutoCommit(true);
        poolProperties.setInitialSize(3);
        poolProperties.setMinIdle(3);
        poolProperties.setMaxIdle(10);
        poolProperties.setMaxActive(10);
        poolProperties.setMaxWait(10000);
        poolProperties.setTestOnBorrow(true);
        poolProperties.setTestWhileIdle(true);
        poolProperties.setValidationQuery("SELECT 1");
        poolProperties.setValidationQueryTimeout(3);
        poolProperties.setTimeBetweenEvictionRunsMillis(10000);
        poolProperties.setMinEvictableIdleTimeMillis(120000);
        poolProperties.setRemoveAbandoned(true);
        poolProperties.setRemoveAbandonedTimeout(60);
        return new DataSource(poolProperties);
    }
}
