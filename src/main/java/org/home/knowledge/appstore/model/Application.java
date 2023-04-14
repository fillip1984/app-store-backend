package org.home.knowledge.appstore.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.home.knowledge.appstore.model.spec.AbstractEntity;
import org.home.knowledge.appstore.model.typed.Category;
import org.home.knowledge.appstore.model.typed.Status;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@EqualsAndHashCode(callSuper = true)
public class Application extends AbstractEntity {

    @NotNull
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 10, max = 500)
    private String description;

    private String repositoryUrl;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Category category;

}
