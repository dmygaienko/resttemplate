package com.mygaienko.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

@ApiModel("File Request")
public class FileRequest {

    @ApiModelProperty
    private String systemName;

    @ApiModelProperty
    private MultipartFile file;

    public FileRequest(String systemName, MultipartFile file) {
        this.systemName = systemName;
        this.file = file;
    }

    public FileRequest() {
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
