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
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <c:choose>
            <c:when test="${usuario != null}">
                <span class="text-light"><c:out value="${usuario.nombre}"/>&nbsp&nbsp</span>
                <c:choose>
                    <c:when test="${usuario.rutaImg != '' && usuario.rutaImg != null}">
                        <img src="assets/imgClientes/<c:out value="${usuario.rutaImg}"/>" class="rounded-circle" alt="alt" width="48px" height="48"/>
                    </c:when>
                    <c:otherwise>
                        <svg xmlns="http://www.w3.org/2000/svg" width="36" height="36" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                        <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                        <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                        </svg>
                    </c:otherwise>
                </c:choose>
                <span class="text-light">&nbsp/&nbsp<c:out value="${usuario.puntos}"/> Puntos</span>        
            </c:when>
            <c:otherwise>
                <svg xmlns="http://www.w3.org/2000/svg" width="36" height="36" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                </svg>
            </c:otherwise>
        </c:choose> 
    </body>
</html>
