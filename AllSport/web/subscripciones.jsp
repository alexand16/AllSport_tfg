<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cuotas de Gimnasio</title>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:300,400,700&amp;display=swap">
        <link rel="stylesheet" href="assets/css/Roboto.css">
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
        <link rel="shortcut icon" href="favicon.ico" />
    </head>
    <style>
        .carousel-control-prev {
            left: -150px;
        }

        .carousel-control-next {
            right: -150px;
        }

        .carousel-control-prev-icon,
        .carousel-control-next-icon {
            background-color: black;
        }

        .carousel-control-prev,
        .carousel-control-next {
            background-color: transparent;
        }
    </style>
    <body>
        <!---------------------------------- Start Nav Header ------------------------------------------->
        <nav class="navbar navbar-dark navbar-expand-md bg-dark py-2" style="background: linear-gradient(62deg, var(--bs-red) 28%, var(--bs-pink)), var(--bs-red);">
            <div class="container">
                <a class="navbar-brand me-auto" href="index.jsp">
                    <img src="assets/img/logonegroRecortado.png" alt="Logo-AllSports" width="52px" height="52px"/>
                    <span class="fs-3 mt-5">&nbsp All Sport</span>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse ms-lg-5 ps-lg-5" id="navbarNav">
                    <div class="ms-lg-5">
                        <div class="ms-lg-5">
                            <div class="ms-lg-5">
                                <div class="ms-lg-5">
                                    <ul class="navbar-nav ms-lg-5">
                                        <li class="nav-item mt-1">
                                            <a class="nav-link" href="aboutUs.jsp">Sobre Nosotros</a>
                                        </li>
                                        <li class="nav-item mt-1">
                                            <a class="nav-link" href="Subscripciones">Subscripciones</a>
                                        </li>
                                        <li class="nav-item mt-1">
                                            <a class="nav-link" href="Blog">Blog</a>
                                        </li>
                                        <li class="nav-item mt-1 me-3">
                                            <a class="nav-link" href="Tienda">Tienda</a>
                                        </li>
                                        <li class="nav-item " style="margin-top: 2px;">
                                            <div style="margin-left: -15px;" id="navcol-5">
                                                <div class="dropdown ">
                                                    <a
                                                        href="#"
                                                        class="btn dropdown-toggle"
                                                        data-bs-toggle="dropdown"
                                                        aria-expanded="false"
                                                        >
                                                        <%--
                                                        verifica que el usuario no sea nulo.
                                                        Si es as�, verifica si la propiedad rutaImg del objeto usuario no est� vac�a.
                                                            Si esto es verdadero, se muestra la imagen junto a su nombre.
                                                            De lo contrario, se muestra el icono de persona predeterminado junto a su nombre.
                                                        Si usuario es nulo, se muestra el mismo icono de persona predeterminado.
                                                        --%>
                                                        <c:choose>
                                                            <c:when test="${usuario != null}">
                                                                <span class="text-light"><c:out value="${usuario.nombre}"/>&nbsp&nbsp</span>
                                                                <c:choose>
                                                                    <c:when test="${usuario.rutaImg != '' && usuario.rutaImg != null}">
                                                                        <img src="assets/imgClientes/<c:out value="${usuario.rutaImg}"/>" class="rounded-circle" alt="${usuario.rutaImg}" width="36" height="36"/>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="36" height="36" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                                                                        <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                                                                        <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                                                                        </svg>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <svg xmlns="http://www.w3.org/2000/svg" width="36" height="36" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                                                                <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                                                                <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                                                                </svg>
                                                            </c:otherwise>
                                                        </c:choose>                       
                                                    </a>
                                                    <%-- 
                                                    Muestra las opciones adecuadas a cada tipo de usuario | Anonimo | Registrado | Administrador |
                                                    --%>
                                                    <ul class="dropdown-menu">
                                                        <c:choose>
                                                            <c:when test="${usuario.tipoUsuario == 'Admin' || usuario.tipoUsuario == 'Normal'}">
                                                                <c:if test="${usuario.tipoUsuario == 'Admin'}">
                                                                    <li>
                                                                        <a class="dropdown-item" href="admin/administrar.jsp"
                                                                           ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-tools" viewBox="0 0 16 16">
                                                                            <path d="M1 0 0 1l2.2 3.081a1 1 0 0 0 .815.419h.07a1 1 0 0 1 .708.293l2.675 2.675-2.617 2.654A3.003 3.003 0 0 0 0 13a3 3 0 1 0 5.878-.851l2.654-2.617.968.968-.305.914a1 1 0 0 0 .242 1.023l3.27 3.27a.997.997 0 0 0 1.414 0l1.586-1.586a.997.997 0 0 0 0-1.414l-3.27-3.27a1 1 0 0 0-1.023-.242L10.5 9.5l-.96-.96 2.68-2.643A3.005 3.005 0 0 0 16 3c0-.269-.035-.53-.102-.777l-2.14 2.141L12 4l-.364-1.757L13.777.102a3 3 0 0 0-3.675 3.68L7.462 6.46 4.793 3.793a1 1 0 0 1-.293-.707v-.071a1 1 0 0 0-.419-.814L1 0Zm9.646 10.646a.5.5 0 0 1 .708 0l2.914 2.915a.5.5 0 0 1-.707.707l-2.915-2.914a.5.5 0 0 1 0-.708ZM3 11l.471.242.529.026.287.445.445.287.026.529L5 13l-.242.471-.026.529-.445.287-.287.445-.529.026L3 15l-.471-.242L2 14.732l-.287-.445L1.268 14l-.026-.529L1 13l.242-.471.026-.529.445-.287.287-.445.529-.026L3 11Z"/>
                                                                            </svg>
                                                                            Administrar</a
                                                                        >
                                                                    </li>
                                                                </c:if>
                                                                <li>
                                                                    <a class="dropdown-item" href="usuarioR/ListaDeEjercicios"
                                                                       ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-card-checklist" viewBox="0 0 16 16">
                                                                        <path d="M14.5 3a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h13zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13z"/>
                                                                        <path d="M7 5.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm-1.496-.854a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 1 1 .708-.708l.146.147 1.146-1.147a.5.5 0 0 1 .708 0zM7 9.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm-1.496-.854a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 0 1 .708-.708l.146.147 1.146-1.147a.5.5 0 0 1 .708 0z"/>
                                                                        </svg>
                                                                        Lista de ejercicios</a
                                                                    >
                                                                </li>
                                                                <li>
                                                                    <a class="dropdown-item" href="usuarioR/ConsultarPedido"
                                                                       ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box2" viewBox="0 0 16 16">
                                                                        <path d="M2.95.4a1 1 0 0 1 .8-.4h8.5a1 1 0 0 1 .8.4l2.85 3.8a.5.5 0 0 1 .1.3V15a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V4.5a.5.5 0 0 1 .1-.3L2.95.4ZM7.5 1H3.75L1.5 4h6V1Zm1 0v3h6l-2.25-3H8.5ZM15 5H1v10h14V5Z"/>
                                                                        </svg>
                                                                        Consultar Pedido</a
                                                                    >
                                                                </li>
                                                                <li>
                                                                    <a class="dropdown-item" href="usuarioR/EditarPerfil"
                                                                       ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pen" viewBox="0 0 16 16">
                                                                        <path d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001zm-.644.766a.5.5 0 0 0-.707 0L1.95 11.756l-.764 3.057 3.057-.764L14.44 3.854a.5.5 0 0 0 0-.708l-1.585-1.585z"/>
                                                                        </svg>
                                                                        Editar Perfil</a
                                                                    >
                                                                </li>
                                                                <li>
                                                                    <a class="dropdown-item" href="CerrarSesion"
                                                                       ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-right" viewBox="0 0 16 16">
                                                                        <path fill-rule="evenodd" d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0v2z"/>
                                                                        <path fill-rule="evenodd" d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
                                                                        </svg>
                                                                        Cerrar Sesi�n</a
                                                                    >
                                                                </li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <li>
                                                                    <a class="dropdown-item" href="Login"
                                                                       ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-in-right" viewBox="0 0 16 16">
                                                                        <path fill-rule="evenodd" d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0v-2z"/>
                                                                        <path fill-rule="evenodd" d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
                                                                        </svg>
                                                                        Inicia sesi�n</a
                                                                    >
                                                                </li>
                                                                <li>
                                                                    <a class="dropdown-item" href="Registro"
                                                                       ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-plus" viewBox="0 0 16 16">
                                                                        <path d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H1s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C9.516 10.68 8.289 10 6 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
                                                                        <path fill-rule="evenodd" d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"/>
                                                                        </svg>
                                                                        Registrate</a
                                                                    >
                                                                </li>
                                                                <li>
                                                                    <a class="dropdown-item" href="index.jsp"
                                                                       ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-right" viewBox="0 0 16 16">
                                                                        <path fill-rule="evenodd" d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0v2z"/>
                                                                        <path fill-rule="evenodd" d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
                                                                        </svg>
                                                                        Salir</a
                                                                    >
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </ul>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>     
        </nav>
        <!---------------------------------- End Nav Header ------------------------------------------->
        <div class="container my-5">
            <div class="container py-5">
                <h1 class=" my-5">Cuotas de Gimnasio</h1>
                <c:if test="${cliente == null}">
                    <h4><i>Es necesario estar registrado para poder subscribirte</i></h4>
                </c:if>

                <div id="cuotasCarousel" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">

                        <c:forEach var="cuota" items="${cuotas}" varStatus="status">
                            <c:if test="${status.index % 3 == 0}">
                                <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                                    <div class="row">
                                    </c:if>
                                    <c:if test="${!status.last}">
                                        <div class="col">
                                            <div class="card h-100">
                                                <div class="card-body flex-grow-0 p-4">
                                                    <span class="badge bg-primary text-uppercase mb-2" style="background: var(--bs-red);border-color: var(--bs-red);">${cuota.nombre}</span>
                                                    <h4 class="display-4 fw-bold card-title">${cuota.precio}&euro;<span class="fs-3 fw-normal text-muted">/mes</span></h4>
                                                </div>
                                                <div class="card-footer d-flex flex-column flex-grow-1 justify-content-between p-4">
                                                    <div>
                                                        <ul class="list-unstyled">
                                                            <li class="d-flex mb-2"><span class="bs-icon-xs bs-icon-rounded bs-icon-primary-light bs-icon me-2" style="background: rgba(255,14,28,0.2);"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-check-lg" style="color: var(--bs-red);">
                                                                    <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"></path>
                                                                    </svg></span><span>Acceso total a las pistas</span></li>
                                                            <li class="d-flex mb-2"><span class="bs-icon-xs bs-icon-rounded bs-icon-primary-light bs-icon me-2" style="background: rgba(255,14,28,0.2);"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-check-lg" style="color: var(--bs-red);">
                                                                    <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"></path>
                                                                    </svg></span><span>Dietas a medida</span></li>
                                                            <li class="d-flex mb-2"><span class="bs-icon-xs bs-icon-rounded bs-icon-primary-light bs-icon me-2" style="background: rgba(255,14,28,0.2);"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-check-lg" style="color: var(--bs-red);">
                                                                    <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"></path>
                                                                    </svg></span><span>Entrenamientos Personalizados</span></li>
                                                            <li class="d-flex mb-2"><span class="bs-icon-xs bs-icon-rounded bs-icon-primary-light bs-icon me-2" style="background: rgba(255,14,28,0.2);"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-check-lg" style="color: var(--bs-red);">
                                                                    <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"></path>
                                                                    </svg></span><span>Acceso a clases (zumba, etc.)</span></li>
                                                            <li class="d-flex mb-2"><span class="bs-icon-xs bs-icon-rounded bs-icon-primary-light bs-icon me-2" style="background: rgba(255,14,28,0.2);"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-check-lg" style="color: var(--bs-red);">
                                                                    <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"></path>
                                                                    </svg></span><span>Online Trainer</span></li>
                                                        </ul>
                                                    </div>
                                                    <c:if test="${cliente != null}">
                                                        <c:if test="${cliente.cuota.id == 100}">
                                                            <form action="usuarioR/ProcesarSub" method="post" class="mt-4">
                                                                <input type="hidden" name="cuotaId" value="${cuota.id}">
                                                                <button type="submit" class="btn btn-danger btn-sm">Comprar</button>
                                                            </form>
                                                        </c:if>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test="${status.index % 3 == 2 || status.index == cuotas.size() - 1}">
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                    <a class="carousel-control-prev align-self-start" href="#cuotasCarousel" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    </a>
                    <a class="carousel-control-next align-self-end" href="#cuotasCarousel" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    </a>
                </div>
            </div>
        </div>
        <!---------------------------------- Start footer ------------------------------------------->
        <footer class="page-footer" style="background: linear-gradient(50deg, var(--bs-red) 33%, var(--bs-pink));">
            <div class="container">
                <div class="links">
                    <a class="nav-link" style="color: rgb(255,255,255)" href="aboutUs.jsp">Sobre Nosotros</a><a class="nav-link" style="color: rgb(255,255,255)" href="Blog">Blog</a><a class="nav-link" style="color: rgb(255,255,255)" href="Tienda">Tienda</a>
                    <div class="social-icons"><a href=https://m.facebook.com/profile.php?id=100049737866622&_rdr#" style="background: var(--bs-black);"><i class="icon ion-social-facebook"></i></a><a href="https://www.instagram.com/allsport_santiponce/" style="background: var(--bs-black);"><i class="icon ion-social-instagram-outline"></i></a><a href="https://api.whatsapp.com/send?phone=652982078" style="background: var(--bs-black);"><i class="icon ion-social-whatsapp"></i></a></div>
                </div>
            </div>
        </footer>
        <!---------------------------------- End footer ------------------------------------------->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/pikaday.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/6.4.8/swiper-bundle.min.js"></script>
        <script src="assets/js/Profile-Edit-Form.js"></script>
        <script src="assets/js/Simple-Slider.js"></script>
        <script src="assets/js/theme.js"></script>
        <script>
            $(document).ready(function () {
                $('#cuotasCarousel').carousel();
            });
        </script>

    </body>





</html>

