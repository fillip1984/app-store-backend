package org.home.knowledge.appstore.repository;

import java.util.List;

import org.home.knowledge.appstore.model.Tag;
import org.home.knowledge.appstore.model.dto.TagSummary;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<TagSummary> findByNameContainingIgnoreCase(String q, Sort sort);
}
