package com.test.backend.entities.DTO;


import lombok.Data;

@Data
public class AuthResponseDto {
    private String accessToken;
    private String tokenType = "Bearer ";
    private int userid;
    public AuthResponseDto(String accessToken, int id) {
        this.accessToken = accessToken;
        this.tokenType = "Bearer";
        this.userid = id;
    }
}
