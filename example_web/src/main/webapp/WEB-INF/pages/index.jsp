<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hellow world</title>
</head>
<body>
  <p>Hello world again!</p>
  <p>
    <c:out value="${message}"></c:out>
  </p>
  <p>
    Counter:
    <c:out value="${counter}"></c:out>
  </p>
</body>
</html>