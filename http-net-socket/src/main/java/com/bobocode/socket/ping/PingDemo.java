package com.bobocode.socket.ping;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;

public class PingDemo {
    public static final String HOST = "93.175.204.87";
    public static final int PORT = 8080;

    @SneakyThrows
    public static void main(String[] args) {

        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(new Person("Bohdan", "Bodnar"));

        try (var socket = new Socket(HOST, PORT);
             var writer = new PrintWriter(socket.getOutputStream())) {

            writer.println("POST /ping HTTP/1.1");
            writer.println("Host: " + HOST + ":" + PORT);
            writer.println("Content-Type: application/json");
            writer.println("Content-Length: " + json.length());
            writer.println();
            writer.println(json);
            writer.flush();
        }
    }
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class Person implements Serializable {
    private String firstName;
    private String lastName;
}
