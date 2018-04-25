/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.util.LinkedList;
import model.Record;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;

/**
 *
 * @author USER
 */
public class NotificationTimerTasks extends TimerTask {

    private int num;
    private LinkedList<Record> records;
    HttpServletRequest request;
    HttpServletResponse response;

    public NotificationTimerTasks(int nu, LinkedList<Record> records,HttpServletRequest req,HttpServletResponse resp) {
        num = nu;
        this.records = records;
        request=req;
        response=resp;
    }

    @Override
    public void run() {
       
        for (int i = 0; i < num; i++) {
            try {
                request.setAttribute("name", records.get(i).getName());
                request.setAttribute("id", records.get(i).getId());
                request.setAttribute("time", records.get(i).getTimeString());
                request.setAttribute("desc", records.get(i).getDescription());
                request.setAttribute("conc", records.get(i).getContacts());
                request.getRequestDispatcher("notification.jsp").forward(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(NotificationTimerTasks.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NotificationTimerTasks.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }


