<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
 <form action="../consumer/updatepharmacy.do" method="post">
<select name="id" style="font-size:20px;">  
<c:forEach items="${listp}" var="p">
<option value="${p.id }">${p.name}</option>  
</c:forEach>
</select>  
<br/><br/>  
<input type="hidden" name="id1" value="${pre.id}">
<input type="submit" value="确认" style="font-size:20px;">
</form> 

</body>
</html>