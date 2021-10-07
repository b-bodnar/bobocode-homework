package com.bobocode.socket.com.bobcode;

import lombok.SneakyThrows;

import java.io.*;
import java.net.ServerSocket;

public class Server {
    @SneakyThrows
    public static void main(String[] args) {
        try (var server = new ServerSocket(8000)) {
            while (true) {
                try (var clientSocket = server.accept()) {

                    var reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    var writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));

                    var message = reader.readLine();
                    var clientAddress = clientSocket.getInetAddress().getHostAddress();
                    System.out.println(clientAddress + " - " + message);

                    writer.println("Hi! " + clientAddress);
                    writer.flush();

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
