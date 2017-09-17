<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book List</title>
</head>
<body>
	<div>
		<table>
			<tr>
				<td>Book Title</td>
				<td>Values</td>
			</tr>
				<c:forEach items="${products}" var="products">
					<tr>
						<td>${product.title}</td>
						<td>
							<c:forEach items="${product.prices}" var="price">
								[${price.value} - ${price.bookType}]
							</c:forEach>
						</td>
					</tr>
				</c:forEach>
		</table>
	</div>
</body>
</html>