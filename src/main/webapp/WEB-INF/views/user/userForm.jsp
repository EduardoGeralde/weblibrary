<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Registration</title>
</head>
<body>
	<form:form action="${spring:mvcUrl('saveUser').build()}" method="post" commandName="user">
	
		<div>
			<label for="name">User name</label>
			<form:input path="name"/>
			<form:errors path="name"/>
		</div>
		
		<div>
			<label for="login">Login</label>
			<form:input path="login"/>
			<form:errors path="login"/>
		</div>
		
		<div>
			<label for="password">Password</label>
			<form:input path="password"/>
			<form:errors path="password"/>
		</div>
		
		<div>
			<c:forEach items="${roleList}" var="role" varStatus="status">
				<div>
					<label for="role_${role}">${role}</label>
					<input type="checkbox" value="${role}" name="roles[${status.index}].name" id="role_${role}"/>
				</div>
			</c:forEach>
			<!-- 
			<c:forEach items="${roleList}" var="role">
				<tr>
					<td><form:checkbox path="roles" value="roles[${status.index}].name" label="${role.name}"/></td>
				</tr>
			</c:forEach>
			-->
		</div>
		
		<div>
			<input type="submit" value="Save">
		</div>
		
	</form:form>
</body>
</html>