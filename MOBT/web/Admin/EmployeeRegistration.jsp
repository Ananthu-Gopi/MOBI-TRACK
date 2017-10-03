<%--
    Document   : admin_reg
    Created on : Mar 4, 2017, 9:41:55 PM
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
    
    document.addEventListener("DOMContentLoaded", function() {
    var elements = document.getElementsByTagName("INPUT");
    for (var i = 0; i < elements.length; i++) {
        elements[i].oninvalid = function(e) {
            e.target.setCustomValidity("");
            if (!e.target.validity.valid) {
                e.target.setCustomValidity("Invalid Entry");
            }
        };
        elements[i].oninput = function(e) {
            e.target.setCustomValidity("");
        };
    }
})
    
</script>
<html>
    <body onload="noback()"></body>
</html>
<h1>Employee Registration</h1>
        <form class="form-group">
                
               


<%
     if(session.getAttribute("log")=="")
    {
        response.sendRedirect("../Guest/login.jsp");
    }
                   String name="";
                   
                    String dcode="";
                    String dsgcode="";
                    String gender="";
                    String email="";
                    String contact="";
                    String password="";
                    String address="";
                    String quali="";
                    String experience="";
                    String status="";
                    String adharno="";
  String id="";                
 
if(request.getParameter("eid")!=null)
{
id=request.getParameter("eid");

%>

<input type="hidden" name="eid" value="<%=id%>">
            <%


String sel="select * from tbl_employee where emp_id='"+id+"'";
                ResultSet rs=obj.selectCommand(sel);
                while(rs.next())
                {
                    name=rs.getString("emp_name");
                    dcode=rs.getString("dept_code");
                    dsgcode=rs.getString("desig_code");
                   gender=rs.getString("emp_gender");
                    email=rs.getString("emp_email");
                   contact=rs.getString("emp_contact");
                   password=rs.getString("emp_password");
                   address=rs.getString("emp_address");
                    quali=rs.getString("emp_qual");
                   experience=rs.getString("emp_expsummary");
                    status=rs.getString("emp_status");
                  adharno=rs.getString("emp_adhharno");
                   
}
}
%>
            <table class="table table-striped">

                <tr>
                    <td><b>Employee Name</b></td>
                    <td><input class="form-control" type="text" name="ename" value="<%=name%>" required=""  ></td>
                    
                </tr>
                <tr>
                    <td><b>Gender</b></td>
                    <%
                    if(gender.equals("Male"))
                    {
                    %>
                    
                    <td><input type="radio" name="gen" value="Male" checked="true">Male<input type="radio" name="gen" value="Female">Female</td>
                    <%
                    }
                    else
                    {
                    %>
                    <td><input type="radio" name="gen" value="Male">Male<input type="radio" name="gen" value="Female" checked="true">Female</td>
                    
                    <%
                    }
                    %>
                    
                </tr>
                <tr>
                    <td><b>Department Code</b></td>
                    <td><select class="form-control" name="dep">
                            <option value="sel">---select--</option>
                    <%
                        String sel="select * from tbl_department";
                        ResultSet rs=obj.selectCommand(sel);
                        while(rs.next()){
                          
                    if(rs.getString("dept_code").equals(dcode))
                    {
                        %>
                        <option value="<%=rs.getString("dept_code")%>" selected="true"><%=rs.getString("dept_code")%></option>
                   <%
                    }
                    else
                    {
                    %>
                    <option value="<%=rs.getString("dept_code")%>"><%=rs.getString("dept_code")%></option>
                   <%
                    }
                        }
                   %> 
                            
                        </select></td>
                </tr>
                <tr>
                    <td><b>Email ID</b></td>
                    <td><input class="form-control" type="email" name="email" value="<%=email%>" required=""></td>
                </tr>
                <tr>
                    <td><b>Mobile No</b></td>
                    <td><input class="form-control" type="number" min="7000000000" max="9999999999" pattern="/(7|8|9)\d{9}/" name="mob" required="" value="<%=contact%>"></td>
                </tr>
        <tr>
            <td><b>Password</b></td>
            <td><input class="form-control" type="password" name="pass" required="" value="<%=password%>"></td>
                </tr>
                
            <tr>
                <td><b>Address</b></td>
                <td>
                    <textarea class="form-control" name="txtaddress" rows="4" cols="21" value=""><%=address%></textarea>
                </td>
            </tr>
            
            <tr>
                <td><b>Qualification</b></td>
                <td><input class="form-control" type="text" name="qual" value="<%=quali%>"></td>
            </tr>
            <tr>
                <td><b>Experience</b></td>
                <td><input class="form-control" type="text" name="exp" value="<%=experience%>"></td>
            </tr>
            <tr>
                <td><b>Designation Code</b></td>
                <td><select name="descode" class="form-control" value="<%=dsgcode%>">
                            <option value="selects">---select--</option>
                    <%
                        String select="select * from tbl_designation";
                        ResultSet rset=obj.selectCommand(select);
                        while(rset.next()){
                          
                       if(rset.getString("desig_code").equals(dsgcode))
                       {
                       
                       %>
                       <option value="<%=rset.getString("desig_code")%>" selected="true"><%=rset.getString("desig_code")%></option>
                   <%
                       }
                       else
                       {
                    %>
                    <option value="<%=rset.getString("desig_code")%>"><%=rset.getString("desig_code")%></option>
                   <%
                       }
                        }
                   %> 
                            
                        </select></td>
            </tr>
            <tr>
                <td><b>Aadhar No</b></td>
                <td><input class="form-control" min="100000000000" max="999999999999" type="number" name="aadhar" value="<%=adharno%>"></td>
            </tr>

                <tr>
                    <td colspan="2"align="center"><input class="btn btn-primary" type="submit" name="sub" value="Submit"><input class="btn btn-default" type="reset" name="res" value="Cancel"></colspan></td>
                </tr>
            </table>
            <%
              if(request.getParameter(("sub"))!=null){
                 String ename=request.getParameter("ename");
                  gender=request.getParameter("gen");
                  String dept=request.getParameter("dep");
                  email=request.getParameter("email");
                  String mob=request.getParameter("mob");
                  String pass=request.getParameter("pass");
                  address=request.getParameter("txtaddress");
                  
                  String qual=request.getParameter("qual");
                  String exp=request.getParameter("exp");
                  String desg=request.getParameter("descode");
                  String adh=request.getParameter("aadhar");
                  
                  
                  if(request.getParameter("eid")!=null)
                  {
                  String eid=request.getParameter("eid");
                  String ins="update tbl_employee set emp_name='"+ename+"',emp_gender='"+gender+"',dept_code='"+dept+"',emp_email='"+email+"',emp_contact='"+mob+"',emp_password='"+pass+"',emp_address='"+address+"',emp_qual='"+qual+"',emp_expsummary='"+exp+"',desig_code='"+desg+"',emp_adhharno='"+adh+"' where emp_id='"+eid+"'";
                  boolean b=obj.executeCommand(ins);
                  if(b==true){
                      out.println("Updated");
                      response.sendRedirect("EmployeeRegistration.jsp");
                      
                  if(desg.equals("mng"))
                      {
                         
                          
                      String ins2="update tbl_department set manager_id='"+eid+"' where dept_code='"+dept+"'";
                  boolean b3=obj.executeCommand(ins2);
                          }
                          
                  
                  
                  
                  }
                  else{
                      out.println("Insert Failed");
                  }
                  
                  
                  
                  
                  
                  
    
                  }
                  else
                  {
                  
                  String ins="insert into tbl_employee(emp_name,emp_gender,dept_code,emp_email,emp_contact,emp_password,emp_address,emp_qual,emp_expsummary,desig_code,emp_adhharno) values('"+ename+"','"+gender+"','"+dept+"','"+email+"','"+mob+"','"+pass+"','"+address+"','"+qual+"','"+exp+"','"+desg+"','"+adh+"')";
                  boolean b=obj.executeCommand(ins);
                  if(b==true){
                  
                      if(desg.equals("mng"))
                      {
                          String sel2="select MAX(emp_id) from tbl_employee";
                          ResultSet rs56=obj.selectCommand(sel2);
                          if(rs56.next())
                          {
                          
                      String ins2="update tbl_department set manager_id='"+rs56.getString("MAX(emp_id)")+"' where dept_code='"+dept+"'";
                  boolean b3=obj.executeCommand(ins2);
                          }
                          }
                      
                      out.println("Inserted");
                      response.sendRedirect("EmployeeRegistration.jsp");
                      
                  }
                  else{
                      out.println("Insert Failed");
                  }
                  
                  
                  
                  }
              }  
            %>
           
        </form>
            <%@include file="AdminFooter.jsp" %>