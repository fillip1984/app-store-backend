package org.home.knowledge.appstore.model.spec;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AbstractEntity {

    // when using BeanUtils to copy over fields, these fields can be supplied so
    // that we ignore them as ui shouldn't be touching these
    public static final String[] AUDIT_FIELDS = new String[] { "id", "created", "createdBy", "updated", "updatedBy" };

    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    @NotNull
    private LocalDateTime created;

    @CreatedBy
    @Size(max = 20)
    private String createdBy;

    @LastModifiedDate
    @NotNull
    private LocalDateTime updated;

    @LastModifiedBy
    @Size(max = 20)
    private String updatedBy;

}
