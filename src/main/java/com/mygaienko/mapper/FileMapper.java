package com.mygaienko.mapper;

import com.mygaienko.api.dto.FileDto;
import com.mygaienko.model.FileEntity;
import org.mapstruct.Mapper;

@Mapper
public interface FileMapper {

    FileDto toDto(FileEntity entity);
    FileEntity toEntity(FileDto dto);

}
