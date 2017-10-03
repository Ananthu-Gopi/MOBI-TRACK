<%-- 
    Document   : EditEmployee
    Created on : Apr 13, 2017, 2:31:00 PM
    Author     : hp
--%>

<%@page import="java.sql.*"%>
<jsp:useBean class="db.ConnectionClass" id="obj"></jsp:useBean>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script>
     function noback()
        {
            window.history.forward();
        }
</script>
<body onload="noback()"></body>
<%@include file="AdminHeader2.jsp" %>
<h1>Edit Employee</h1>
<form class="form-group">
            <table class="table table-striped">
                
                    <th><b>EMPLOYEE ID</b></th>
                    <th><b>EMPLOYEE NAME</b></th>
                    <th><b>DEPARTMENT CODE</b></th>
                    <th><b>DESIGNATION CODE</b></th>
                    <%
                         if(session.getAttribute("log")=="")
    {
        response.sendRedirect("../Guest/login.jsp");
    }
                        String sel="select * from tbl_employee";
                        ResultSet rs=obj.selectCommand(sel);
                while(rs.next())
                {
                    String name=rs.getString("emp_name");
                    String id=rs.getString("emp_id");
                    String dcode=rs.getString("dept_code");
                    String dsgcode=rs.getString("desig_code");
                  
                    %>
                    <tr>
                        <td><%=id%></td>
                        <td><%=name%></td>
                        <td><%=dcode%></td>
                        <td><%=dsgcode%></td>
                        <td><a href="EmployeeRegistration.jsp?eid=<%=id%>">EDIT</a></td>
                    </tr>
                    
                    <% } %>
                
            </table>
        </FORM>
                    <%@include file="AdminFooter.jsp" %>