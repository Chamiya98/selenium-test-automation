package com.prodoscore.pages.models;

import lombok.Getter;

@Getter
public class LoginData {

    private String testCaseName;
    private String username;
    private String password;
    private String expectedResult;


    public LoginData() {
    }

    public LoginData(String testCaseName, String username, String password, String expectedResult) {
        this.testCaseName = testCaseName;
        this.username = username;
        this.password = password;
        this.expectedResult = expectedResult;
    }

    @Override
    public String toString() {
        return testCaseName;
    }
}