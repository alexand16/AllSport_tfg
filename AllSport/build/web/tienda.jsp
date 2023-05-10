<%-- 
    Document   : tienda
    Created on : 10 may 2023, 13:29:00
    Author     : alanr
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>Tienda</title>
    </head>
    <!---------------------------------- Start Main Content (Shop)------------------------------------------->
    <c:forEach items="${productos}" var="p">
        <c:out value="${p.nombreProducto}"/>
        <c:out value="${p.descripcion}"/>
        <c:out value="${p.precioEnPuntos}"/>
        <c:out value="${p.precio}"/>
        <c:out value="${p.cantidadStock}"/>
        <img src="<c:out value="${p.imgProducto}"/>" alt="<c:out value="${p.nombreProducto}"/>" width="52px" height="52px"/> 
        <hr>
    </c:forEach>
    <!---------------------------------- End Main Content------------------------------------------->

</body>

</html>
