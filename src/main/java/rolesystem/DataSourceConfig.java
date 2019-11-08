package rolesystem;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource datasource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://database-1.chjygaawk2tq.us-east-2.rds.amazonaws.com/authdb")
                .username("postgres")
                .password("fedora123")
                .build();
    }
}
