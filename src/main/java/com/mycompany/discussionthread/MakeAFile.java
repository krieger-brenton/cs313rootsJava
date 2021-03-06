/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.mycompany.discussionthread;

import classes.Comment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Brenton
 */
@WebServlet(name = "MakeAFile", urlPatterns = {"/MakeAFile"})
public class MakeAFile extends HttpServlet {
    
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
        String dataDirectory = System.getenv("OPENSHIFT_DATA_DIR");
        if (dataDirectory != null)
        {
            dataDirectory += "userInfo.txt";
        }
        else
        {
            dataDirectory = "userInfo.txt";
        }
        response.getWriter().write("Trying to write to: " + dataDirectory + '\n');
        BufferedWriter writer = new BufferedWriter(new FileWriter(dataDirectory, false));
        writer.write("Brent,test" + "\n");
        writer.write("Burton,test" + "\n");
        writer.close();
        response.getWriter().write("Trying to read\n");
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(dataDirectory));
            String line;
            
            while ((line = reader.readLine()) != null)
            {
                response.getWriter().write(line + "\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
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
