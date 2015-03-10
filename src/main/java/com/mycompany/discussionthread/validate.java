/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.discussionthread;

import classes.User;
import classes.UserFileHandler;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Brenton
 */
@WebServlet(name = "validate", urlPatterns = {"/validate"})
public class validate extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String name = request.getParameter("name");
        String password = request.getParameter("password");        
        
        //filereader from userInfo
        
        String dataDirectory = System.getenv("OPENSHIFT_DATA_DIR");
        if (dataDirectory != null)
        {
            dataDirectory += "userInfo.txt";
        }
        else
        {
            dataDirectory = "userInfo.txt";
        }
        
        UserFileHandler UFH = new UserFileHandler(dataDirectory);
        List<User> users = UFH.getUsers();
        boolean found = false;
        
        for(User user: users)
        {
            if (name.equals(user.getUserName()) && password.equals(user.getPassword()))
            {
                request.getSession().setAttribute("name", name);
                request.getSession().setAttribute("password", password);
                found = true;
                request.getRequestDispatcher("NewPost.jsp").forward(request, response);
                return;
            }
        }
         
        if(!found)
        {
           request.getRequestDispatcher("invalid.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
