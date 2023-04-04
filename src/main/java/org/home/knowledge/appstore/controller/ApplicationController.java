package org.home.knowledge.appstore.controller;

import java.util.List;

import org.home.knowledge.appstore.model.Application;
import org.home.knowledge.appstore.model.dto.ApplicationSummary;
import org.home.knowledge.appstore.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
@Slf4j
public class ApplicationController {

    private final ApplicationService applicationService;

    // create
    @PostMapping
    public ResponseEntity<Application> save(@RequestBody Application application) {
        log.info("Saving application: {}", application);
        return ResponseEntity.ok(applicationService.save(application));
    }

    @GetMapping
    public ResponseEntity<List<ApplicationSummary>> findAll(@RequestParam(defaultValue = "") String q) {
        log.info("Retrieving all applications for query: {}", q);
        return ResponseEntity.ok(applicationService.findAll(q));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> findById(@PathVariable Long id) {
        log.info("Retrieving application by id: {}", id);
        return ResponseEntity.ok(applicationService.findById(id));
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<Application> update(@PathVariable Long id, @RequestBody Application application) {
        log.info("Updating application: {}", application);

        // limit which fields can be edited
        var existingApplication = applicationService.findById(id);
        existingApplication.setName(application.getName());
        existingApplication.setDescription(application.getDescription());
        return ResponseEntity.ok(applicationService.save(existingApplication));
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        log.info("Deleting application by id: {}", id);
        return ResponseEntity.ok(applicationService.deleteById(id));
    }
}