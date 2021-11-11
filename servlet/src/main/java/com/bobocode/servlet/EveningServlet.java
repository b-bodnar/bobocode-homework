package com.bobocode.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.util.Optional;

@WebServlet("/evening")
public class EveningServlet extends HttpServlet {

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        var session = req.getSession();
        var sessionName = Optional.ofNullable((String) session.getAttribute("name"));
        var requestName = Optional.ofNullable(req.getParameter("name"));

        requestName.filter(name -> sessionName.isEmpty())
                .ifPresent(name -> session.setAttribute("name", name));

        var name = requestName.or(() -> sessionName)
                .orElse("incognito");

        var writer = resp.getWriter();
        writer.println("Good evening " + name);
        writer.flush();
    }
}
