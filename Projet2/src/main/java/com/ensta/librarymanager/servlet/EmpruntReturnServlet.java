package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.IEmpruntService;

@WebServlet("/emprunt_return")
public class EmpruntReturnServlet extends HttpServlet{

    IEmpruntService empruntService = EmpruntService.getInstance();

    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        try {
            request.setAttribute("list_currentEmprunt", empruntService.getListCurrent());

        } catch (ServiceException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_return.jsp");
        dispatcher.forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            empruntService.returnBook(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("list_currentEmprunt", empruntService.getListCurrent());
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Could not return Book.");
        }
        response.sendRedirect(request.getContextPath() + "/emprunt_list");
    }
}
