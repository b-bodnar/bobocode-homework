package com.bobocode.socket;

import lombok.SneakyThrows;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    @SneakyThrows
    public static void main(String[] args) {
        try (var client = new Socket(InetAddress.getLocalHost().getHostAddress(), 8000)) {
            var writer = new PrintWriter(
                    new BufferedWriter(new OutputStreamWriter(client.getOutputStream())));
            var reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            var message = "Hello!";
            writer.println(message);
            writer.flush();

            var answer = reader.readLine();
            System.out.println(answer);
        }
    }
}
