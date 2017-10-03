<%-- 
    Document   : deactivate_user
    Created on : Mar 8, 2017, 6:00:39 PM
    Author     : hp
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean class="db.ConnectionClass" id="obj"></jsp:useBean>
<!DOCTYPE html>
<script>
     function noback()
        {
            window.history.forward();
        }
</script>
<body onload="noback()"></body>
<%
     if(session.getAttribute("log").equals(""))
    {
        response.sendRedirect("../Guest/login.jsp");
    }
    if((request.getParameter("id"))!=null){
        String id=request.getParameter("id");
        String update="UPDATE tbl_employee set emp_status=0 where emp_id='"+id+"'";
        obj.executeCommand(update);
        response.sendRedirect("DeactivateEmployee.jsp");
    }
    
    %>

      <%@include file="AdminHeader2.jsp" %>
<h1>Deactivate Employee</h1>
        <table class="table table-striped">
            <tr><th>Employee ID</th><th>Employee Name</th><th>Gender</th><th>Department</th><th>Email ID</th><th>Mobile NO</th></tr>
        <%
           String sel="select * from tbl_employee where emp_status=1";
           ResultSet rs=obj.selectCommand(sel);
           //System.out.println(rs);
           while(rs.next())
           {
               %>
            <tr>
                <td><%=rs.getString("emp_id")%></td>
                <td><%=rs.getString("emp_name")%></td>
                <td><%=rs.getString("emp_gender")%></td>
                <td><%=rs.getString("dept_code")%></td>
                <td><%=rs.getString("emp_email")%></td>
                <td><%=rs.getString("emp_contact")%></td>
                <% String s=rs.getString("emp_id");%>
                <td><a href="DeactivateEmployee.jsp?id=<%=rs.getString("emp_id")%>">Deactivate</a></td>
            </tr>
            <%
           }
            
            %>
        </table>
        <%@include file="AdminFooter.jsp" %>