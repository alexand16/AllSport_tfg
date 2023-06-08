<%-- 
    Document   : editarUsuario
    Created on : 20 abr 2023, 15:57:20
    Author     : alanr
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Editar usuario</title>
        <link rel="stylesheet" href="../assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:300,400,700&amp;display=swap">
        <link rel="stylesheet" href="../assets/css/Roboto.css">
        <link rel="stylesheet" href="../assets/fonts/fontawesome-all.min.css">
        <link rel="stylesheet" href="../assets/fonts/font-awesome.min.css">
        <link rel="stylesheet" href="../assets/fonts/ionicons.min.css">
        <link rel="stylesheet" href="../assets/fonts/fontawesome5-overrides.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/css/pikaday.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/6.4.8/swiper-bundle.min.css">
        <link rel="stylesheet" href="../assets/css/Navbar-Right-Links-Dark-icons.css">
        <link rel="stylesheet" href="../assets/css/Pretty-Registration-Form-.css">
        <link rel="stylesheet" href="../assets/css/Pricing-Yearly--Monthly-badges.css">
        <link rel="stylesheet" href="../assets/css/Profile-Edit-Form-styles.css">
        <link rel="stylesheet" href="../assets/css/Profile-Edit-Form.css">
        <link rel="stylesheet" href="../assets/css/Shopping-Grid.css">
        <link rel="stylesheet" href="../assets/css/Simple-Slider-Simple-Slider.css">
        <link rel="shortcut icon" href="../favicon.ico" />
    </head>
    <body>
        <main class="page cv-page">
            <section style="max-width: 840px; margin: auto;box-shadow: 0px 2px 10px rgba(0,0,0,.1);"class=" mt-5">
                <div class="container profile profile-view" id="profile">
                    <div class="row">
                        <div class="col-md-12 alert-col relative">
                            <div class="alert alert-info alert-dismissible absolue center" role="alert"><button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button><span>Profile save with success</span></div>
                        </div>
                    </div>
                    <form style="margin-bottom: 20px;" method="POST" action="EditarPerfil" enctype="multipart/form-data">
                        <div class="row profile-row">
                            <div class="col-md-4 relative" style="padding-left: 15px;padding-right: 19px;">
                                <c:choose>
                                    <c:when test="${usuario.rutaImg != '' && usuario.rutaImg != null}">
                                        <img id="imagenPerfil" class="rounded-circle me-5" src="../assets/imgClientes/${cliente.rutaImg}" width="250px" height="250px" alt="${cliente.rutaImg}" />
                                    </c:when>
                                    <c:otherwise>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="250px" height="250px" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                                        <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                                        <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                                        </svg>
                                    </c:otherwise>
                                </c:choose>
                                <input class="form-control form-control" type="file" name="imagenCliente" id="file" accept=".png, .jpg, .jpeg, .gif" style="margin-right: 32px;padding-right: 0px;padding-left: 10px; display: none;">
                                <label for="file" class="btn btn-secondary ms-5 mt-3" style="cursor: pointer;">Cambiar Imagen</label>
                            </div>
                            <div class="col-md-8">
                                <h1>Perfil</h1>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-12 col-md-6">
                                        <div class="form-group mb-3">
                                            <label for="nombre" class="form-label">Nombre:</label>
                                            <input class="form-control" id="nombre" type="text" name="nombre" value="${cliente.nombre}" required>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 col-md-6">
                                        <div class="form-group mb-3">
                                            <label for="apellidos" class="form-label">Apellidos:</label>
                                            <input class="form-control" id="nombre" type="text" name="apellidos" value="${cliente.apellidos}" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group mb-3">
                                    <label for="email" class="form-label">Email:</label>
                                    <input class="form-control" id="email" type="email" name="email" value="${cliente.email}" required>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12 col-md-6">
                                        <div class="form-group mb-3">
                                            <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento:</label>
                                            <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" value="${cliente.fechaNacimiento}" required>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 col-md-6">
                                        <div class="form-group mb-3">
                                            <label for="telefono" class="form-label">Telefono:</label>
                                            <input class="form-control" id="telefono" type="text" name="telefono" value="${cliente.telefono}" required pattern="[0-9]{9}" title="El campo de teléfono debe contener 9 dígitos numéricos.">

                                        </div>
                                    </div>
                                </div>
                                <hr>
                                <span>Tipo de subscripción: ${usuario.cuota.nombre} &nbsp; &nbsp; | &nbsp; &nbsp;Estado: ${usuario.estadoMembresia}</span>
                                <hr>
                                <div class="row">
                                    <div class="col-md-12 content-right">
                                        <button class="btn btn-primary form-btn" type="submit">GUARDAR</button>
                                        <a href="../index.jsp" class="btn btn-danger">VOLVER
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <i>La imagen solo admite los formatos png, jpg, jepg y gif*</i>
                <c:if test="${not empty error}">    
                    <div class="alert alert-danger">
                        ${error}
                    </div>
                </c:if>
            </section>
        </main>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            $(document).ready(function () {
                // Obtener el campo input file
                var inputImagen = document.getElementById('file');

                // Agregar un evento de escucha al campo input file
                inputImagen.addEventListener('change', function (e) {
                    // Obtener el archivo seleccionado
                    var archivo = e.target.files[0];

                    // Crear un objeto URL con el archivo seleccionado
                    var urlImagen = URL.createObjectURL(archivo);

                    // Actualizar el atributo src del elemento img con la URL de la imagen
                    $('#imagenPerfil').attr('src', urlImagen);
                });
            });
        </script>
        <script src="../assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/pikaday.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/6.4.8/swiper-bundle.min.js"></script>
    </body>
</html>