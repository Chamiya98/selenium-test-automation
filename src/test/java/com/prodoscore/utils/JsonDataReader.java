package com.prodoscore.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class JsonDataReader {

    public static Object[][] getLoginData() {

        try {
            ObjectMapper mapper = new ObjectMapper();

            InputStream is = JsonDataReader.class
                    .getClassLoader()
                    .getResourceAsStream("loginData.json");

            List<Map<String, String>> data =
                    mapper.readValue(is, new TypeReference<List<Map<String, String>>>() {});

            Object[][] result = new Object[data.size()][1];

            for (int i = 0; i < data.size(); i++) {
                result[i][0] = data.get(i);
            }

            return result;

        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON file", e);
        }
    }
}
