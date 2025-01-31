<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de Sesion</title>
    <link rel="icon" type="image/png" href="imagenes/favicon.png">
    <link rel="stylesheet" href="css/recuperar.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <nav>
        <div class="container-fluid">
            <div class="nav-left">
                <a href="index.jsp" class="logo">InnovaBank</a>
            </div>
            <div class="nav-right">
                <a href="registro.jsp" class="hazteCliente">Hazte cliente</a>
                <a href="inicioSesion.jsp" class="login-cuentas">Login</a>
                <a href="cuentas.jsp" class="login-cuentas">Cuentas</a>
            </div>
        </div>
    </nav>

    <div class="container Recuperar" style="max-width: 600px;">
        <h3 class="text-center mb-4"><b>Recuperar contraseña:</b></h3>

        <!-- Formulario de inicio de sesion -->
        <form id="recuperar" action="recuperar" method="post">
            <!-- Nombre de club o correo -->
            <div class="mb-3">
                <label>Email:</label>
                <input type="text" id="emailUsuario" name="emailUsuario" class="form-control" placeholder="Introduzca su correo" required>
            </div>
            <!-- Boton de iniciar sesion -->
            <button type="submit" class="btn btn-dark w-100">Enviar correo</button>
        </form>

        <!-- Contenedor para mostrar mensajes de resultado -->
<!-- Contenedor para mostrar mensajes de resultado -->
        <div id="result" class="text-center mt-3">
            <% if (request.getAttribute("successMessage") != null) { %>
                <div class="alert alert-success"><%= request.getAttribute("successMessage") %></div>
            <% } %>
            <% if (request.getAttribute("errorMessage") != null) { %>
                <div class="alert alert-danger"><%= request.getAttribute("errorMessage") %></div>
            <% } %>
        </div>    
     </div>
</body>
</html>