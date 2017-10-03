<%-- 
    Document   : admin_dash
    Created on : Mar 9, 2017, 3:41:27 PM
    Author     : hp
--%>

<%@include file="AdminHeader.jsp" %>

<head>
    
    <script>
        function noback()
        {
            window.history.forward();
        }
    </script>

</head>
<body onload="noback()">
    <%
     if(session.getAttribute("log")=="")
    {
        response.sendRedirect("../Guest/login.jsp");
    }
    %>
<section class='aboutus' id='about'>
<div class='container'>
<div class="row">
                <div class="col-md-12 yomer-text-center">
                    <h2>Employee Tracking System</h2>
                    <p class="bigtext">
                       Employee tracking can add a great deal of value to any organizations, with benefits for employers and managers. In today's emerging scenario, Employee Tracking Software is mandatory for all organizations depending on working strategies. Tracking System leads to the increment in performance and most important aspect is that both in time and out time are recorded on daily basis which minimizes the chances of any mistake related to attendance of an employee. Employee tracking application can be accessed anytime and anywhere there is internet access. It is the central place for managing your field workforce every day.
                    </p>
                </div>
            </div>
            

</div>

</section>

            <a href="EmployeeRegistration.jsp"><b>Employee Registration</b></a>
        
            <a href="ActivateEmployee.jsp"><b>Activate Employee</b></a>
      
            <a href="DeactivateEmployee.jsp"><b>Deactivate Employee</b></a>
        
            <a href="DepartmentDetails.jsp"><b>Department Registration</b></a>
        
            <a href="DesignationDetails.jsp"><b>Designation </b></a>
            
            <a href="TaskDetails.jsp"><b>Task Registration </b></a>
            
       
            
</body>            <%@include file="AdminFooter.jsp" %>
