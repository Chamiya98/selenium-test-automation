package com.prodoscore.pages.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prodoscore.pages.models.LoginData;


import java.io.File;
import java.io.IOException;

public class JsonDataReader {

    public static Object[][] getLoginData() {

        ObjectMapper mapper = new ObjectMapper();

        try {
            LoginData[] data = mapper.readValue(
                    new File("src/test/resources/loginData.json"),
                    LoginData[].class
            );

            Object[][] result = new Object[data.length][1];

            for (int i = 0; i < data.length; i++) {
                result[i][0] = data[i];
            }

            return result;

        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file", e);
        }
    }

}
