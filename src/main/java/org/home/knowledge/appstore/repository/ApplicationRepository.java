package org.home.knowledge.appstore.repository;

import java.util.List;

import org.home.knowledge.appstore.model.Application;
import org.home.knowledge.appstore.model.dto.ApplicationSummary;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<ApplicationSummary> findByNameContainingIgnoreCase(String q, Sort sort);
}
