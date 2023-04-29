<%@page import="java.lang.String"%>
<%@page import="java.util.Date"%>


<%
    Float age = (Float) request.getAttribute("test_Float");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Bonjour</h1>
    <% out.println(age); %>
        
</body>
</html> 
