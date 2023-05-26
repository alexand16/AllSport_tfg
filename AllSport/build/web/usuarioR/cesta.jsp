<%-- 
    Document   : cesta
    Created on : 17 may 2023, 10:07:44
    Author     : alanr
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cesta de compras</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <div class="jumbotron mt-4">
                <h2 class="display-4">Cesta de compras</h2>
                <c:choose>
                    <c:when test="${cesta.size() > 0}">

                        <ul id="items-cesta" class="list-group">
                            <c:forEach items="${productosPedidos}" var="pp">
                                <li class="list-group-item d-flex align-items-center justify-content-between">
                                    <img src="<c:out value='${pp.producto.imgProducto}'/>" alt="<c:out value='${pp.producto.nombreProducto}'/>" width="100px" height="100px" class="mr-3">
                                    <span class="d-flex align-items-center justify-content-between"><c:out value='${pp.producto.nombreProducto}'/> | <c:out value='${(pp.producto.precio) * pp.cantidad}'/>&euro; &nbsp;&nbsp;&nbsp;&nbsp;
                                        <form action="Cesta" method="POST">
                                            <input type="hidden" name="productId" value="${pp.producto.id}">
                                            <input type="number" name="quantity" value="${pp.cantidad}" min="1" max="${pp.producto.cantidadStock}" class="ms-5">
                                            <input type="submit" value="Confirmar Cantidad" class="btn btn-success m-2">
                                        </form>
                                        <form action="EliminarProducto" method="POST" class="">
                                            <input type="hidden" name="productId" value="${pp.id}">
                                            <input type="submit" value="Eliminar" class="btn btn-danger m-2">
                                        </form>
                                    </span>
                                </li>
                            </c:forEach>
                            <li class="list-group-item d-flex justify-content-end">
                                Total a pagar: <c:out value="${totalAPagar}"/>&euro;
                            </li>
                        </ul>
                        <form action="GraciasPorSuCompra" method="post">
                            <input type="submit" value="Procesar cesta" class="btn btn-primary mt-3">
                            <a href="Tienda" class="btn btn-secondary mt-3">Volver</a>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <li class="list-group-item">
                            La cesta está vacía, añade un producto a tu cesta en la tienda <a href="Tienda" class="btn btn-secondary">Volver</a>
                        </li>
                        </ul>
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                ${error}
            </div>
        </c:if>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>


