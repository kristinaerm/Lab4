<%-- 
    Document   : change
    Created on : 17.03.2018, 22:30:39
    Author     : Кристина
--%>

<%@page import="model.Record"%>
<%@page import="model.LoaderSQL"%>
  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Изменить запись:</h1>
        <form action="change" method="post">
            <%
                String s =request.getAttribute("id").toString();  
               // Record rec =(Record)request.getAttribute("id");  
                Record  r = new LoaderSQL().selectTask(s);
            %>
            <p>Дата и время:
                <input type="text" maxlength="25" size="25"  name="time" id="time" value="<%= r.getTimeString()%>"onblur ="bl_cursor();" onfocus="fc_cursor();"/></p>
            <p>Название:
                <input type="text" maxlength="25" size="30"  name="name" id="name" value="<%= r.getName()%>"onblur ="bl_cursor1();" onfocus="fc_cursor1();"/></p>
            <p>Описание:
                <input type="text" maxlength="30" size="30"  name="desc" id="desc" value="<%= r.getDescription()%>"onblur ="bl_cursor2();" onfocus="fc_cursor2();"/></p>
            <p>Контакты:
                <input type="text" maxlength="40" size="40"  name="conc" id="conc" value="<%= r.getContacts()%>"onblur ="bl_cursor3();" onfocus="fc_cursor3();"/></p>
            <h4><button name = "submit" value="c<%= s%>">Сохранить изменения</button></h4>
          
        </form>
    </body>
</html>
