<%@page import="java.lang.String"%>
<%@page import="java.lang.Integer"%>
<%@page import="java.util.Date"%>


<%
    String[] nomemp= (String[]) request.getAttribute("nomemp");
    Integer nbr= (Integer)request.getAttribute("nbr");
    
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
   
        <% out.println(nomemp[0]); %>
        <% out.println(nomemp[1]); %>
        <% out.println(nomemp[2]); %>
        <% out.println(nbr); %>
       
</body>
</html> 
