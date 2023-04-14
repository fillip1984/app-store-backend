package org.home.knowledge.appstore.model.typed;

import org.home.knowledge.appstore.model.typed.spec.EnumUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Category {
    FINANCE("Finance"),
    KNOWLEDGE("Knowledge"),
    PRODUCTIVITY("Productivity"),
    TOOLING("Tooling"),
    TUTORIAL("Tutorial"),
    UNCATEGORIZED("Uncategorized");

    @JsonCreator
    Category getEnum(String value) {
        return EnumUtils.findEnumInsensitiveCase(this.getDeclaringClass(), value);
    }

    @Getter
    @JsonValue
    String value;

}
