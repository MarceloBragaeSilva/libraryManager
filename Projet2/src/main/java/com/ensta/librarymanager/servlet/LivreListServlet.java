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

@WebServlet("/livre_list")
public class LivreListServlet extends HttpServlet{
    
    ILivreService livreService = LivreService.getInstance();

    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        try {
            request.setAttribute("list_livre", livreService.getList());
            
        }catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_list.jsp");
        dispatcher.forward(request, response);
    }
}
