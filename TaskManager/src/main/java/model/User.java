/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.LinkedList;
import java.util.UUID;

/**
 *
 * @author USER
 */
//  проверочка
public class User {

    private final String id;
    private final String login;
    private final String password;
    private TaskLog taskLog;

    //убрать строку передачи айдишника!!!
    public User(String l, String p, LinkedList<Record> rec) {
        id = UUID.randomUUID().toString();
        login = l;
        password = p;
        taskLog = new TaskLog(rec);
    }
    
    public User(String i, String l, String p, LinkedList<Record> rec) {
        id = i;
        login = l;
        password = p;
        taskLog = new TaskLog(rec);
    }

    public void setTaskLog(TaskLog t) {
        taskLog = t;
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public TaskLog getTaskLog() {
        return taskLog;
    }

}
