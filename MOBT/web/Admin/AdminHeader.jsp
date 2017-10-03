<%-- 
    Document   : AdminHeader
    Created on : Apr 16, 2017, 12:52:30 PM
    Author     : Win7
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
MobiTrack
</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style1.css" rel="stylesheet">
<link href="font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
 <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
 <![endif]-->

</head>

<body>
 
    <script>
function goNewWin() {

//window.open("../Guest/Login.jsp",'TheNewpop','toolbar=1,location=1,directories=1,status=1,menubar=1,scrollbars=1,resizable=1'); 
window.location.href="../Guest/Login.jsp";
window.close();

//open("../Guest/Login.jsp",'_self').close();
}

</script>
    
<div class="nav">
		<div class='logo'></div>
		<ul class="menu">
		<li class="btMenu">
		<a href="javascript:void(0)">MENU</a></li>
                <li class="menuItem"><a href="HomePage.jsp">Home</a></li>
		<li class="menuItem"><a href="EmployeeRegistration.jsp">Employee Registration</a></li>
		<li class="menuItem"><a href="ActivateEmployee.jsp">Activate Employee</a></li>
		<li class="menuItem"><a href="DeactivateEmployee.jsp">Deactivate Employee</a></li>
		<li class="menuItem"><a href="DepartmentDetails.jsp">Department Registration</a></li>
		<li class="menuItem"><a href="DesignationDetails.jsp">Designation</a></li>
		<li class="menuItem"><a href="TaskDetails.jsp">Task Registration</a></li>
                <li class="menuItem"><a href="logout.jsp">Logout</a></li>
		</ul>
		</div>
  
  <!-- WRAPPER STARTS HERE-->
<div class='wrapper'>

<header><!-- HEADER STARTS HERE-->


    
<div class='container titles yomer-text-center'>
<h1 data-scroll-reveal="wait 0.25s, then enter top and move 40px over 1s"></h1>
<div class='subhead'>
 <p><b></b>!</p> 
 <div class='buttons'>
     <div class="row">
         <div class="col-lg-offset-2 col-lg-8">
     <a href="EmployeeRegistration.jsp" style="width:200px;height:70px;margin-bottom: 30px;margin-right: 40px;padding-top: 21px;" class="btn btn-danger">Employee Registration</a>
     <a href="ActivateEmployee.jsp" style="width:200px;height:70px;margin-bottom: 30px;padding-top: 21px;margin-right: 40px;" class="btn btn-success">Activate Employee</a>
     <a href="DeactivateEmployee.jsp" style="width:200px;height:70px;margin-bottom: 30px;padding-top: 21px;margin-right: 40px;" class="btn btn-primary">Deactivate Employee</a>
     <a href="DepartmentDetails.jsp" style="width:200px;height:70px;margin-bottom: 30px;padding-top: 21px;margin-right: 40px;" class="btn btn-danger">Department Registration</a>
     <a href="EditEmployee.jsp" style="width:200px;height:70px;margin-bottom: 30px;padding-top: 21px;margin-right: 40px;" class="btn btn-success">Edit Employee</a>
     <a href="TaskDetails.jsp" style="width:200px;height:70px;margin-bottom: 30px;padding-top: 21px;margin-right: 40px;" class="btn btn-primary">Task Registration</a>
         </div>
         </div>           
				
                    
</div> 
</div>
</div>
<div class=" logos"></div>
 <a class="godown animate" href="#first">Down</a>

</header>
    
   <section class='aboutus' id='about'>
<div class='container'>
<div class="row"> 