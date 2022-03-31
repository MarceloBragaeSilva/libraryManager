package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.IEmpruntService;
import com.ensta.librarymanager.service.IMembreService;
import com.ensta.librarymanager.service.MembreService;

@WebServlet("/membre_details")
public class MembreDetailsServlet extends HttpServlet{
    
    IEmpruntService empruntService = EmpruntService.getInstance();
    IMembreService membreService = MembreService.getInstance();
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try {
            request.setAttribute("list_currentMembre", empruntService.getListCurrentByMembre(Integer.parseInt(request.getParameter("id"))));
            request.setAttribute("membre", membreService.getById(Integer.parseInt(request.getParameter("id"))));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            //Mise a jour du membre
            Membre membre_change = membreService.getById(Integer.parseInt(request.getParameter("id")));
            
            membre_change.setPrenom(request.getParameter("prenom"));
            membre_change.setNom(request.getParameter("nom"));
            membre_change.setEmail(request.getParameter("email"));
            membre_change.setTelephone(request.getParameter("telephone"));

            if (request.getParameter("abonnement").equals("BASIC"))
                membre_change.setAbonnement(Abonnement.BASIC);
            else if (request.getParameter("abonnement").equals("PREMIUM"))
                membre_change.setAbonnement(Abonnement.PREMIUM);
            else if (request.getParameter("abonnement").equals("VIP"))
                membre_change.setAbonnement(Abonnement.VIP);
            membreService.update(membre_change);

            request.setAttribute("id_m", membre_change.getKey());
            request.setAttribute("list_currentMembre", empruntService.getListCurrentByMembre(membre_change.getKey()));

            response.sendRedirect(request.getContextPath() + "/membre_details?id=" + membre_change.getKey());

        } catch(Exception e){
            e.printStackTrace();
            new ServletException("Could not update Member.", e);
        }
    }
}
