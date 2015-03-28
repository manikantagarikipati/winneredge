<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	
	<style type="text/css">
body {
  font-family: 'Nunito', sans-serif;
  color: #384047;
}

h1 {
  margin: 0 0 30px 0;
  text-align: center;
}
		
	a {
	display: block;
  width: 115px;
  height: 25px;
  background: #4E9CAF;
  padding: 10px;
  text-align: center;
  border-radius: 5px;
  color: white;
  font-weight: bold;
	}
</style>
</head>
<body>
<h1>Welcome to Spring Login Example</h1>
<br>
<a href="/controller/register">Register</a>
<br>
<br>
<a href="/controller/login">LogIn</a>
<br>
<a href="http://google.co.in">Back To Tutorial</a>
</body>
</html>
