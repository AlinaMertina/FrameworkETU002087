<%@page import="java.util.Vector"%>
<%@page import="java.lang.Object"%>

<%
    Vector<Object[]> tabe = (Vector<Object[]>) request.getAttribute("nomemp");
   
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
    <table>
        <tr>
            <th>Nom Employer</th>
            <th>Age</th>
        </tr>
        <% for(int i=0 ;i<tabe.size();i++){ %>
            <tr>
                <td> <% out.println((String)tabe.get(i)[0]); %></td>
                <td> <% out.println((String)tabe.get(i)[1]); %></td>
            </tr>
        <% } %>
       
    </table>
</body>
</html> 
