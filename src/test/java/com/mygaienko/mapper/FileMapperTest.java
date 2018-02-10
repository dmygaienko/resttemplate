package com.mygaienko.mapper;

import com.mygaienko.api.dto.AttributeDto;
import com.mygaienko.api.dto.FileDto;
import com.mygaienko.model.AttributeEntity;
import com.mygaienko.model.FileEntity;
import com.mygaienko.model.SignEntity;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FileMapperTest {

    private final FileMapper mapper = Mappers.getMapper(FileMapper.class);

    @Test
    public void testToDtoSimple() {
        FileDto actualDto = mapper.toDto(new FileEntity("testFileName", "key1", "key2"));
        assertEquals(new FileDto("testFileName", "key1", "key2"), actualDto);
    }

    @Test
    public void toEntity() {
        FileEntity actualEntity = mapper.toEntity(getDto());
        assertEquals(getEntity(), actualEntity);
    }

    @Test
    public void toDto() {
        FileDto actualDto = mapper.toDto(getEntity());
        assertEquals(getDto(), actualDto);
    }

    private FileDto getDto() {
        List<AttributeDto> attributes = Arrays.asList(new AttributeDto("key1", "value1"), new AttributeDto("key2", "value2"));
        return new FileDto("testFileName", "key1", "key2", "signValue", attributes);
    }

    private FileEntity getEntity() {
        List<AttributeEntity> attributes = Arrays.asList(new AttributeEntity("key1", "value1"), new AttributeEntity("key2", "value2"));
        return new FileEntity("testFileName", "key1", "key2", new SignEntity("signValue"), attributes);
    }

}