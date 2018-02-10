package com.mygaienko.mapper;

import com.mygaienko.api.dto.FileDto;
import com.mygaienko.model.FileEntity;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.Assert.*;

public class FileMapperTest {

    @Test
    public void toDto() {
        FileMapper mapper = Mappers.getMapper(FileMapper.class);
        FileDto actualDto = mapper.toDto(new FileEntity("testFileName", "key1", "key2"));
        assertEquals(new FileDto("testFileName", "key1", "key2"), actualDto);
    }

    @Test
    public void toEntity() {
    }

}