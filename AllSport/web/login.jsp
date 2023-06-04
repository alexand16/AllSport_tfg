<%-- 
    Document   : login
    Created on : 21 abr 2023, 8:43:38
    Author     : alanr
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Inicio de sesión</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    </head>
    <body>
        <div class="container pt-5">
            <div class="row justify-content-center align-items-center mt-5 pt-5">
                <div class="col-md-4 pt-5 mt-5">
                    <h1 class="text-center mb-4">Iniciar sesión</h1>
                    <form method="post" class="mb-3">
                        <div class="mb-3">
                            <label for="usuario" class="form-label">Usuario:</label>
                            <input type="text" name="usuario" value="${usuario}" class="form-control" placeholder="Introduce tu nombre de usuario" required>
                        </div>
                        <div class="mb-3">
                            <label for="contraseña" class="form-label">Contraseña:</label>
                            <input type="password" name="contraseña" value="${contraseña}" class="form-control" placeholder="Introduce tu contraseña" required>
                        </div>
                        <button type="submit" class="btn btn-primary " style="margin: 1em 2em 1em 0em; background-color: var(--bs-red); border: none;">Iniciar sesión</button>
                        <a href="index.jsp" class="btn btn-secondary">Volver
                        </a>
                        <!--<a href="register.html" style="color: var(--bs-red);" class="">¿No tienes cuenta? Registrate</a>
                        --></form>
                        <c:if test="${not empty error}">
                        <div class="alert alert-danger">
                            ${error}
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/pikaday.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/6.4.8/swiper-bundle.min.js"></script>
        <script src="assets/js/Profile-Edit-Form.js"></script>
        <script src="assets/js/Simple-Slider.js"></script>
        <script src="assets/js/theme.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
        <script src="assets/js/validateLogin.js"></script>
    </body>
</html>
