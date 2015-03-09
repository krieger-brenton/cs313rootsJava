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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Brenton
 */
@WebServlet(name = "PostComment", urlPatterns = {"/PostComment"})
public class PostComment extends HttpServlet {

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
        if (request.getParameter("comment") != null)
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("commentInfo.txt", true));
            Comment comment = new Comment(request.getParameter("comment"), (String)request.getSession().getAttribute("name"));
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy 'at' HH:mm:ss");
            writer.write(comment.getComment() + "/endC" + comment.getUser() + "/endC" + sdf.format(comment.getDate()) + "\n");
            writer.close();
        }
        
        Stack commentsR = new Stack();
        List<Comment> comments = new ArrayList<Comment>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("commentInfo.txt"));
            String line;
            
            while ((line = reader.readLine()) != null) 
            {
                Comment comment = new Comment();
                comment.getComment(line);
                commentsR.push(comment);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        while(!commentsR.empty())
        {
            comments.add((Comment)commentsR.pop());
        }
        
        request.setAttribute("comments", comments);
        
        request.getRequestDispatcher("ViewPosts.jsp").forward(request, response);
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
