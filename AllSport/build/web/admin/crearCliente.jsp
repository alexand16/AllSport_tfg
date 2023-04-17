<%-- 
    Document   : crearCliente
    Created on : 17 abr 2023, 12:48:37
    Author     : alanr
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Crear Departamento</title>
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
        <div class="container">
            <h1>Crear Departamento</h1>
            <br>
            <form>
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre:</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" value="${nombre}">
                </div>
                <div class="mb-3">
                    <label for="apellidos" class="form-label">Apellidos</label>
                    <input type="text" class="form-control" id="apellidos" name="apellidos" value="${apellidos}">
                </div>
                <div class="mb-3">
                    <label for="telefono" class="form-label">Telefono</label>
                    <input type="text" class="form-control" id="telefono" name="telefono" value="${telefono}">
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="nombre" value="${email}">
                </div>
                <div class="mb-3">
                    <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento:</label>
                    <input type="date" class="form-control" id="email" name="fechaNacimiento" value="${fechaNacimiento}">
                </div>
                <div class="mb-3">
                    <label for="observaciones" class="form-label">Observaciones</label>
                    <input type="text" class="form-control" id="observaciones" name="observaciones" value="${observaciones}">
                </div>
                <div class="mb-3">
                    <label for="tipoUsuario" class="form-label">Tipo de usuario:</label>
                    <select class="form-select" id="tipoUsuario" name="tipoUsuario">
                        <option value="normal">Normal</option>
                        <option value="admin">Admin</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="tipoCuota" class="form-label">Tipo de Cuota:</label>
                    <select class="form-select" id="tipoCuota" name="tipoCuota">
                        <option value="a">a</option>
                        <option value="b">b</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">Crear</button>
            </form>

            <br>

            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    <c:out value="${error}"/>
                </div>
            </c:if>
        </div>
    </body>
</html>
