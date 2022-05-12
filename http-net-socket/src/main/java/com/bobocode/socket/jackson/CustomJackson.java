package com.bobocode.socket.jackson;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * This class shell work like an ObjectMapper with different generic classes
 */
public class CustomJackson {
    //TODO Parse json to map and then create object and set fields with data from map
    @SneakyThrows
    public static void main(String[] args) {
        var json = """
                {
                  "firstName": "Bohdan",
                  "lastName": "Bodnar",
                  "city": "Lviv",
                  "email": "iloveyoulviv@gmail.com"
                }               
                  """;

        var user = jsonToObject(json, User.class);
        System.out.println(user);
    }

    @SneakyThrows
    private static <T> T jsonToObject(String json, Class<T> c) {
        var obj = c.getConstructor().newInstance();
        var fields = c.getDeclaredFields();
        var fieldsValues = parseJsonToMap(json);
        for (Field field : fields) {
            field.setAccessible(true);
            var fieldName = field.getName();
            field.set(obj, fieldsValues.get(fieldName));
        }
        return obj;
    }

    private static Map<String, String> parseJsonToMap(String json) {
        return Arrays.stream(json.replaceAll("[}{\"]", "").split(","))
                .map(String::strip)
                .collect(toMap(
                        pair -> pair.substring(0, pair.indexOf(":")),
                        pair -> pair.substring(pair.indexOf(":") + 1).strip()));
    }
}
