<%-- 
    Document   : valorar
    Created on : 6 jun 2023, 17:17:02
    Author     : alanr
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Valorar</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            .rating input {
                display: none;
            }

            .rating label {
                font-size: 48px;
                color: #ddd;
                transition: color 0.3s;
                cursor: pointer;
            }

            .rating label:before {
                content: "\f005";
                font-family: "Font Awesome 5 Free";
                font-weight: 900;
            }

            .rating input:checked ~ label,
            .rating input:checked ~ label ~ label {
                color: #ddd;
            }

        </style>
    </head>
    <body>
        <div class="container mt-5">
            <h1>Valorar</h1>
            <%-- Mostrar mensaje de error si existe --%>
            <c:if test="${not empty error}">
                <p class="error">${error}</p>
            </c:if>

            <form action="Valorar" method="post">
                <div class="rating">
                    <input type="radio" id="star1" name="rating" value="1" data-index="0" />
                    <label for="star1" class="text-warning"></label>
                    <input type="radio" id="star2" name="rating" value="2" data-index="1" />
                    <label for="star2" class="text-warning"></label>
                    <input type="radio" id="star3" name="rating" value="3" data-index="2" />
                    <label for="star3" class="text-warning"></label>
                    <input type="radio" id="star4" name="rating" value="4" data-index="3" />
                    <label for="star4" class="text-warning"></label>
                    <input type="radio" id="star5" name="rating" value="5" data-index="4" />
                    <label for="star5" class="text-warning"></label>
                </div>
                <textarea name="comentario" placeholder="Comentario" class="form-control"></textarea>
                <br>
                <button type="submit" class="btn btn-primary">Enviar</button>
            </form>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script>
            $(document).ready(function () {
                $(".rating input").click(function () {
                    var selectedStar = $(this).data("index");
                    var stars = $(this).parent().children("label");

                    stars.each(function (index) {
                        if (index <= selectedStar) {
                            $(this).addClass("text-warning");
                        } else {
                            $(this).removeClass("text-warning");
                        }
                    });
                });

                $(".rating input").hover(function () {
                    var hoveredStar = $(this).data("index");
                    var stars = $(this).parent().children("label");

                    stars.each(function (index) {
                        if (index <= hoveredStar) {
                            $(this).addClass("text-warning");
                        } else {
                            $(this).removeClass("text-warning");
                        }
                    });
                }, function () {
                    var stars = $(this).parent().children("label");
                    stars.removeClass("text-warning");
                });
            });

        </script>
    </body>
</html>



