package cn.itcast.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.activation.DataSource;

public class JdbcConfig {



    @Bean(name = "dataSource")
    public DataSource createDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/chanzhi?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return (DataSource) dataSource;
    }
    @Bean(name = "jdbcTemplate")
    public JdbcTemplate createJdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate((javax.sql.DataSource) dataSource);
        return jdbcTemplate;
    }
}
