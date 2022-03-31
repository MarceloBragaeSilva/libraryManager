package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.service.ILivreService;
import com.ensta.librarymanager.service.LivreService;

@WebServlet("/livre_add")
public class LivreAddServlet extends HttpServlet{

    ILivreService livreService = LivreService.getInstance();
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_add.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            int id_l = livreService.create(request.getParameter("titre"), request.getParameter("auteur"), request.getParameter("isbn"));

            response.sendRedirect(request.getContextPath() + "/livre_details?id="+id_l);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
