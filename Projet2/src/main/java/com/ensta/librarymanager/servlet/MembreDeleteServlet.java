package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.IMembreService;
import com.ensta.librarymanager.service.MembreService;

@WebServlet("/membre_delete")
public class MembreDeleteServlet extends HttpServlet{
    
    IMembreService membreService = MembreService.getInstance();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try {
            if (request.getParameter("id") != null){
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("id", id);
                request.setAttribute("membre", membreService.getById(id));
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_delete.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            membreService.delete(Integer.parseInt(request.getParameter("id")));

            response.sendRedirect(request.getContextPath() + "/membre_list");
        } catch(Exception e){
            e.printStackTrace();
            throw new ServletException("Could not delete Member.");
        }
    }
}
