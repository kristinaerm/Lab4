/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletView;

import exceptions.InvalidRecordFieldException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DataCheck;
import model.LoaderSQL;
import model.Record;

/**
 *
 * @author USER
 */
@WebServlet(name = "ServletTaskManager", urlPatterns = {"/ServletTaskManager"})
public class ServletTaskManager extends HttpServlet {

    private final LoaderSQL service = new LoaderSQL();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NamingException, SQLException, InvalidRecordFieldException, ParseException {
        Record r;
        char s = request.getParameter("submit").charAt(0);

        switch (s) {
            case 'a':
                try {
                    String nn = request.getParameter("name");
                    String dd = request.getParameter("descr");
                    String tt = request.getParameter("time");
                    String cc = request.getParameter("cont");
                    r = new Record(nn, dd, tt, cc);
                    DataCheck.timeCheck(r.getTimeString());
                    LinkedList<Record> list;
                    list = new LoaderSQL().selectInTableTask();
                    int i = 0;
                    while ((i < list.size())&&(!list.get(i).getName().equals(r.getName()))&&(!list.get(i).getDescription().equals(r.getDescription()))&&(!list.get(i).getContacts().equals(r.getContacts()))&&(!list.get(i).getTimeString().equals(r.getTimeString()))){
                        i++;
                    }
                    if (i==list.size()){
                        service.addDataInTableTask(r.getId(), r.getName(), r.getTimeString(), r.getContacts(), r.getDescription());
                    }
                } catch (InvalidRecordFieldException | SQLException | ParseException | NamingException ex) {
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                break;

            case 'd':
                try {
                    String str = request.getParameter("submit").substring(1);
                    request.setAttribute("d", "d");
                    service.deleteDataInTableTask(str);
                } catch (SQLException | NamingException e) {
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                break;
            
            case 'c':
                try {
                    String str1 = request.getParameter("submit").substring(1);
                    request.setAttribute("id", str1);
                    request.getRequestDispatcher("change.jsp").forward(request, response);
                } catch (IOException | ServletException e) {
                    request.getRequestDispatcher("taskManager.jsp").forward(request, response);
                }
                break;

            default:
                break;
        }
        request.getRequestDispatcher("taskManager.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(ServletTaskManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServletTaskManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidRecordFieldException ex) {
            Logger.getLogger(ServletTaskManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ServletTaskManager.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(ServletTaskManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServletTaskManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidRecordFieldException ex) {
            Logger.getLogger(ServletTaskManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ServletTaskManager.class.getName()).log(Level.SEVERE, null, ex);
        }
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
