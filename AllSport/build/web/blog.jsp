<%-- 
    Document   : blog
    Created on : 29 abr 2023, 12:11:08
    Author     : alanr
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>JSP Page</title>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/Roboto.css">
        <link rel="stylesheet" href="assets/fonts/ionicons.min.css">
        <link rel="stylesheet" href="assets/fonts/fontawesome5-overrides.min.css">
        <link rel="stylesheet" href="assets/css/Navbar-Right-Links-Dark-icons.css">
        <link rel="stylesheet" href="assets/css/Pretty-Registration-Form-.css">
        <link rel="stylesheet" href="assets/css/Pricing-Yearly--Monthly-badges.css">
        <link rel="stylesheet" href="assets/css/Profile-Edit-Form-styles.css">
        <link rel="stylesheet" href="assets/css/Profile-Edit-Form.css">
        <link rel="stylesheet" href="assets/css/Shopping-Grid.css">
        <link rel="stylesheet" href="assets/css/Simple-Slider-Simple-Slider.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:300,400,700&amp;display=swap">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/css/pikaday.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/6.4.8/swiper-bundle.min.css">

    </head>
    <body>
        <main class="container">
            <h1>Últimas publicaciones</h1>
            <c:forEach items="${posts}" var="p">
                <article class="container">                   
                    <h2 class=""><c:out value="${p.titulo}"/></h2>
                    <p class="">
                        Publicado el <c:out value="${p.fechaCreacion}"/> por <strong><c:out value="${p.administrador.nombre}"/>
                            &nbsp
                            <c:choose>
                                <c:when test="${p.administrador.rutaImg != ''}">
                                    <img src="assets/imgClientes/<c:out value="${p.administrador.rutaImg}"/>" alt="${p.administrador.rutaImg}" class="rounded-circle" width="48px" height="48"/>
                                </c:when>
                                <c:otherwise>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="42" height="42" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                                    <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                                    <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                                    </svg>
                                </c:otherwise>
                            </c:choose>
                        </strong>
                    </p>
                    <div class="my-3">
                        <c:out value="${p.contenido}"/>
                    </div>
                    <div class="">
                        <a href="Responder" class="btn btn-danger">Responder</a>
                    </div><br><br>
                </article>
            </c:forEach>
        </main>
    </body>
</html>
