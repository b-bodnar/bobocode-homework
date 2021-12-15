package com.bobocode.socket.jackson;

/**
 * This class shell work like an ObjectMapper with different generic classes
 */
public class CustomJackson {
//TODO Parse json to map and then create object and set fields with data from map

    public static void main(String[] args) {
        var json = """
                {
                  "firstName": "Bohdan",
                  "lastName": "Bodnar",
                  "email": "iloveyoulviv@gmail.com"
                }               
                  """;

        var user = jsonToObject(json, User.class);
    }

    private static <T> T jsonToObject(String json, Class<T> c) {
        return null;
    }

    static class User {
        String firstName;
        String lastName;
        String email;
    }
}
