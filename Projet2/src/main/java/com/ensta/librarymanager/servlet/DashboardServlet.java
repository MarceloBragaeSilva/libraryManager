package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.*;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        IMembreService membreService = MembreService.getInstance();
        ILivreService livreService = LivreService.getInstance();
        IEmpruntService empruntService = EmpruntService.getInstance();

        try {
            request.setAttribute("count_livre", livreService.count());
            request.setAttribute("count_membre", membreService.count());
            request.setAttribute("count_emprunt", empruntService.count());
            request.setAttribute("list_currentEmprunt", empruntService.getListCurrent());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
