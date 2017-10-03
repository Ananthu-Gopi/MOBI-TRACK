<%-- 
    Document   : logout
    Created on : Jun 5, 2017, 7:31:47 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
     <%
     session.setAttribute("log","");
     response.sendRedirect("../Guest/Login.jsp");
     
     %>
    </body>
</html>
