package com.mdbytes.app.rest.config;

import com.mdbytes.app.entity.Alert;
import com.mdbytes.app.entity.Product;
import com.mdbytes.app.entity.Store;
import com.mdbytes.app.entity.User;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

@Component
public class RestConfig {

    @Autowired
    private EntityManager entityManager;

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return RepositoryRestConfigurer.withConfig(config -> config.exposeIdsFor(Alert.class, User.class, Product.class, Store.class));
    }
}
