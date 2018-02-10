package com.mygaienko.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {

    private String fileName;

    private String key1;

    private String key2;

    private String sign;

    private List<AttributeDto> attributes;

    public FileDto(String fileName, String key1, String key2) {
        this.fileName = fileName;
        this.key1 = key1;
        this.key2 = key2;
    }
}
