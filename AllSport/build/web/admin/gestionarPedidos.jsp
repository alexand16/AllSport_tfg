<%-- 
    Document   : getionarPedidos
    Created on : 30 may 2023, 9:58:46
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
            <ul id="items-cesta" class="list-group">
                <c:forEach items="${pedidosCompletos}" var="pc">
                    <li class="list-group-item d-flex align-items-center justify-content-between">
                        <span class="d-flex align-items-center justify-content-between"><c:out value='${pc.cliente.nombre}'/> | <c:out value='${pc.total}'/>&euro; | <c:out value='${pc.fechaPedido}'/> | <c:out value='${pc.estadoPedido}'/>&nbsp;&nbsp;&nbsp;&nbsp;
                            <form action="GestionarPedidos" method="POST">
                                <input type="hidden" name="pedidoId" value="${pc.id}">
                                <select name="estado">
                                    <option value="entregado" <c:if test="${pc.estadoPedido == 'entregado'}">selected</c:if>>Entregado</option>
                                    <option value="en proceso" <c:if test="${pc.estadoPedido == 'en proceso'}">selected</c:if>>En proceso</option>
                                    <option value="en envio" <c:if test="${pc.estadoPedido == 'en envio'}">selected</c:if>>En envío</option>
                                </select>
                                <input type="submit" value="Confirmar Estado" class="btn btn-success m-2">
                            </form>
                        </span>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </body>
</html>
