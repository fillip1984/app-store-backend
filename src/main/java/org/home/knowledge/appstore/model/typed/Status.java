package org.home.knowledge.appstore.model.typed;

import org.home.knowledge.appstore.model.typed.spec.EnumUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Status {
    BACKLOG("Backlog"), IN_PROGRESS("In progress"), COMPLETE("Complete");
    // Backlog, In_Progress, Complete;

    @JsonCreator
    Status getEnum(String value) {
        return EnumUtils.findEnumInsensitiveCase(this.getDeclaringClass(), value);
    }

    @Getter
    @JsonValue
    String value;

}
