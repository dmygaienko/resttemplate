package com.mygaienko.mapper;

import com.mygaienko.api.dto.AttributeDto;
import com.mygaienko.model.AttributeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AttributeMapper {

    AttributeDto toDto(AttributeEntity entity);
    AttributeEntity toEntity(AttributeDto dto);

}
