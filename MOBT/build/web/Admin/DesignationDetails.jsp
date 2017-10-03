<%-- 
    Document   : DesignationDetails
    Created on : Mar 9, 2017, 5:43:55 PM
    Author     : hp
--%>

<%@page import="java.sql.*"%>
<jsp:useBean class="db.ConnectionClass" id="obj"></jsp:useBean>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="AdminHeader2.jsp" %>
<h1>Add Designation</h1>
<form class="form-group">
            <table class="table table-striped">
                <tr>
                    <td>Designation Name</td>
                    <td><input type="text" name="name"></td>
                </tr>
                <tr>
                    <td>Designation Code</td>
                    <!--<td><input type="text" name="id"></td>-->
                    <td>
                        <select name="id">
                            <option value="mng">mng</option>
                            <option value="emp">emp</option>
                        </select>
                        
                        
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" name="sub"><input type="reset" name="cancel"></colspan></td>
                </tr>
            </table>
            <%
                 if(session.getAttribute("log")=="")
    {
        response.sendRedirect("../Guest/login.jsp");
    }
                if(request.getParameter("sub")!=null){
                    String name = request.getParameter("name");
                    String id = request.getParameter("id");
                    String query="insert into tbl_designation(desig_name,desig_code) values('"+name+"','"+id+"')";
                    boolean b = obj.executeCommand(query);
                    if(b==true){
                        out.println("Insert Successfull");
                        response.sendRedirect("DesignationDetails.jsp");
                    }
                    else{
                        out.println("Insert Failed");
                    }
                }
            %>
        </form>
         <table class="table table-striped">
            <th>Designation name</th>
            <th>Designation code</th>
            <%
                String sel="select * from tbl_designation";
                ResultSet rs=obj.selectCommand(sel);
                while(rs.next())
                {
                    String name=rs.getString("desig_name");
                    String id=rs.getString("desig_code");
                    %>
            <tr>
                <td><%=name%></td>
                <td><%=id%></td>
            </tr>
            <%
                }
            %>
            <tr>
                
            </tr>
           
            
        </table>
            <%@include file="AdminFooter.jsp" %>