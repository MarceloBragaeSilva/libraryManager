package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.IEmpruntService;
import com.ensta.librarymanager.service.ILivreService;
import com.ensta.librarymanager.service.LivreService;

@WebServlet("/livre_details")
public class LivreDetailsServlet extends HttpServlet{
    IEmpruntService empruntService = EmpruntService.getInstance();
    ILivreService livreService = LivreService.getInstance();
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try {
            request.setAttribute("list_currentEmprunt", empruntService.getListCurrentByLivre(Integer.parseInt(request.getParameter("id"))));
            request.setAttribute("livre", livreService.getById(Integer.parseInt(request.getParameter("id"))));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_details.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            //Mise a jour du livre
            Livre livre_change = livreService.getById(Integer.parseInt(request.getParameter("id")));
            
            livre_change.setAuteur(request.getParameter("auteur"));
            livre_change.setTitre(request.getParameter("titre"));
            livre_change.setIsbn(request.getParameter("isbn"));
            livreService.update(livre_change);

            request.setAttribute("id_l", livre_change.getKey());
            request.setAttribute("list_currentEmprunt", empruntService.getListCurrentByLivre(livre_change.getKey()));

            response.sendRedirect(request.getContextPath() + "/livre_details?id=" + livre_change.getKey());

        } catch(Exception e){
            e.printStackTrace();
            new ServletException("Could not update Book.", e);
        }
    }
}
