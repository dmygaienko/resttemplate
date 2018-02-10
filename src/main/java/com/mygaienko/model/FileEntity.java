package com.mygaienko.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {

    private String fileName;

    private String key1;

    private String key2;

    private SignEntity sign;

    private FakeSign fakeSign;

    private List<AttributeEntity> attributes;

    public FileEntity(String fileName, String key1, String key2) {
        this.fileName = fileName;
        this.key1 = key1;
        this.key2 = key2;
    }

    public FileEntity(String fileName, String key1, String key2, SignEntity sign, List<AttributeEntity> attributes) {
        this.fileName = fileName;
        this.key1 = key1;
        this.key2 = key2;
        this.sign = sign;
        this.attributes = attributes;
    }
}
