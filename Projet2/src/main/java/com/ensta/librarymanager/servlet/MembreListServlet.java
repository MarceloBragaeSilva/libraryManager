package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.service.IMembreService;
import com.ensta.librarymanager.service.MembreService;

@WebServlet("/membre_list")
public class MembreListServlet extends HttpServlet{

    IMembreService membreService = MembreService.getInstance();

    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        try {
            request.setAttribute("list_membre", membreService.getList());
            
        }catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_list.jsp");
        dispatcher.forward(request, response);
    }
    
}
