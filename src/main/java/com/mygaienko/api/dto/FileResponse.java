package com.mygaienko.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("File Response")
public class FileResponse {

    @ApiModelProperty
    private String result;

    public FileResponse() {
    }

    public FileResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "FileResponse{" +
                "result='" + result + '\'' +
                '}';
    }
}
