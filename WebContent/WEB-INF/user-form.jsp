<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update User</title>
</head>
<body>

	<div>
		<!-- DISPLAY UPDATE USER FORM-->
		<!-- ======================== -->
		<form action="update-user" method="post">
			<h1><font color="white" class="header">Update User</font></h1>
			<br><br>
			
			<!-- USER NAME INPUT  -->
			<label><b>User Name</b></label>
			<input type="text" value = "<c:out value='${user.name}'/>" name="name" required class="input">
			<br><br>
			
			<!-- UPDATE BIRTHDAY INPUT  -->
			<label><b>User Birthday</b></label>
			<input type="date" value="<c:out value='${user.birthday}'/>" name="birthday" class="input">
			<br><br>
			
			<!-- SUBMIT BUTTON  -->
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
		height: 50vh;
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