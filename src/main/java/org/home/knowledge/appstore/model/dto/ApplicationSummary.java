package org.home.knowledge.appstore.model.dto;

import org.home.knowledge.appstore.model.dto.spec.AbstractSummary;

public interface ApplicationSummary extends AbstractSummary {

    String getName();

    String getDescription();

    String getRepositoryUrl();

}
