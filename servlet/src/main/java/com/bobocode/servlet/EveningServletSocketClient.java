package com.bobocode.servlet;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class EveningServletSocketClient {

    @SneakyThrows
    public static void main(String[] args) {
        var localhost = InetAddress.getLocalHost().getHostAddress();
        try (var socket = new Socket(localhost, 8080);
             var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             var writer = new PrintWriter(socket.getOutputStream())) {

            // through parameter, we pass name ?name=BUDDY
            writer.println("GET /evening HTTP/1.1");
            writer.println("Host: " + localhost + ":8080");

            // in response, we get session id and add it to next request.
            // Tomcat finds this cookie in session and add name from session in response
            writer.println("Cookie: JSESSIONID=36C46EC98A1BAE77AA95A9F4451D9232");

            writer.println();
            writer.flush();

            reader.lines().forEach(System.out::println);
        }
    }
}
