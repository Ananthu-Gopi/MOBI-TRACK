<%-- 
    Document   : TaskDetails
    Created on : Mar 21, 2017, 11:06:06 AM
    Author     : hp
--%>
<%@page import="java.sql.*"%>
<jsp:useBean class="db.ConnectionClass" id="obj"></jsp:useBean>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="AdminHeader2.jsp" %>

<script>
    
     function noback()
        {
            window.history.forward();
        }
</script>

<body onload="noback()"></body>
<h1>Task Registration</h1>
<form class="form-group">
            <table class="table table-striped">

                <tr>
                    <td><b>New Task Type</b></td>
                    <td><input type="text" name="taskname"></td>
                </tr>
                <tr>
                    <td colspan="2"align="center"><input type="submit" name="sub" value="Submit"><input type="reset" name="res" value="Cancel"></colspan></td>
                </tr>
            </table>
        </form>
        <%
             if(session.getAttribute("log")=="")
    {
        response.sendRedirect("../Guest/login.jsp");
    }
            if(request.getParameter("sub")!=null){
                String taskname=request.getParameter("taskname");
                String ins="insert into tbl_task(task_type) values('"+taskname+"')";
                boolean b=obj.executeCommand(ins);
                if(b==true){
                      out.println("Inserted");
                      response.sendRedirect("TaskDetails.jsp");
                      
                  }
                  else{
                      out.println("Insert Failed");
                  }
            }
        %>
       <table class="table table-striped">
           
            <th>Task Type</th>
            <%
                String sel="select * from tbl_task";
                ResultSet rs=obj.selectCommand(sel);
                while(rs.next())
                {
                    String name=rs.getString("task_type");
                    
                    %>
            <tr>
                <td><%=name%></td>
                
            </tr>
            <%
                }
            %>
        </table>
    <%@include file="AdminFooter.jsp" %>