package com.example.config;

import com.example.dao.DaoImpl;
import com.example.dao.DaoImpl2;
import com.example.dao.DaoFile;
import com.example.dao.DaoApi;
import com.example.dao.IDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:app.properties")
public class PropertyDrivenConfig {

    @Value("${dao.target:dao}")
    private String target;

    @Bean(name = "dao")
    @DependsOn("propertySourcesPlaceholderConfigurer")
    public IDao selectedDao() {
        return switch (target) {
            case "dao" -> new DaoImpl();
            case "dao2" -> new DaoImpl2();
            case "daoFile" -> new DaoFile();
            case "daoApi" -> new DaoApi();
            default -> throw new IllegalArgumentException("Implémentation inconnue: " + target + " (dao|dao2|daoFile|daoApi)");
        };
    }

    // Résolution des placeholders @Value sans Spring Boot
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
