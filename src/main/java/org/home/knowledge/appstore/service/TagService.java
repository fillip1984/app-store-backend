package org.home.knowledge.appstore.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.home.knowledge.appstore.model.Tag;
import org.home.knowledge.appstore.model.dto.TagSummary;
import org.home.knowledge.appstore.repository.TagRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagService {

    private final TagRepository tagRepository;

    // create
    public Tag save(Tag tag) {
        log.info("Saving tag: {}", tag);
        return tagRepository.save(tag);
    }

    public List<Tag> saveAll(List<Tag> tags) {
        log.info("Saving all tags. Count: {}", tags.size());
        return tagRepository.saveAll(tags);
    }

    // read
    public List<TagSummary> findAll(String q) {
        log.info("Retrieving all tags for query: {}", q);
        return tagRepository.findByNameContainingIgnoreCase(q,
                Sort.by(Sort.Order.asc("name").ignoreCase()));
    }

    public Tag findById(Long id) {
        log.info("Retrieving tag by id: {}", id);
        return tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find tag by id: " + id));
    }

    public long count() {
        log.info("Retrieving tag count");
        return tagRepository.count();
    }

    // update
    public Tag update(Tag tag) {
        log.info("Updating tag: {}", tag);
        return tagRepository.save(tag);
    }

    // delete
    public Long deleteById(Long id) {
        log.info("Deleting tag by id: {}", id);
        tagRepository.deleteById(id);
        return id;
    }
}
