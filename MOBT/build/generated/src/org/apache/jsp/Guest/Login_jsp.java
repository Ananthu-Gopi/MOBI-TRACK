package org.apache.jsp.Guest;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.ResultSet;

public final class Login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!--\n");
      out.write("Author: W3layouts\n");
      out.write("Author URL: http://w3layouts.com\n");
      out.write("License: Creative Commons Attribution 3.0 Unported\n");
      out.write("License URL: http://creativecommons.org/licenses/by/3.0/\n");
      out.write("-->\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      db.ConnectionClass obj = null;
      synchronized (_jspx_page_context) {
        obj = (db.ConnectionClass) _jspx_page_context.getAttribute("obj", PageContext.PAGE_SCOPE);
        if (obj == null){
          obj = new db.ConnectionClass();
          _jspx_page_context.setAttribute("obj", obj, PageContext.PAGE_SCOPE);
        }
      }
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\t\n");
      out.write("<head>\n");
      out.write("\n");
      out.write("    \n");
      out.write("\n");
      out.write("  \n");
      out.write("    <title>MOBITRACK</title>\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("<script type=\"application/x-javascript\"> addEventListener(\"load\", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>\n");
      out.write("<meta name=\"keywords\" content=\"Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates\" />\n");
      out.write("<link href=\"css/style.css\" rel='stylesheet' type='text/css' />\n");
      out.write("<!--webfonts-->\n");
      out.write("<link href='http://fonts.googleapis.com/css?family=PT+Sans:400,700,400italic,700italic|Oswald:400,300,700' rel='stylesheet' type='text/css'>\n");
      out.write("<link href='http://fonts.googleapis.com/css?family=Exo+2' rel='stylesheet' type='text/css'>\n");
      out.write("<!--//webfonts-->\n");
      out.write("<script src=\"//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js\"></script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    \n");
      out.write("<script>$(document).ready(function(c) {\n");
      out.write("\t$('.close').on('click', function(c){\n");
      out.write("\t\t$('.login-form').fadeOut('slow', function(c){\n");
      out.write("\t  \t\t$('.login-form').remove();\n");
      out.write("\t\t});\n");
      out.write("\t});\t  \n");
      out.write("});\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write(" <!--SIGN UP-->\n");
      out.write(" <h1>MobiTrack</h1>\n");
      out.write("<div class=\"login-form\">\n");
      out.write("\t<div class=\"close\"> </div>\n");
      out.write("\t\t<div class=\"head-info\">\n");
      out.write("\t\t\t<label class=\"lbl-1\"> </label>\n");
      out.write("\t\t\t<label class=\"lbl-2\"> </label>\n");
      out.write("\t\t\t<label class=\"lbl-3\"> </label>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t\t<div class=\"clear\"> </div>\n");
      out.write("\t<div class=\"avtar\">\n");
      out.write("\t\t<img src=\"images/avtar.png\" />\n");
      out.write("\t</div>\n");
      out.write("\t\t\t<form>\n");
      out.write("\t\t\t\t\t<input type=\"text\" class=\"text\" value=\"Username\" name=\"user\" onfocus=\"this.value = '';\" onblur=\"if (this.value == '') {this.value = 'Username';}\" >\n");
      out.write("\t\t\t\t\t\t<div class=\"key\">\n");
      out.write("\t\t\t\t\t<input type=\"password\" value=\"Password\" name=\"pass\" onfocus=\"this.value = '';\" onblur=\"if (this.value == '') {this.value = 'Password';}\">\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\n");
      out.write("\t<div class=\"signin\">\n");
      out.write("\t\t<input type=\"submit\" value=\"Login\" name=\"log\">\n");
      out.write("\t</div>\n");
      out.write("                        </form>\n");
      out.write("</div>");

                if(request.getParameter("log")!=null){
                   String user=request.getParameter("user");
                   String password=request.getParameter("pass");
                   String query="select * from tbl_admin where admin_username='"+user+"' and admin_password='"+password+"'";
                 // System.out.println(query);
                   ResultSet rs=obj.selectCommand(query);
                   if(rs.next()){
                       session.setAttribute("log","log");
                       response.sendRedirect("../Admin/HomePage.jsp");
                       
                   }
                   else{
                       out.println("Invalid Login");
                   
                   }
                }
            
      out.write("\n");
      out.write(" <div class=\"copy-rights\">\n");
      out.write("\t\t\t\t\t<p>Template by <a href=\"http://w3layouts.com\" target=\"_blank\">w3layouts</a> </p>\n");
      out.write("\t\t\t</div>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
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
