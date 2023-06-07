<%-- 
    Document   : SubProcesada
    Created on : 3 jun 2023, 22:00:46
    Author     : alanr
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Gracias por su compra</title>
        <link rel="stylesheet" href="../assets/bootstrap/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1 class="display-4">Gracias por su compra</h1>
            <div class="mt-4">           
                <a href="RecargarCliente" class="btn btn-secondary">Volver</a>
            </div>
        </div>
    <c:if test="${! empty error}">
        <div class="error mt-3">${error}</div>
    </c:if>
</body>
</html>
