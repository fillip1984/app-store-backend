package org.home.knowledge.appstore.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.home.knowledge.appstore.model.spec.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Application extends AbstractEntity {

    @NotNull
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 1000)
    private String description;

}