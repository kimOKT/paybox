<%-- 
    Document   : jspForm
    Created on : 29 août 2017, 20:48:08
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
        
        
        <form action="https://preprod-ppps.paybox.com/PPPS.php" method="post" name="Tests Paybox Direct">
                Date (JJMMAAAA)
                <input name="DATEQ" value="20032007" size="8" maxlength="8" type="text"><br>
                Type de question
                <input name="TYPE" value="00002" size="5" maxlength="5" type="text"><br>
                Numero de question
                <input name="NUMQUESTION" value="0000000001" size="10" maxlength="10" type="text"><br>
                Montant
                <input name="MONTANT" value="1000" size="10" maxlength="10" type="text"><br>
                Site
                <input name="SITE" value="1999888" size="7" maxlength="7" type="text"><br>
                Rang
                <input name="RANG" value="99" size="2" maxlength="2" type="text"><br>
                Reference commande
                <input name="REFERENCE" value="Hello World" size="30" maxlength="30" type="text"><br>
                <input name="VERSION" value="00104" type="hidden"><br>
                <input name="CLE" value="1999888I" type="hidden"><br>
                <input name="IDENTIFIANT" value="107904482" type="hidden"><br>
                <input name="DEVISE" value="978" type="hidden"><br>
                <input name="PORTEUR" value="1111222233334444" type="hidden"><br>
                <input name="DATEVAL" value="1010" type="hidden"><br>
                <input name="CVV" value="123" type="hidden"><br>
                <input name="ACTIVITE" value="024" type="hidden"><br>
                <input name="ARCHIVAGE" value="AXZ130968CT2" type="hidden"><br>
                <input name="DIFFERE" value="000" type="hidden"><br>
                <input name="NUMAPPEL" value="" type="hidden"><br>
                <input name="NUMTRANS" value="" type="hidden"><br>
                <input name="AUTORISATION" value="" type="hidden"><br>
                <input name="PAYS" value="" type="hidden"><br>
                <input type="submit">
        </form>
        
        
    </body>
</html>
