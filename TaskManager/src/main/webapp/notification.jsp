<%-- 
    Document   : notification
    Created on : 16.03.2018, 21:59:43
    Author     : Кристина
--%>

<%@page import="model.NotificationTimerTasks"%>
<%@page import="model.LoaderSQL"%>
<%@page import="java.util.LinkedList"%>
<%@page import="model.Record"%>
<%@page import="java.util.Timer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="notific.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="p" method="post">
              <%
                String s =request.getAttribute("id").toString();  
               // Record rec =(Record)request.getAttribute("id");  
                Record  r = new LoaderSQL().selectTask(s);
            %>       
            <p>Дата и время:
                <input type="text" maxlength="25" size="25" id="txt" name="time"/></p>
            <p>Название:
                <input type="text" maxlength="25" size="30" id="txt1" name="name"/></p>
            <p>Описание:
                <input type="text" maxlength="30" size="30" id="txt2" name="desc"/></p>
            <p>Контакты:
                <input type="text" maxlength="40" size="40" id="txt3" name="conc"/></p>
            <h4> <button value="d" name = "submit">Выполнить</button></h4>
            <h3><input type="text" maxlength="40" size="25" id="txt4" name="tch"/></h3>
            <h5><button value="c" name = "submit">Отложить на  другое время</button></h5>
        </form >

    </body>
</html>
