/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletView;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.LoaderSQL;

/**
 *
 * @author Кристина
 */
@WebServlet(name = "ServletChange", urlPatterns = {"/ServletChange"})
public class ServletChange extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private LoaderSQL notif = new LoaderSQL();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str1 = req.getParameter("submit").substring(1);
        String name = req.getParameter("name");
        String desc = req.getParameter("desc");
        String conc = req.getParameter("conc");
        String time = req.getParameter("time");
        Locale.setDefault(Locale.ENGLISH);
        char s = req.getParameter("submit").charAt(0);
        switch (s) {
            case 'c':
                try {
                    notif.changeDataInTableTask(str1, name, time, conc, desc);
                } catch (Exception e) {
                    throw new ServletException("Error during task creation", e);
                }
                req.getRequestDispatcher("taskManager.jsp").forward(req, resp);
                break;
            case 'd':
                String id = req.getParameter("submit");
                 {
                    try {
                        notif.deleteDataInTableTask(id);
                    } catch (SQLException | NamingException ex) {
                        Logger.getLogger(ServletChange.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        }
    }
}
