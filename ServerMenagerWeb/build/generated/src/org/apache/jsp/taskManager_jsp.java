package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.NotificationTimerTasks;
import java.util.Timer;
import model.LoaderSQL;
import java.util.LinkedList;
import model.Record;
import java.io.Reader;

public final class taskManager_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"error.jsp", true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"notific.css\"/>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <script src=\"js.js\"></script>\r\n");
      out.write("        <title>My Task Manager</title>\r\n");
      out.write("\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <form name=\"mainform\" action=\"taskManager\" method=\"post\">\r\n");
      out.write("            <h1>Задачи пользователя</h1>\r\n");
      out.write("\r\n");
      out.write("            <p><button value=\"r\" name = \"submit\">Задачи</button></p>\r\n");
      out.write("\r\n");
      out.write("            <table id=\"tasklog\"> \r\n");
      out.write("\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <th>№</th>\r\n");
      out.write("                    <th>Время</th>\r\n");
      out.write("                    <th>Название</th>\r\n");
      out.write("                    <th>Описание</th>\r\n");
      out.write("                    <th>Контакты</th>\r\n");
      out.write("                    <th>Delete</th>\r\n");
      out.write("                    <th> Change</th>\r\n");
      out.write("                </tr>\r\n");
      out.write("                ");


                    LinkedList<Record> r = new LinkedList<>();
                    r = new LoaderSQL().selectInTableTask();
                    String submit = request.getParameter("submit");
                    if (("r".equals(submit)) || ("a".equals(submit))) {

                
      out.write("\r\n");
      out.write("                ");
                    for (int i = 0; i < r.size(); i++) {
                
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                <tr id=i>\r\n");
      out.write("                    <td>");
      out.print( i + 1);
      out.write("</td>\r\n");
      out.write("\r\n");
      out.write("                    <td><a href=\"\">");
      out.print( r.get(i).getTimeString());
      out.write("</a></td>\r\n");
      out.write("                    <td>");
      out.print( r.get(i).getName());
      out.write("</td>\r\n");
      out.write("                    <td>");
      out.print( r.get(i).getDescription());
      out.write("</td>\r\n");
      out.write("                    <td>");
      out.print( r.get(i).getContacts());
      out.write("</td>\r\n");
      out.write("                    <td><button onclick=\"del()\" value=\"d");
      out.print( r.get(i).getId());
      out.write("\"  name = \"submit\">Delete</button></td> \r\n");
      out.write("                    <td><button value=\"c");
      out.print( r.get(i).getId());
      out.write("\" name = \"submit\">Change</button></td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                ");

                    }
                
      out.write("\r\n");
      out.write("                ");

                        }

                
      out.write("\r\n");
      out.write("            </table>\r\n");
      out.write("\r\n");
      out.write("            <br>\r\n");
      out.write("\r\n");
      out.write("            <table>\r\n");
      out.write("                <td>\r\n");
      out.write("                    <label>Добавить запись:</label> \r\n");
      out.write("                    <br>\r\n");
      out.write("                    <table id = \"addRecTable\">\r\n");
      out.write("                        <tr><td><label>Название</label></td>\r\n");
      out.write("                            <td><input type=\"text\" name=\"name\" id=\"name\"></td></tr>\r\n");
      out.write("                        <tr><td><label >Время</label></td>\r\n");
      out.write("                            <td><input type=\"datetime-local\" name=\"time\" id=\"time\"></td></tr>\r\n");
      out.write("                        <tr><td><label>Описание</label></td>\r\n");
      out.write("                            <td><input type=\"text\" name=\"descr\" id=\"descr\"></td></tr>\r\n");
      out.write("                        <tr><td><label>Контакты</label></td>\r\n");
      out.write("                            <td><input type=\"text\" name=\"cont\" id=\"conc\"></td></tr>\r\n");
      out.write("                    </table>\r\n");
      out.write("                    <button onclick=\"er()\" value=\"a\" name = \"submit\">Добавить</button>\r\n");
      out.write("                </td>\r\n");
      out.write("\r\n");
      out.write("            </table>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        </form>\r\n");
      out.write("\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
