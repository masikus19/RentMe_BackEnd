package com.example.rentme_backend_morgan.transactions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;

//@Configuration
public class ConfTransactional {

//    @Bean(name = "jpatrans")
    public JpaTransactionManager jpaTransactionManagerOther() {
        return new JpaTransactionManager();
    }

}
