package com.mygaienko.api.dto;

public class ErrorResponse {

    private String service;
    private String errorMsg;

    public ErrorResponse(String service, String errorMsg) {
        this.service = service;
        this.errorMsg = errorMsg;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getService() {
        return service;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
