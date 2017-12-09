package com.mygaienko.api.dto;

import org.springframework.web.multipart.MultipartFile;

public class FileRequest {

    private String systemName;

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
