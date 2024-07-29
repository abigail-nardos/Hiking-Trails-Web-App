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
		<!-- ILLEGAL INPUT ERROR LABEL -->
		<label><font size="+7"> ERROR: CANNOT DISPLAY PAGE</font></label>
		<br><br>
		<br><br>
		<font size="+3">The requested page is unavailable. 
		This may be due to a connection error or SQL exception.
		Please try again later! (╯°□°)╯︵ ɹoɹɹƎ</font>
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

	label {
		position: fixed;
		right: 700px;
	}
</style>
</html>