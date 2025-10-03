package com.test.Dto;

public class LoginResponse {
    private String token;
    private String tipo = "Bearer";
    private String email;

    public LoginResponse(String token, String email) {
        this.token = token;
        this.email = email;
    }

    public String getToken() { return token; }
    public String getTipo() { return tipo; }
    public String getEmail() { return email; }
}
