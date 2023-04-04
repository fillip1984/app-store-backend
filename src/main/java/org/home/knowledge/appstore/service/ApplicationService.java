package org.home.knowledge.appstore.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.home.knowledge.appstore.model.Application;
import org.home.knowledge.appstore.model.dto.ApplicationSummary;
import org.home.knowledge.appstore.repository.ApplicationRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    // create
    public Application save(Application application) {
        log.info("Saving application: {}", application);
        return applicationRepository.save(application);
    }

    public List<Application> saveAll(List<Application> applications) {
        log.info("Saving all applications. Count: {}", applications.size());
        return applicationRepository.saveAll(applications);
    }

    // read
    public List<ApplicationSummary> findAll(String q) {
        log.info("Retrieving all applications for query: {}", q);
        return applicationRepository.findByNameContainingIgnoreCase(q,
                Sort.by(Sort.Order.asc("name").ignoreCase()));
    }

    public Application findById(Long id) {
        log.info("Retrieving application by id: {}", id);
        return applicationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find application by id: " + id));
    }

    public long count() {
        log.info("Retrieving application count");
        return applicationRepository.count();
    }

    // update
    public Application update(Application application) {
        log.info("Updating application: {}", application);
        return applicationRepository.save(application);
    }

    // delete
    public Long deleteById(Long id) {
        log.info("Deleting application by id: {}", id);
        applicationRepository.deleteById(id);
        return id;
    }
}
