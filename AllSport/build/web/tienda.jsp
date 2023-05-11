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
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:300,400,700&amp;display=swap">
        <link rel="stylesheet" href="assets/fonts/fontawesome-all.min.css">
        <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
        <link rel="stylesheet" href="assets/fonts/ionicons.min.css">
        <link rel="stylesheet" href="assets/fonts/fontawesome5-overrides.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/css/pikaday.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/6.4.8/swiper-bundle.min.css">
        <link rel="stylesheet" href="assets/css/Navbar-Right-Links-Dark-icons.css">
        <link rel="stylesheet" href="assets/css/Pretty-Registration-Form-.css">
        <link rel="stylesheet" href="assets/css/Pricing-Yearly--Monthly-badges.css">
        <link rel="stylesheet" href="assets/css/Profile-Edit-Form-styles.css">
        <link rel="stylesheet" href="assets/css/Profile-Edit-Form.css">
        <link rel="stylesheet" href="assets/css/Shopping-Grid.css">
        <link rel="stylesheet" href="assets/css/Simple-Slider-Simple-Slider.css">
    </head>
    <!---------------------------------- Start Main Content (Shop)------------------------------------------->
    <%-- comment 
    <c:forEach items="${productos}" var="p">
        <c:out value="${p.nombreProducto}"/>
        <c:out value="${p.descripcion}"/>
        <c:out value="${p.precioEnPuntos}"/>
        <c:out value="${p.precio}"/>
        
        <img src="<c:out value="${p.imgProducto}"/>" alt="<c:out value="${p.nombreProducto}"/>" width="52px" height="52px"/> 
        <hr>
    </c:forEach>
    --%>
    <div class="shopping-grid">
        <div class="container">
            <h3 class="" align="center">All Sports Shop</h3>
            <div class="row">
                <c:forEach items="${productos}" var="p">
                    <div class="col-md-3 col-sm-6">
                        <div class="product-grid7 mb-4">
                            <a class="text-decoration-none text-dark" href="https://www.youtube.com/">
                                <div class="product-image7">
                                    <img class="pic-1" src="<c:out value="${p.imgProducto}"/>" alt="<c:out value="${p.nombreProducto}"/>" width="52px" height="52px"/> 
                                    <img class="pic-2" src="<c:out value="${p.imgProducto}"/>" alt="<c:out value="${p.nombreProducto}"/>" width="52px" height="52px"/> 
                                    <span class="product-new-label stock"> Unidades: <c:out value="${p.cantidadStock}"/></span>
                                </div>
                                <div class="product-content ">
                                    <h3 class="title"><c:out value="${p.nombreProducto}"/></h3> 
                                    <div class="price">
                                        <c:out value="${p.precio}"/>? / <c:out value="${p.precioEnPuntos}"/><small class="text-muted"> Puntos</small>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>

                </c:forEach>
            </div>
        </div>
    </div>


    <!---------------------------------- End Main Content------------------------------------------->
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/pikaday.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/6.4.8/swiper-bundle.min.js"></script>
    <script src="assets/js/Profile-Edit-Form.js"></script>
    <script src="assets/js/Simple-Slider.js"></script>
    <script src="assets/js/theme.js"></script>
</body>

</html>
