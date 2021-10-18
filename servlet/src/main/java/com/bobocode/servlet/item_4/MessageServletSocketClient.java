package com.bobocode.servlet.item_4;

import lombok.SneakyThrows;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MessageServletSocketClient {

    @SneakyThrows
    public static void main(String[] args) {

        var localhost = InetAddress.getLocalHost().getHostAddress();
        String jsonMessage = """
                {
                    "name": "Bohdan",
                    "message": "Hello!"
                }
                """;

        try (var socket = new Socket(localhost, 8080);
             var writer = new PrintWriter(socket.getOutputStream())) {

            writer.println("POST /message HTTP/1.1");
            writer.println("Host: " + localhost + ":8080");
            writer.println("Content-Type: application/json");
            writer.println("Content-Length: " + jsonMessage.length());
            writer.println();
            writer.println(jsonMessage);
            writer.flush();
        }
    }
}
