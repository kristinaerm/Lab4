/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletView;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Locale;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import model.LoaderSQL;

/**
 *
 * @author Кристина
 */
@WebServlet(name = "ServletDelete", urlPatterns = {"/ServletDelete"})
public class ServletDelete extends HttpServlet {

    private LoaderSQL notif = new LoaderSQL();
   

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        
        String id = req.getParameter("id");
       
        
        try {
            notif.deleteDataInTableTask(id);

        } catch (Exception e) {

            throw new ServletException("Error during task creation", e);

        }

        resp.sendRedirect("/");

    }
        
    }

  

