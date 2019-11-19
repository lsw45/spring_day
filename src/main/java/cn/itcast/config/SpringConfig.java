package cn.itcast.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "cn.itcast")
@Import(JdbcConfig.class)
public class SpringConfig {
}
