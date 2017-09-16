<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book Registration</title>
</head>
<body>
	<form action="/weblibrary/products" method="post">
	
		<div>
			<label for="subject">Book Subject</label>
			<input type=text name="subject" id="subject"/>
		</div>	
	
		<div>
			<label for="title">Title</label>
			<input type="text" name="title" id="title"/>
		</div>
		
		<div>
			<label for="author">Author</label>
			<input type="text" name="author" id="author"/>
		</div>
		
		<div>
			<label for="description">Description</label>
			<textarea rows="10" cols="20" name="description" id="description"></textarea>
		</div>
		
		<div>
			<label for="pages">Pages Number</label>
			<input type="text" name="pages" id="pages"/>
		</div>
		
		<div>
			<label for="company">Publishing Company</label>
			<input type="text" name="company" id="company"/>
		</div>
		
		<div>
			<c:forEach items="${types}" var="bookType" varStatus="status">
				<div>
					<label for="price_${bookType}">${bookType}</label>
					<input type="text" name="prices[${status.index}].value" id="price_${bookType}"/>
					<input type="hidden" name="prices[${status.index}].bookType" value="${bookType}"/>
				</div>
			</c:forEach>
		</div>
		
		<div>
			<input type="submit" value="Save">
		</div>
	</form>

</body>
</html>