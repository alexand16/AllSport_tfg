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
    </head>
    <body>
        <main class="container py-4">
            <div class="row">
                <div class="col-md-8">
                    <h2 class="mb-4">Últimas publicaciones</h2>
                    <c:forEach items="${posts}" var="p">
                        <article class="mb-4 ">
                            <h3 class="h4"><c:out value="${p.titulo}"/></h3>
                            <p class="text small mb-0">
                                Publicado el <c:out value="${p.fechaCreacion}"/> por <strong><c:out value="${p.administrador.nombre}"/></strong>&nbsp
                                <c:choose>
                                    <c:when test="${p.administrador.rutaImg != ''}">
                                        <img src="assets/imgClientes/<c:out value="${p.administrador.rutaImg}"/>" alt="${p.administrador.rutaImg}" class="rounded-circle me-2" width="48px" height="48"/>
                                    </c:when>
                                    <c:otherwise>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="42" height="42" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                                        <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                                        <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                                        </svg>
                                    </c:otherwise>
                                </c:choose>
                            </p>
                            <p><c:out value="${p.contenido}"/></p>
                            <div class="mb-3">
                                <form action="CrearRespuesta" method="post">
                                    <input type="hidden" name="id" value="${p.id}">
                                    <button type="submit" class="btn btn-primary btn-sm">Responder 
                                    </button>
                                </form>
                            </div>
                            <hr>
                            <div class="comments container ms-3">
                                <h4 class="h5 mb-4">Comentarios</h4>
                                <c:forEach items="${respuestas}" var="r">
                                    <c:if test="${p.id == r.post.id}">
                                        <p class="encabezado-respuesta">
                                            Publicado el <c:out value="${r.fechaCreacion}"/> por <strong><c:out value="${r.usuario.nombre}"/>
                                                &nbsp
                                                <c:choose>
                                                    <c:when test="${r.usuario.rutaImg != ''}">
                                                        <img src="assets/imgClientes/<c:out value="${r.usuario.rutaImg}"/>" alt="${r.usuario.rutaImg}" class="rounded-circle" width="48px" height="48"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="42" height="42" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                                                        <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                                                        <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                                                        </svg>
                                                    </c:otherwise>
                                                </c:choose>
                                            </strong><br>
                                            <c:out value="${r.contenido}"/>
                                        </p>
                                    </c:if>                  
                                </c:forEach>
                            </div><br>
                        </article>
                    </c:forEach>
                    </main>
                    </body>
                    </html>
