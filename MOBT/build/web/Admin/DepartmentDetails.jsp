<%-- 
    Document   : DepartmentDetails
    Created on : Mar 9, 2017, 5:43:41 PM
    Author     : hp
--%>

<%@page import="java.sql.*"%>
<jsp:useBean class="db.ConnectionClass" id="obj"></jsp:useBean>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="AdminHeader2.jsp" %>
<h1>Department Registration</h1>
<script>
     function noback()
        {
            window.history.forward();
        }
</script>
<body onload="noback()"></body>
<form class="form-group">
            <table class="table table-striped">
                <tr>
                    <td>Department ID</td>
                    <td><input type="text" name="id"></td>
                </tr>
                <tr>
                    <td>Department Name</td>
                    <td><input type="text" name="name"></td>
                </tr>
                <tr>
<!--                    <td>Manager ID</td>
                       <td><select name="manid">
                            <option value="selects">---select--</option>
                    <%   if(session.getAttribute("log")=="")
    {
        response.sendRedirect("../Guest/login.jsp");
    }
                        String select="select * from tbl_employee where desig_code='mng'";
                        ResultSet rset=obj.selectCommand(select);
                        while(rset.next()){
                          
                       
                    %>
                    <option value="<%=rset.getString("emp_id")%>"><%=rset.getString("emp_id")%></option>
                   <%
                        }
                   %> 
                            
                        </select></td>-->
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" name="sub"><input type="reset" name="cancel"></colspan></td>
                </tr>
            </table>
                   <br/>
                   <br/>
            <%
                if(request.getParameter("sub")!=null){
                    String deptid = request.getParameter("id");
                    String deptname = request.getParameter("name");
                    String query="insert into tbl_department(dept_name,dept_code) values('"+deptname+"','"+deptid+"')";
                    boolean b = obj.executeCommand(query);
                    if(b==true){
                        out.println("Insert Successfull");
                        response.sendRedirect("DepartmentDetails.jsp");
                    }
                    else{
                        out.println("Insert Failed");
                    }
                }
            %>
        </form>
        <table class="table table-striped">
            <th>Department name</th>
            <th>Department code</th>
            <th>Manager ID</th>
            <%
                String sel="select * from tbl_department";
                ResultSet rs=obj.selectCommand(sel);
                while(rs.next())
                {
                    String name=rs.getString("dept_name");
                    String id=rs.getString("dept_code");
                    String mid=rs.getString("manager_id");
                    %>
            <tr>
                <td><%=name%></td>
                <td><%=id%></td>
                <td><%=mid%></td>
            </tr>
            <%
                }
            %>
            <tr>
                
            </tr>
           
            
        </table>
            <%@include file="AdminFooter.jsp" %>