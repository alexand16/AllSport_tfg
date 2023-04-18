<%-- 
    Document   : editarCliente
    Created on : 17 abr 2023, 12:48:59
    Author     : alanr
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Editar Usuario</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
            crossorigin="anonymous"
            />
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
            crossorigin="anonymous"
        ></script>
    </head>
    <body>
        <div class="">
            <div class="row">
                <div class="col-md-6 offset-md-3 mt-5 card p-3">
                    <h1 class="text-center">Editar Usuario</h1>
                    <br>
                    <form class="">
                        <input type="hidden" name="id" value="${cliente.id}">
                        <div class="form-group">
                            <label for="nombre">Nombre:</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" value="${cliente.nombre}" required>
                        </div>
                        <div class="form-group">
                            <label for="apellidos">Apellidos:</label>
                            <input type="text" class="form-control" id="apellidos" name="apellidos" value="${cliente.apellidos}" required>
                        </div>
                        <div class="form-group">
                            <label for="estadoMembresia">Estado:</label>
                            <input type="text" class="form-control" id="estadoMembresia" name="estadoMembresia" value="${cliente.estadoMembresia}" required>
                        </div>
                        <div class="form-group">
                            <label for="fechaPago">Fecha de Pago:</label>
                            <input type="date" class="form-control" id="fechaPago" name="fechaPago" value="${cliente.fechaPago}" required>
                        </div>
                        <label for="tipoCuota" class="form-label">Tipo de Cuota:</label>
                        <select class="form-select" id="tipoCuota" name="tipoCuota">
                            <c:forEach items="${cuotas}" var="tipoCuota">
                                <c:choose>
                                    <c:when test="${tipoCuota.id eq cliente.cuota.id}">
                                        <option value="${tipoCuota.id}" selected>${tipoCuota.nombre} --> ${tipoCuota.precio}$</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${tipoCuota.id}">${tipoCuota.nombre} --> ${tipoCuota.precio}$</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>                                  
                        </select>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" id="email" name="email" value="${cliente.email}" required>
                        </div>
                        <div class="form-group">
                            <label for="telefono">Telefono:</label>
                            <input type="text" class="form-control" id="telefono" name="telefono" value="${cliente.telefono}" required>
                        </div>
                        <div class="form-group">
                            <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                            <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" value="${cliente.fechaNacimiento}" required>
                        </div>
                        <div class="form-group">
                            <label for="puntos">Puntos:</label>
                            <input type="text" class="form-control" id="puntos" name="puntos" value="${cliente.puntos}" required>
                        </div>
                        <div class="form-group">
                            <label for="observaciones">Observaciones:</label>
                            <input type="text" class="form-control" id="observaciones" name="observaciones" value="${cliente.observaciones}" required>
                        </div>
                        <br/>
                        <button type="submit" class="btn btn-primary">Enviar</button> 
                        <a href="MenuClientes" class="btn btn-secondary">Volver
                        </a>
                    </form>
                    <c:if test="${! empty error}">
                        <div class="error mt-3">${error}</div>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
