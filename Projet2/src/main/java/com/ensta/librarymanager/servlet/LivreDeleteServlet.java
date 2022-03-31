package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.ILivreService;
import com.ensta.librarymanager.service.LivreService;

@WebServlet("/livre_delete")
public class LivreDeleteServlet extends HttpServlet {
    
    ILivreService livreService = LivreService.getInstance();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try {
            if (request.getParameter("id") != null){
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("id", id);
                request.setAttribute("livre", livreService.getById(id));
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_delete.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            livreService.delete(Integer.parseInt(request.getParameter("id")));

            response.sendRedirect(request.getContextPath() + "/livre_list");
        } catch(Exception e){
            e.printStackTrace();
            throw new ServletException("Could not delete Book.");
        }
    }

}
