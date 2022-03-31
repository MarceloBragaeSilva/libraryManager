package com.ensta.librarymanager.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.IEmpruntService;
import com.ensta.librarymanager.service.ILivreService;
import com.ensta.librarymanager.service.IMembreService;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

@WebServlet("/emprunt_add")
public class EmpruntAddServlet extends HttpServlet{

    IEmpruntService empruntService = EmpruntService.getInstance();
    IMembreService membreService = MembreService.getInstance();
    ILivreService livreService = LivreService.getInstance();
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try {
            request.setAttribute("list_possMembre", membreService.getListMembreEmpruntPossible());
            request.setAttribute("list_dispoLivre", livreService.getListDispo());

        } catch (ServiceException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            if (request.getParameter("idMembre") == null || request.getParameter("idLivre") == null){
                throw new ServletException("Could not create loan. Member or/and Book not found.");
            }
            else{
                empruntService.create(Integer.parseInt(request.getParameter("idMembre")),
                    Integer.parseInt(request.getParameter("idLivre")), LocalDate.now());
                request.setAttribute("list_possMembre", membreService.getListMembreEmpruntPossible());
                request.setAttribute("list_dispoLivre", livreService.getListDispo());

                response.sendRedirect(request.getContextPath() + "/emprunt_list");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}