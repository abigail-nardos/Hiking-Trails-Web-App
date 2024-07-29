<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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

	<div class="error-page">
		<!-- OUTPUT ERROR PAGE FORM -->
		<!-- ====================== -->
		<form action = default, method="post">
		
			<!-- ILLEGAL INPUT ERROR LABEL -->
			<label><font size="+7">ꗃ SECRET MESSAGE UNLOCKED ꗃ</font></label>
			<input class = "secretMessage" type="text" name="secretMessage" value="${secretMessage}" readonly>
			<br><br>
			<br><br>
			
			<!-- SUBMIT BUTTON -->
			<button type="submit" class="submit">Back</button>
			
		</form>
	</div>
</body>

<!-- NOTE: ERROR CONNECTING TO .css FILE, SO INPUTTING STYLE LINES BELOW -->
<style>
	div {
		border-radius: 5px;
		background-color: #643B9F;
		padding: 100px;
		position: absolute;
		width: 50%;
		height: 50%;
		top: 200px;
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
	
	.submit {
		position: fixed;
		padding: 10px 20px;
		border: none;
		border-radius: 5px;
		cursor: pointer;
		font-size: 20px;
		width: 250px;
		height: 50px;
		top: 800px;
		right: 45%;
	}
	label {
		position: fixed;
		right: 520px;
	}
	
	.secretMessage {
		position: fixed;
		top: 465px;
		right: 410px;
		height: 200px;
		width: 1000px;
		font-size: 50px;
		color: white;
		text-align: center;
		background-color: #643B9F;
	}
</style>
</html>