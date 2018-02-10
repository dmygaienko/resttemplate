package com.mygaienko.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeEntity {

    private Long id;

    private String key;

    private String value;

    public AttributeEntity(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
