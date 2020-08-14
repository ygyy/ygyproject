package com.glodon.springboot.spring_boot_demo.servlet;

import org.springframework.context.annotation.Bean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "helloServlet",urlPatterns = "/helloServlet")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        System.out.println("执行了HelloServlet的doGet方法。。。。。。");
    }
}
