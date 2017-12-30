package com.mygaienko.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Generate File Request")
public class GenerateFileRequest {

    @ApiModelProperty
    private String fileName;

    @ApiModelProperty
    private String key1;

    @ApiModelProperty
    private String key2;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public String getKey2() {
        return key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    @Override
    public String toString() {
        return "GenerateFileRequest{" +
                "fileName='" + fileName + '\'' +
                ", key1='" + key1 + '\'' +
                ", key2='" + key2 + '\'' +
                '}';
    }
}
