package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.IEmpruntService;

@WebServlet("/emprunt_list")
public class EmpruntListServlet extends HttpServlet{
    
    IEmpruntService empruntService = EmpruntService.getInstance();
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        try {
            if(request.getParameterMap().containsKey("show") && request.getParameter("show").equals("all")){
                request.setAttribute("list_emprunt", empruntService.getList());
            }
            else{
                request.setAttribute("list_emprunt", empruntService.getListCurrent());
            }
            
        }catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp");
        dispatcher.forward(request, response);
    }

}
