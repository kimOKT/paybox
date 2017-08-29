<%-- 
    Document   : index
    Created on : 28 août 2017, 22:48:24
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
        <h1>Payement direct</h1>
        
        <form action="/CallAPI" method="post" name="Tests Paybox Direct">
            Montant
            <input name="MONTANT" value="1000" size="10" maxlength="10" type="text"><br>
            <input type="submit">
        </form>

        
    </body>
</html>
