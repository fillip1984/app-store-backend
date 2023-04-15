package org.home.knowledge.appstore.controller;

import java.util.List;

import org.home.knowledge.appstore.model.Tag;
import org.home.knowledge.appstore.model.dto.TagSummary;
import org.home.knowledge.appstore.model.spec.AbstractEntity;
import org.home.knowledge.appstore.service.TagService;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
@Slf4j
public class TagController {

    private final TagService tagService;

    // create
    @PostMapping
    public ResponseEntity<Tag> save(@RequestBody Tag tag) {
        log.info("Saving tag: {}", tag);
        return ResponseEntity.ok(tagService.save(tag));
    }

    @GetMapping
    public ResponseEntity<List<TagSummary>> findAll(@RequestParam(defaultValue = "") String q) {
        log.info("Retrieving all tags for query: {}", q);
        return ResponseEntity.ok(tagService.findAll(q));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> findById(@PathVariable Long id) {
        log.info("Retrieving tag by id: {}", id);
        return ResponseEntity.ok(tagService.findById(id));
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<Tag> update(@PathVariable Long id, @RequestBody Tag tag) {
        log.info("Updating tag: {}", tag);

        // limit which fields can be edited
        var existingTag = tagService.findById(id);
        BeanUtils.copyProperties(tag, existingTag, AbstractEntity.AUDIT_FIELDS);
        return ResponseEntity.ok(tagService.save(existingTag));
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        log.info("Deleting tag by id: {}", id);
        return ResponseEntity.ok(tagService.deleteById(id));
    }
}