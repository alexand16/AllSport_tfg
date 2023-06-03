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
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <title>Gracias por su compra</title>
    </head>
    <body>
        <div class="container">
            <h1 class="display-4">Gracias por su compra</h1>
            <div class="mt-4">           
                <!--<a href="<c:out value='${nombreFactura}'/>" class="btn btn-primary">Ver factura</a>
                <embed src="assets/facturas/factura5301-30052023.pdf" type="application/pdf" width="100%" height="600px"/>
                --><a href="Tienda" class="btn btn-secondary">Volver</a>
            </div>
        </div>
        <c:if test="${! empty error}">
            <div class="error mt-3">${error}</div>
        </c:if>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/pikaday.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/6.4.8/swiper-bundle.min.js"></script>
        <script src="assets/js/Profile-Edit-Form.js"></script>
        <script src="assets/js/Simple-Slider.js"></script>
        <script src="assets/js/theme.js"></script>
    </body>
</html>

