package com.mygaienko.mapper;

import com.mygaienko.api.dto.FileDto;
import com.mygaienko.model.FileEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = AttributeMapper.class)
public interface FileMapper {

    @Mappings(
            @Mapping(target = "sign", source = "sign.value"))
    FileDto toDto(FileEntity entity);

    /*@Mappings(
            @Mapping(target = "sign.value", source = "sign"))*/
    @InheritInverseConfiguration
    FileEntity toEntity(FileDto dto);

}
