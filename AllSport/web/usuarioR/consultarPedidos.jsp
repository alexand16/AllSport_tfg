<%-- 
    Document   : consultarPedidos
    Created on : 30 may 2023, 11:08:18
    Author     : alanr
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    </head>
    <body>
        <div class="container">
            <h1>Mis Pedidos:</h1>
            <c:choose>
                <c:when test="${pedidosCompletos.size() > 0}">
                    <ul id="items-cesta" class="list-group">
                        <c:forEach items="${pedidosCompletos}" var="pc">
                            <li class="list-group-item d-flex align-items-center justify-content-between">
                                <span class="d-flex align-items-center justify-content-between"><c:out value='${pc.cliente.nombre}'/> | <c:out value='${pc.total}'/>&euro; | <c:out value='${pc.fechaPedido}'/> | <c:out value='${pc.estadoPedido}'/>&nbsp;&nbsp;&nbsp;&nbsp;
                                </span>
                            </li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    <li class="list-group-item">
                        No has hecho ningun pedido, para crear un pedido debes hacerlo desde la tienda<a href="RecargarCliente" class="btn btn-secondary">Volver</a>
                    </li>
                </c:otherwise>
            </c:choose>

        </div>
    </body>
</html>
