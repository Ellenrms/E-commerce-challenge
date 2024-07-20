package com.ellenmateus.ecommerce.dto;

public class DTOLoginResponse {

    private String token;

    public DTOLoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
