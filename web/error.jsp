<%-- 
    Document   : error
    Created on : 29 août 2017, 13:41:54
    Author     : Geek-Kim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Erreur lors du paiement! <%= request.getAttribute("error") %></h1>
    </body>
</html>
