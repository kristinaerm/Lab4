<%@page import="model.NotificationTimerTasks"%>
<%@page import="java.util.Timer"%>
<%@page import="model.LoaderSQL"%>


<%@page import="java.util.LinkedList"%>
<%@page import="model.Record"%>
<%@page import="java.io.Reader"%>
<%@page errorPage="error.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="notific.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js.js"></script>
        <title>My Task Manager</title>
    </head>
    <body>


            <%
                String clock = "0";
                String mess = "0";
                if (request.getAttribute("tt") != null) {
                    out.println("<p>" + request.getAttribute("tt").toString() + "</p>");
                    clock = request.getAttribute("tt").toString();
                    mess = request.getAttribute("mes").toString();
                    out.println("<p>" + clock.toString() + mess.toString()+"</p>");
                }

            %>

            <script>
            var timeout = <%=clock%>;
            
            function timer()
            {
                if (--timeout > 0)
                {
                    window.setTimeout("timer()", 1000);
                } else
                {
                    alert(timeout.toString());
                }
            }

        </script>
        <form name="mainform" action="taskManager" method="post">
            <h1>Задачи пользователя</h1>

            <table id="tasklog"> 

                <tr>
                    <th>№</th>
                    <th>Время</th>
                    <th>Название</th>
                    <th>Описание</th>
                    <th>Контакты</th>
                    <th>Delete</th>
                    <th> Change</th>
                </tr>
                <%

                    LinkedList<Record> r = new LinkedList<>();
                    r = new LoaderSQL().selectInTableTask();
                    String submit = request.getParameter("submit");
                    

                %>
                <%                    for (int i = 0; i < r.size(); i++) {
                %>


                <tr id=i>
                    <td><%= i + 1%></td>

                    <td><a href=""><%= r.get(i).getTimeString()%></a></td>
                    <td><%= r.get(i).getName()%></td>
                    <td><%= r.get(i).getDescription()%></td>
                    <td><%= r.get(i).getContacts()%></td>
                    <td><button onclick="del()" value="d<%= r.get(i).getId()%>"  name = "submit">Delete</button></td> 
                    <td><button value="c<%= r.get(i).getId()%>" name = "submit">Change</button></td>
                </tr>
                <%
                    }
                %>
            </table>

            <br>

            <table>
                <td>
                    <label>Добавить запись:</label> 
                    <br>
                    <table id = "addRecTable">
                        <tr><td><label>Название</label></td>
                            <td><input type="text" name="name" id="name"></td></tr>
                        <tr><td><label >Время</label></td>
                            <td><input type="datetime-local" name="time" id="time"></td></tr>
                        <tr><td><label>Описание</label></td>
                            <td><input type="text" name="descr" id="descr"></td></tr>
                        <tr><td><label>Контакты</label></td>
                            <td><input type="text" name="cont" id="conc"></td></tr>
                    </table>
                    <button onclick="er()" value="a" name = "submit">Добавить</button>
                </td>

            </table>


        </form>
        <script>

            timer();

        </script>
    </body>
</html>
