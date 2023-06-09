package org.home.knowledge.appstore;

import org.home.knowledge.appstore.security.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring Data Jpa configuration
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class PersistenceConfig {
    // enables access to Spring Security context so that Spring Data JPA @CreatedBy
    // and @LastModifiedBy annotated fields can pickup user details when auditing
    // operations such as insert, update, etc
    @Bean
    AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }
}
