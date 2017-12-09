package com.mygaienko.api.dto;


public class FileResponse {

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
