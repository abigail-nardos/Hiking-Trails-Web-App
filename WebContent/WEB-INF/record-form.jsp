<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Update Hiking Record</title>
	
	<!-- NOTE: ERROR LINKING TO .css FILE -->
	<!-- script and style lines inputted below for now -->
	<link rel="stylesheet" type="text/css" href="/style.css" media="screen">
</head>
<body>

	<div>
		<!-- DISPLAY UPDATE HIKING RECORD FORM-->
		<!-- ================================= -->
		<form action="update-hiking-record" method="post">
			<h1><font color="white" class="header">Update Hiking Record</font></h1>
			<br><br>
			
			<!-- CHOOSE A TRAIL SELECTION -->
			<label for="trail_name"><b>Choose a Trail</b></label>
			<select name="trail_name" required>
				<option value = "<c:out value='${trail_name}'/>" selected> <c:out value='${trail_name}'/> </option>
				<c:forEach var="trail" items="${listTrail}">
					<option value = "<c:out value='${trail.name}'/>"> <c:out value='${trail.name}'/> </option>
				</c:forEach>
			</select>
			<br><br>
			
			<!-- DISPLAY USER NAME -->
			<label for="user_name"><b>Choose a User</b></label>
			<select name="user_name" required>
				<option value = "<c:out value='${user_name}'/>" selected> <c:out value='${user_name}'/> </option>
				<c:forEach var="user" items="${listUser}">
					<option value = "<c:out value='${user.name}'/>"> <c:out value='${user.name}'/> </option>
				</c:forEach>	
			</select>
			<br><br>
			
			<!-- CHOOSE A DATE SELECTION -->
			<label for="date"><b>Date</b></label>
			<input type="date" name="date" value="<c:out value='${date}'/>" required>
			<br><br>
			
			<!-- CHOOSE A START TIME SELECTION -->
			<label for="start_time"><b>Start Time</b></label>
			<input type="time" name="start_time" value="<c:out value='${start_time}'/>">
			<br><br>
			
			<!-- CHOOSE AN END TIME SELECTION -->
			<label for="end_time"><b>End Time</b></label>
			<input type="time" name="end_time" value="<c:out value='${end_time}'/>">
			<br><br>
			
			<!-- SUBMIT BUTTON -->
			<button type="submit" class="btn-record-submit">Save</button>
			
		</form>
	</div>
</body>

<!-- NOTE: ERROR CONNECTING TO .css FILE, SO INPUTTING STYLE LINES BELOW -->
<style>
	input[type=text], select {
	  width: 100%;
	  padding: 12px 20px;
	  margin: 8px 0;
	  display: inline-block;
	  border: 1px solid #ccc;
	  border-radius: 4px;
	  box-sizing: border-box;
	}
	
	input[type=submit] {
	  width: 100%;
	  background-color: #4CAF50;
	  color: white;
	  padding: 14px 20px;
	  margin: 8px 0;
	  border: none;
	  border-radius: 4px;
	  cursor: pointer;
	}
	
	input[type=submit]:hover {
	  background-color: #45a049;
	}
	
	div {
	  border-radius: 5px;
	  background-color: #643B9F;
	  padding: 100px;
	}
	body {
		background-color: #1A0129;
		color: #fff;
		font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
		display: flex;
		align-content: center;
		justify-content: center;
		height: 70vh;
		margin: 0;
	}
	
	.btn-record-submit {
		padding: 10px 20px;
		border: none;
		border-radius: 5px;
		cursor: pointer;
	}
	
</style>
</html>