package com.github.bbodnar;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class JacksonSimpleDataBinding {
    @SneakyThrows
    public static void main(String[] args) {
        var mapper = new ObjectMapper();
        String jsonString = """
                {
                    "name":"Mahesh",
                    "age":21
                }""";
        var student = mapper.readValue(jsonString, Student.class);
        System.out.println(student);

        jsonString = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(student);

        System.out.println(jsonString);

        // Simple data binding
        Map<String, Object> dataMap = new HashMap<>();
        int[] marks = {1, 2, 3};

        Student st = new Student("Mahesh", "Ndye", 10);
        // JAVA Object
        dataMap.put("student", student);
        // JAVA String
        dataMap.put("name", "Mahesh Kumar");
        // JAVA Boolean
        dataMap.put("verified", Boolean.FALSE);
        // Array
        dataMap.put("marks", marks);

        mapper.writeValue(new File("test.json"), dataMap);
        var readData = mapper.readValue(new File("test.json"), Map.class);

        System.out.println(readData.get("student"));
        System.out.println(readData.get("name"));
        System.out.println(readData.get("verified"));
        System.out.println(readData.get("marks"));
    }
}
