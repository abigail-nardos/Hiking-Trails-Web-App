<!-- IMPORTS -->
<%@page import="java.util.List"%>
<%@page import="simple.Trail"%>
<%@page import="simple.User"%>
<%@page import="simple.HikingRecord"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html >
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Victoria Hiking Trails</title>
	
	<!-- NOTE: ERROR LINKING TO .js AND .css FILES -->
	<!-- script and style lines inputted below for now -->
	<link rel="stylesheet" type="text/css" href="/style.css" media="screen">
	<script type="text/javascript" src="main.js"></script>
</head>

<body>
	
	<!-- OUTPUT HIKING TRAILS TABLE -->
	<!-- ========================== -->
	<div class="Trails-Container">
	
		<!-- HIKING TRAILS SEARCH FILTER -->
		<div class="trail-filter">
			<input type="text" id="trailFilter" placeholder="Trail Name Filter" onkeyup="searchTrails()">
		</div>
		
		<% List<Trail> trails = (List<Trail>)request.getAttribute("listTrail");%>
		<table id="trailTable">
		
			<caption><font size="+7">Hiking Trails </font></caption>
		
		 	<thead>
			   	<tr>
				    <th>Trail Name</th>
				    <th>Location</th>
				    <th>Difficulty</th>
				    <th>Distance</th>
				    <th>Elevation</th>
			   	</tr>
			</thead>
			
		  	<tbody>
		  		<%
				for(Trail trail : trails){
				%>
			   	<tr>
				    <td><%=trail.getName()%></td>
				    <td><%=trail.getLocation()%></td>
				    <td><%=trail.getDifficulty()%></td>
				    <td><%=trail.getDistance()%></td>
				    <td><%=trail.getElevation()%></td>
				</tr>
			   <%} %>
			  
		  	</tbody>
		 </table>
	</div>
	 
	<div class="Right-Container">
		
		<!-- HIKING RECORDS CONTAINER -->
		<!-- ======================== -->
		<div class="Hiking-Records-Container">
	
			<button class="add-record-button" onclick="openRecordForm()"><u>Add Record</u></button>
			
			<!-- DISPLAY ADD HIKING RECORD FORM -->
			<!-- ============================== -->
			<div class="record-popup" id="recordForm">
			
				<form action="insert-hiking-record" method="get" class="record-form-container">
				
					<h1><font color="black">User Record Form</font></h1>
					
					<!-- CHOOSE A TRAIL SELECTION -->
					<label for="trail_name"><b>Choose a Trail</b></label>
					<select name="trail_name" required>
						<c:forEach var="trail" items="${listTrail}">
							<option value = "<c:out value='${trail.name}'/>"> <c:out value='${trail.name}'/> </option>
						</c:forEach>	
					</select>
					<br><br>
					
					<!-- CHOOSE A USER SELECTION -->
					<label for="user_name"><b>Choose a User</b></label>
					<select name="user_name" required>
						<c:forEach var="user" items="${listUser}">
							<option value = "<c:out value='${user.name}'/>"> <c:out value='${user.name}'/> </option>
						</c:forEach>	
					</select>
					<br><br>
					
					<!-- CHOOSE A DATE SELECTION -->
					<label for="date"><b>Date</b></label>
					<input type="date" name="date" required>
					<br><br>
					
					<!-- CHOOSE A START TIME SELECTION -->
					<label for="start_time"><b>Start Time</b></label>
					<input type="time" name="start_time">
					<br><br>
					
					<!-- CHOOSE AN END TIME SELECTION -->
					<label for="end_time"><b>End Time</b></label>
					<input type="time" name="end_time">
					<br><br>
					<br><br>
					
					<!-- SUBMIT AND CANCEL BUTTONS -->
					<button type="button" class="btn-record-cancel" onclick="closeRecordForm()">&times;</button>
					<button type="submit" class="btn-record-submit">Submit</button>
				</form>
			</div>
			
			<!-- HIKING RECORDS SEARCH FILTER -->
			<div class="hiking-records-filter">
				<input type="text" id="recordFilter" placeholder="Trail Name Filter" onkeyup="searchRecords()">
			</div>
			
			<!-- OUTPUT HIKING RECORDS TABLE -->
			<!-- =========================== -->
			<table id="userRecordTable">
		
				<caption><font size="+7"> Hiking Records </font></caption>
				
			 	<thead>
				   	<tr>
					    <th>Trail Name</th>
					    <th>User Name</th>
					    <th>Date</th>
					    <th>Start Time</th>
					    <th>End Time</th>
					    <th></th>
				   	</tr>
				</thead>
				
				<tbody>
					<c:forEach var="record" items="${listRecord}">
						<tr>
							<td>
								<c:out value="${record.trailName}"/>
							</td>
							<td>
								<c:out value="${record.userName}"/>
							</td>
							<td>
								<c:out value="${record.date}"/>
							</td>
							<td>
								<c:out value="${record.startTime}"/>
							</td>
							<td>
								<c:out value="${record.endTime}"/>
							</td>
							<td>
								<a href="edit-hiking-record?trail_name=<c:out value='${record.trailName}'/>&user_name=<c:out value='${record.userName}'/>&date=<c:out value='${record.date}'/>&start_time=<c:out value='${record.startTime}'/>&end_time=<c:out value='${record.endTime}'/>">edit</a>
								<a href="delete-hiking-record?trail_name=<c:out value='${record.trailName}'/>&user_name=<c:out value='${record.userName}'/>&date=<c:out value='${record.date}'/>">delete</a>
							</td>
						</tr>
					</c:forEach>						
			  	</tbody>
		 	</table>
		</div>
	
		<!-- USERS CONTAINER -->
		<!-- =============== -->
		<div class="Users-Container">
	
		 	<button class="add-user-button" onclick="openUserForm()"><u>Add User</u></button>
			
			<!-- DISPLAY ADD USER FORM -->
			<!-- ===================== -->
			<div class="user-popup" id="userForm">
			
				<form action="insert-user" method="get" class="user-form-container">
				
					<h1><font color="black">User Form</font></h1>
					
					<!-- CHOOSE A NAME INPUT -->
					<label for="name"><b>Name</b></label>
					<input type="text" placeholder="Enter Name" name="name" required>
					
					<!-- CHOOSE A BIRTHDAY SELECTION -->
					<label for="birthday"><b>Birthday</b></label>
					<input type="date" name="birthday">
					<br><br>
					<br><br>
					
					<!-- SUBMIT AND CANCEL BUTTONS -->
					<button type="button" class="btn-user-cancel" onclick="closeUserForm()">&times;</button>
					<button type="submit" class="btn-user-submit">Submit</button>
				</form>
			</div>
			
			<!-- USER SEARCH FILTER -->
			<div class="users-filter">
				<input type="text" id="usersFilter" placeholder="User Name Filter" onkeyup="searchUsers()">
			</div>
			
			<% List<User> users = (List<User>)request.getAttribute("listUser");%>
			
			<!-- DISPLAY USERS TABLE -->
			<!-- =================== -->
			<table id="userTable">
			
				<caption><font size="+7"> Users </font></caption>
				
			 	<thead>
				   	<tr>
					    <th>User Name</th>
					    <th>Birthday</th>
					    <th></th>
				   	</tr>
				</thead>
				
				<tbody>
				   <c:forEach var="user" items="${listUser}">
						<tr>
							<td>
								<c:out value="${user.name}"/>
							</td>
							<td>
								<c:out value="${user.birthday}"/>
							</td>
							<td>
								<a href="edit-user?name=<c:out value='${user.name}'/>">edit</a>
								<a href="delete-user?name=<c:out value='${user.name}'/>">delete</a>
							</td>
						</tr>
					</c:forEach>	
			  	</tbody>
			 </table>
		</div>
	</div>
</body>

<!-- NOTE: ERROR CONNECTING TO .css FILE, SO INPUTTING STYLE LINES BELOW -->
<style>
	*{
		box-sizing: border-box;
		}
		
		body {
			background-color: #1A0129;
			color: #fff;
			font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
			display: flex;
			align-content: center;
			justify-content: center;
			height: 100vh;
			margin: 0;
		}
		
		.Trails-Container {
			position: absolute;
			width: 50%;
			height: 100%;
			left: 0px;
			background-color: #643B9F;
			border-radius: 10px;
			box-shadow: 0 5px 10px rgba(12, 16, 31, 0.4);
			padding: 100px;
			margin: auto;
			overflow: hidden;
			overflow-y: scroll;
		}
		
		.Hiking-Records-Container {
			position: absolute;
			width: 50%;
			height: 50%;
			right: 0px;
			top: 0px;
			background-color: #643B9F;
			border-radius: 10px;
			box-shadow: 0 5px 10px rgba(12, 16, 31, 0.4);
			padding: 100px;
			margin: auto;
			overflow: hidden;
			overflow-y: scroll;
		}
		
		.Users-Container {
			position: absolute;
			width: 50%;
			height: 50%;
			right: 0px;
			bottom: 0px; 
			background-color: #643B9F;
			border-radius: 10px;
			box-shadow: 0 5px 10px rgba(12, 16, 31, 0.4);
			padding: 100px;
			margin: auto;
			overflow: hidden;
			overflow-y: scroll;
		}
		
		
		table {
			color: #e3e3e3;
			padding: 10px;
			width: 100%;
		}
		
		table th,
		table td {
			padding: 15px;
			text-align: left;
			font-size: 20px;
		}
		
		table tbody tr {
			background-color: #1c223b;
		}
		
		.edit {
			cursor: pointer;
			background-color: DCCFEC;
			color: black;
			border: 0;
			border-radius: 2px;
			color: #909090;
			font-size: 16px;
			padding: 5px 10px;
		}
		
		.trail-filter input {
			position: absolute;
			top: 120px;
			height: 30px;
			width: 150px;
		}
		
		.hiking-records-filter input {
			position: absolute;
			top: 120px;
			left: 70px;
			height: 30px;
			width: 150px;
		}
		
		.users-filter input {
			position: absolute;
			top: 120px;
			left: 70px;
			height: 30px;
			width: 150px;
		}
		
		.add-record-button {
			position: absolute;
			left: 700px;
			top: 50px;
			background-color: #C1BECD; 
			color: black; 
			padding: 10px 24px;
			cursor: pointer; 
			float: left; 
		}
		
		.add-user-button {
			position: absolute;
			left: 700px;
			top: 50px;
			background-color: #C1BECD; 
			color: black; 
			padding: 10px 24px;
			cursor: pointer; 
			float: left; 
		}		
		
		.record-popup {
			display: none;
			position: fixed;
			bottom: 0;
			right: 15px;
			border: 15px; solid #f1f1f1;
			z-index: 9;
		}
		
		.user-popup {
			display: none;
			position: fixed;
			bottom: 0;
			right: 15px;
			border: 3px; solid #f1f1f1;
			z-index: 9;
		}
		
		.record-form-container {
			max-width: 400px;
			max-height: 800px;
			padding: 30px;
			background-color: white;
		}
		
		.user-form-container {
			max-width: 400px;
			max-height: 800px;
			padding: 30px;
			background-color: white;
		}
		
		.record-form-container input[type=text] {
			width: 100%;
			padding: 15px;
			margin: 5px 0 22px 0;
			border: none;
			background: #f1f1f1;
		}
		
		.user-form-container input[type=text] {
			width: 100%;
			padding: 15px;
			margin: 5px 0 22px 0;
			border: none;
			background: #f1f1f1;
		}
		
		.record-form-container {
			color: black;
		}
		
		.user-form-container {
			color: black;
		}
		
		.btn-record-cancel {
			position: fixed;
			top: 660px;
			right: 30px;
		}
		
		.btn-user-cancel {
			position: fixed;
			top: 755px;
			right: 30px;
		}
		
		.btn-record-submit {
			position: fixed;
			right: 200px;
			bottom: 15px;
		}
		
		.btn-user-submit {
			position: fixed;
			right: 200px;
			bottom: 15px;
		}
		
		a:link, a:visited {
			background-color: #f4e3ff;
			color: black;
		}
		
		a:hover, a:active {
			background-color: purple;
		}
</style>

<!-- NOTE: ERROR CONNECTING TO .JS FILE, SO INPUTTING SCRIPT LINES BELOW -->
<script>
	//TO DO: document searchTrails method
	function searchTrails() {
		var input, filter, table, tr, td, i, txtValue;
		input = document.getElementById("trailFilter");
		filter = input.value.toUpperCase();
		table = document.getElementById("trailTable");
		tr = table.getElementsByTagName("tr");
		
		for (i = 0; i < tr.length; i++) {
		  td = tr[i].getElementsByTagName("td")[0];
		  
		  if (td) {
		    txtValue = td.textContent || td.innerText;
		    
		    if (txtValue.toUpperCase().indexOf(filter) > -1) {
		      tr[i].style.display = "";
		    } else {
		      tr[i].style.display = "none";
		    }
		  }       
		}
	}

	//TO DO: document searchRecords method
	function searchRecords() {
		var input, filter, table, tr, td, i, txtValue;
		input = document.getElementById("recordFilter");
		filter = input.value.toUpperCase();
		table = document.getElementById("userRecordTable");
		tr = table.getElementsByTagName("tr");
		
		for (i = 0; i < tr.length; i++) {
			td = tr[i].getElementsByTagName("td")[0];
			
			if (td) {
				txtValue = td.textContent || td.innerText;
				
				if (txtValue.toUpperCase().indexOf(filter) > -1) {
				  tr[i].style.display = "";
				} else {
				  tr[i].style.display = "none";
				}
			}       
		}
	}
	
	//TO DO: document searchUsers method
	function searchUsers() {
		var input, filter, table, tr, td, i, txtValue;
		input = document.getElementById("usersFilter");
		filter = input.value.toUpperCase();
		table = document.getElementById("userTable");
		tr = table.getElementsByTagName("tr");
		
		for (i = 0; i < tr.length; i++) {
			td = tr[i].getElementsByTagName("td")[0];
			
			if (td) {
				txtValue = td.textContent || td.innerText;
				
				if (txtValue.toUpperCase().indexOf(filter) > -1) {
				  tr[i].style.display = "";
				} else {
				  tr[i].style.display = "none";
				}
			}       
		}
	}
	
	//TO DO: document openRecordForm method
	function openRecordForm() {
		document.getElementById("recordForm").style.display = "block";
	}
	
	//TO DO: document closeRecordForm method
	function closeRecordForm() {
		document.getElementById("recordForm").style.display = "none";
	}
	
	//TO DO: document openUserForm method
	function openUserForm() {
		document.getElementById("userForm").style.display = "block";
	}
	
	//TO DO: document closeUserForm method
	function closeUserForm() {
		document.getElementById("userForm").style.display = "none";
	}
</script>
</html>