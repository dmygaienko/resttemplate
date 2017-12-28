package com.mygaienko.api.dto;

import java.util.Arrays;

public class DownloadFileResponse {

    private String fileName;
    private byte[] content;

    public DownloadFileResponse() {
    }

    public DownloadFileResponse(String fileName, byte[] content) {
        this.fileName = fileName;
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "DownloadFileResponse{" +
                "fileName='" + fileName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
