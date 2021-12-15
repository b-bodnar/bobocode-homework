package com.github.bbodnar;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.stream.StreamSupport;

public class JacksonTreeModel {
    @SneakyThrows
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = """
                {
                    "name":"Mahesh Kumar",
                    "age":21,
                    "verified":false,
                    "marks": 
                        [
                            100,
                            90,
                            85
                        ]
                }
                """;

        JsonNode rootNode = mapper.readTree(jsonString);

        var name = rootNode.path("name").textValue();
        System.out.println("name:" + name);

        var age = rootNode.path("age").intValue();
        System.out.println("age:" + age);

        var verified = rootNode.path("verified").booleanValue() ? "yes" : "no";
        System.out.println("verified:" + verified);

        var marks = rootNode.path("marks").elements();
        System.out.print("marks: [ ");
        while (marks.hasNext()){
            System.out.print( marks.next().intValue() + " ");
        }
        System.out.println("]");


    }
}
