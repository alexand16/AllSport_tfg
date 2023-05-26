<%-- 
    Document   : graciasPorSuCompra
    Created on : 26 may 2023, 10:51:01
    Author     : alanr
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css">
        <title>Gracias por su compra</title>
    </head>
    <body>
        <div class="container">
            <h1 class="display-4">Gracias por su compra</h1>
            <div class="mt-4">
                <!--
                <a href="web/assets/facturas/${nombreFactura}" class="btn btn-primary">Ver factura</a>
                <iframe src="C:/Users/alanr/OneDrive/Documentos/NetBeansProjects/AllSport/web/assets/facturas/factura3853-26052023.pdf" width="100%" height="600px"></iframe>
                <embed src="C:/Users/alanr/OneDrive/Documentos/NetBeansProjects/AllSport/web/assets/facturas/factura3853-26052023.pdf" type="application/pdf" width="100%" height="600px" />
                <iframe src="/assets/facturas/factura3853-26052023.pdf" width="100%" height="600px"></iframe>
                -->
                <a href="Tienda" class="btn btn-secondary">Volver</a>
            </div>
        </div>
    <c:if test="${! empty error}">
        <div class="error mt-3">${error}</div>
    </c:if>
</body>
</html>

