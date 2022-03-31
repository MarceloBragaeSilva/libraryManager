package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.service.IMembreService;
import com.ensta.librarymanager.service.MembreService;

@WebServlet("/membre_add")
public class MembreAddServlet extends HttpServlet{
    
    IMembreService membreService = MembreService.getInstance();
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_add.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try{
            int id_m = membreService.create(request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("adresse"),
                request.getParameter("email"), request.getParameter("telephone"), Abonnement.BASIC);

            response.sendRedirect(request.getContextPath() + "/membre_details?id="+id_m);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
