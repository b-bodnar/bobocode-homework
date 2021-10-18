package com.bobocode.servlet.item_4;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.stream.Collectors;

@WebServlet("/message")
public class MessageServlet extends HttpServlet {

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        var reader = req.getReader();
        var mapper = new ObjectMapper();

        var json = reader.lines().collect(Collectors.joining("\n"));
        var message = mapper.readValue(json, Message.class);

        System.out.println(message);
    }
}

@Data
@NoArgsConstructor
class Message {
    private String name;
    private String message;

    @Override
    public String toString() {
        return name + ": " + message;
    }
}
