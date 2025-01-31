<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Restablecer Contraseña</title>
    <link rel="stylesheet" href="css/recuperar.css">    

    <script>
        function validarFormulario(event) {
            var password = document.getElementById("passwordUsuario").value;
            var confirmPassword = document.getElementById("confirmPasswordUsuario").value;
            var mensajeError = document.getElementById("errorMensaje");

            if (password !== confirmPassword) {
                mensajeError.innerHTML = "Las contraseñas no coinciden.";
                mensajeError.style.display = "block";
                event.preventDefault();
            }
        }
    </script>
</head>
<body>

<%
    // Obtener el token de la URL
    String token = request.getParameter("token");

    if (token == null || token.isEmpty()) {
        response.sendRedirect("inicioSesion.jsp"); // Redirección segura en lugar de return
%>
    <div class="container text-center mt-5">
        <div class="alert alert-danger">Error: El enlace de recuperación es inválido o ha expirado.</div>
        <a href="inicioSesion.jsp" class="btn btn-dark">Volver al inicio</a>
    </div>
<%
    } else {
%>

<div class="container Restablecer">
    <h3 class="text-center mb-4"><b>Restablecer contraseña:</b></h3>

    <<form id="restablecer" action="restablecer" method="post">
    <input type="hidden" name="token" value="<%= request.getParameter("token") %>">

    <div class="mb-3">
        <label>Nueva contraseña:</label>
        <input type="password" id="passwordUsuario" name="passwordUsuario" class="form-control" required>
    </div>

    <div class="mb-3">
        <label>Repita la contraseña:</label>
        <input type="password" id="confirmPasswordUsuario" name="confirmPasswordUsuario" class="form-control" required>
    </div>

    <button type="submit" class="btn btn-dark w-100">Cambiar contraseña</button>
</form>


    <div id="result" class="text-center mt-3">
        <% if (request.getAttribute("successMessage") != null) { %>
            <div class="alert alert-success"><%= request.getAttribute("successMessage") %></div>
        <% } %>
        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="alert alert-danger"><%= request.getAttribute("errorMessage") %></div>
        <% } %>
    </div>    
</div>

<%
    }
%>

</body>
</html>
