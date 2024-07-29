<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="simple.User"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Users</title>
</head>
<body>
	<% List<User> users = (List<User>)request.getAttribute("listUser");%>
	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Birthday</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<% for(User user : users){ %>
			   <tr>
				    <td><%=user.getName()%></td>
					<td><%=user.getBirthday()%></td>
					<td><a href="edit?name=<c:out value='${user.name}' />">Edit</a>
					&nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?name=<c:out value='${user.name}' />">Delete</a></td>
				    <%} %>
			   </tr>

		</tbody>
	</table>

</body>
</html>