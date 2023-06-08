<%-- 
    Document   : menuClientes
    Created on : 17 abr 2023, 9:50:15
    Author     : alanr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Clientes</title>
        <link rel="stylesheet" href="../assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="../assets/css/Navbar-Right-Links-Dark-icons.css">
        <link rel="stylesheet" href="../assets/css/Profile-Edit-Form-styles.css">
        <link rel="stylesheet" href="../assets/css/Profile-Edit-Form.css">
        <link rel="stylesheet" href="../assets/css/Simple-Slider-Simple-Slider.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:300,400,700&amp;display=swap">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.11.2/datatables.min.css"/>
        <script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.11.2/datatables.min.js"></script>
        <link rel="shortcut icon" href="../favicon.ico" />
    </head>
    <body>
        <!---------------------------------- Start Nav Header ------------------------------------------->
        <nav class="navbar navbar-dark navbar-expand-md bg-dark py-2" style="background: linear-gradient(62deg, var(--bs-red) 28%, var(--bs-pink)), var(--bs-red);">
            <div class="container">
                <a class="navbar-brand me-auto" href="../index.jsp">
                    <img src="../assets/img/logonegroRecortado.png" alt="Logo-AllSports" width="52px" height="52px"/>
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
                                            <a class="nav-link" href="../aboutUs.jsp">Sobre Nosotros</a>
                                        </li>
                                        <li class="nav-item mt-1">
                                            <a class="nav-link" href="../Subscripciones">Subscripciones</a>
                                        </li>
                                        <li class="nav-item mt-1">
                                            <a class="nav-link" href="../Blog">Blog</a>
                                        </li>
                                        <li class="nav-item mt-1 me-3">
                                            <a class="nav-link" href="../Tienda">Tienda</a>
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
                                                        Si es así, verifica si la propiedad rutaImg del objeto usuario no está vacía.
                                                            Si esto es verdadero, se muestra la imagen junto a su nombre.
                                                            De lo contrario, se muestra el icono de persona predeterminado junto a su nombre.
                                                        Si usuario es nulo, se muestra el mismo icono de persona predeterminado.
                                                        --%>
                                                        <c:choose>
                                                            <c:when test="${usuario != null}">
                                                                <span class="text-light"><c:out value="${usuario.nombre}"/>&nbsp&nbsp</span>
                                                                <c:choose>
                                                                    <c:when test="${usuario.rutaImg != '' && usuario.rutaImg != null}">
                                                                        <img src="../assets/imgClientes/<c:out value="${usuario.rutaImg}"/>" class="rounded-circle" alt="${usuario.rutaImg}" width="36" height="36"/>
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
                                                                    <a class="dropdown-item" href="../usuarioR/ListaDeEjercicios"
                                                                       ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-card-checklist" viewBox="0 0 16 16">
                                                                        <path d="M14.5 3a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h13zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13z"/>
                                                                        <path d="M7 5.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm-1.496-.854a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 1 1 .708-.708l.146.147 1.146-1.147a.5.5 0 0 1 .708 0zM7 9.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm-1.496-.854a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 0 1 .708-.708l.146.147 1.146-1.147a.5.5 0 0 1 .708 0z"/>
                                                                        </svg>
                                                                        Lista de ejercicios</a
                                                                    >
                                                                </li>
                                                                <li>
                                                                    <a class="dropdown-item" href="../usuarioR/ConsultarPedido"
                                                                       ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box2" viewBox="0 0 16 16">
                                                                        <path d="M2.95.4a1 1 0 0 1 .8-.4h8.5a1 1 0 0 1 .8.4l2.85 3.8a.5.5 0 0 1 .1.3V15a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V4.5a.5.5 0 0 1 .1-.3L2.95.4ZM7.5 1H3.75L1.5 4h6V1Zm1 0v3h6l-2.25-3H8.5ZM15 5H1v10h14V5Z"/>
                                                                        </svg>
                                                                        Consultar Pedido</a
                                                                    >
                                                                </li>
                                                                <li>
                                                                    <a class="dropdown-item" href="../usuarioR/EditarPerfil"
                                                                       ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pen" viewBox="0 0 16 16">
                                                                        <path d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001zm-.644.766a.5.5 0 0 0-.707 0L1.95 11.756l-.764 3.057 3.057-.764L14.44 3.854a.5.5 0 0 0 0-.708l-1.585-1.585z"/>
                                                                        </svg>
                                                                        Editar Perfil</a
                                                                    >
                                                                </li>
                                                                <li>
                                                                    <a class="dropdown-item" href="../CerrarSesion"
                                                                       ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-right" viewBox="0 0 16 16">
                                                                        <path fill-rule="evenodd" d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0v2z"/>
                                                                        <path fill-rule="evenodd" d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
                                                                        </svg>
                                                                        Cerrar Sesión</a
                                                                    >
                                                                </li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <li>
                                                                    <a class="dropdown-item" href="../Login"
                                                                       ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-in-right" viewBox="0 0 16 16">
                                                                        <path fill-rule="evenodd" d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0v-2z"/>
                                                                        <path fill-rule="evenodd" d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
                                                                        </svg>
                                                                        Inicia sesión</a
                                                                    >
                                                                </li>
                                                                <li>
                                                                    <a class="dropdown-item" href="../Registro"
                                                                       ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-plus" viewBox="0 0 16 16">
                                                                        <path d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H1s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C9.516 10.68 8.289 10 6 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
                                                                        <path fill-rule="evenodd" d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"/>
                                                                        </svg>
                                                                        Registrate</a
                                                                    >
                                                                </li>
                                                                <li>
                                                                    <a class="dropdown-item" href="../index.jsp"
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
        <div class=" m-5">
            <h1>Clientes</h1>
            <div style="overflow-y: scroll; height: 550px; border: 1px solid black;">
                <table id="clientesTable" class="table table-striped">
                    <thead>
                        <tr>
                            <th style="position: sticky; top: 0; background-color: white;">Nombre</th>
                            <th style="position: sticky; top: 0; background-color: white;">Apellido</th>
                            <th style="position: sticky; top: 0; background-color: white;">Estado</th>
                            <th style="position: sticky; top: 0; background-color: white;">Fecha de Pago</th>
                            <th style="position: sticky; top: 0; background-color: white;">Tipo de Cuota</th>
                            <th style="position: sticky; top: 0; background-color: white;">Email</th>
                            <th style="position: sticky; top: 0; background-color: white;">Telefono</th>
                            <th style="position: sticky; top: 0; background-color: white;">Fecha de Nacimiento</th>
                            <th style="position: sticky; top: 0; background-color: white;">Puntos</th>
                            <th style="position: sticky; top: 0; background-color: white;">Observaciones</th>
                            <th style="position: sticky; top: 0; background-color: white;"></th>
                            <th style="position: sticky; top: 0; background-color: white;"></th>
                        </tr>
                    </thead>
                    <tbody style="overflow-y: scroll; height: 100%;">
                        <c:forEach items="${clientes}" var="c">
                            <tr>
                                <td><c:out value="${c.nombre}"/></td>
                                <td><c:out value="${c.apellidos}"/></td>
                                <td><c:out value="${c.estadoMembresia}"/></td>
                                <td><c:out value="${c.fechaPago}"/></td>
                                <td><c:out value="${c.cuota.nombre}"/></td>
                                <td><c:out value="${c.email}"/></td>
                                <td><c:out value="${c.telefono}"/></td>
                                <td><c:out value="${c.fechaNacimiento}"/></td>
                                <td><c:out value="${c.puntos}"/></td>
                                <td><c:out value="${c.observaciones}"/></td>
                                <td>
                                    <form action="EditarCliente" method="post">
                                        <input type="hidden" name="id" value="${c.id}">
                                        <button type="submit" class="btn btn-primary">Editar 
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-person-gear" viewBox="0 0 16 16">
                                            <path d="M11 5a3 3 0 1 1-6 0 3 3 0 0 1 6 0ZM8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4Zm.256 7a4.474 4.474 0 0 1-.229-1.004H3c.001-.246.154-.986.832-1.664C4.484 10.68 5.711 10 8 10c.26 0 .507.009.74.025.226-.341.496-.65.804-.918C9.077 9.038 8.564 9 8 9c-5 0-6 3-6 4s1 1 1 1h5.256Zm3.63-4.54c.18-.613 1.048-.613 1.229 0l.043.148a.64.64 0 0 0 .921.382l.136-.074c.561-.306 1.175.308.87.869l-.075.136a.64.64 0 0 0 .382.92l.149.045c.612.18.612 1.048 0 1.229l-.15.043a.64.64 0 0 0-.38.921l.074.136c.305.561-.309 1.175-.87.87l-.136-.075a.64.64 0 0 0-.92.382l-.045.149c-.18.612-1.048.612-1.229 0l-.043-.15a.64.64 0 0 0-.921-.38l-.136.074c-.561.305-1.175-.309-.87-.87l.075-.136a.64.64 0 0 0-.382-.92l-.148-.045c-.613-.18-.613-1.048 0-1.229l.148-.043a.64.64 0 0 0 .382-.921l-.074-.136c-.306-.561.308-1.175.869-.87l.136.075a.64.64 0 0 0 .92-.382l.045-.148ZM14 12.5a1.5 1.5 0 1 0-3 0 1.5 1.5 0 0 0 3 0Z"/>
                                            </svg> 
                                        </button>
                                    </form>
                                </td>
                                <td>
                                    <form action="EliminarCliente" method="post" onsubmit="return confirmar()">
                                        <input type="hidden" name="id" value="${c.id}">
                                        <button type="submit" class="btn btn-danger">Eliminar 
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-person-x" viewBox="0 0 16 16">
                                            <path d="M11 5a3 3 0 1 1-6 0 3 3 0 0 1 6 0ZM8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4Zm.256 7a4.474 4.474 0 0 1-.229-1.004H3c.001-.246.154-.986.832-1.664C4.484 10.68 5.711 10 8 10c.26 0 .507.009.74.025.226-.341.496-.65.804-.918C9.077 9.038 8.564 9 8 9c-5 0-6 3-6 4s1 1 1 1h5.256Z"/>
                                            <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7Zm-.646-4.854.646.647.646-.647a.5.5 0 0 1 .708.708l-.647.646.647.646a.5.5 0 0 1-.708.708l-.646-.647-.646.647a.5.5 0 0 1-.708-.708l.647-.646-.647-.646a.5.5 0 0 1 .708-.708Z"/>
                                            </svg>
                                        </button>
                                    </form>
                                </td>   
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <c:if test="${not empty error}">
                <div class="alert alert-danger">
                    ${error}
                </div>
            </c:if>
            <br>
            <a href="CrearCliente" class="btn btn-primary">Crear Nuevo Cliente 
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-person-plus" viewBox="0 0 16 16">
                <path d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H1s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C9.516 10.68 8.289 10 6 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
                <path fill-rule="evenodd" d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"/>
                </svg>
            </a>
            <br>
            <a href="administrar.jsp" class="btn btn-secondary mt-2">Volver 
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-grid-fill" viewBox="0 0 16 16">
                <path d="M1 2.5A1.5 1.5 0 0 1 2.5 1h3A1.5 1.5 0 0 1 7 2.5v3A1.5 1.5 0 0 1 5.5 7h-3A1.5 1.5 0 0 1 1 5.5v-3zm8 0A1.5 1.5 0 0 1 10.5 1h3A1.5 1.5 0 0 1 15 2.5v3A1.5 1.5 0 0 1 13.5 7h-3A1.5 1.5 0 0 1 9 5.5v-3zm-8 8A1.5 1.5 0 0 1 2.5 9h3A1.5 1.5 0 0 1 7 10.5v3A1.5 1.5 0 0 1 5.5 15h-3A1.5 1.5 0 0 1 1 13.5v-3zm8 0A1.5 1.5 0 0 1 10.5 9h3a1.5 1.5 0 0 1 1.5 1.5v3a1.5 1.5 0 0 1-1.5 1.5h-3A1.5 1.5 0 0 1 9 13.5v-3z"/>
                </svg>
            </a>
        </div>                                    
        <script>

        </script>
        <script>
            $(document).ready(function () {
                $('#clientesTable').DataTable();
            });

            function confirmar() {
                return confirm('¿Estás seguro de que quieres eliminar este cliente?');
            }
        </script>
        <script src="../assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/pikaday.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/6.4.8/swiper-bundle.min.js"></script>
        <script src="../assets/js/Simple-Slider.js"></script>
        <script src="../assets/js/theme.js"></script>
    </body>
</html>
