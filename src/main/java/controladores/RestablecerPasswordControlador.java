package controladores;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.RestablecerPasswordServicio;

@WebServlet("/restablecer")
public class RestablecerPasswordControlador extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private RestablecerPasswordServicio restablecerServicio = new RestablecerPasswordServicio();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Obtener el token desde la URL
        String token = request.getParameter("token");

        // Si el token es inválido, redirigir a una página de error o mostrar mensaje
        if (token == null || token.isEmpty()) {
            request.setAttribute("errorMessage", "El enlace de recuperación es inválido o ha expirado.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        // Si el token es válido, cargar la vista con el token
        request.setAttribute("token", token);
        request.getRequestDispatcher("restablecerPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Obtener datos del formulario
        String token = request.getParameter("token");
        String nuevaPassword = request.getParameter("passwordUsuario");
        String confirmPassword = request.getParameter("confirmPasswordUsuario");

        // Validaciones
        if (token == null || token.isEmpty()) {
            request.setAttribute("errorMessage", "El enlace de recuperación es inválido.");
            request.getRequestDispatcher("restablecerPassword.jsp").forward(request, response);
            return;
        }

        if (nuevaPassword == null || confirmPassword == null || nuevaPassword.isEmpty() || confirmPassword.isEmpty()) {
            request.setAttribute("errorMessage", "Los campos de contraseña no pueden estar vacíos.");
            request.getRequestDispatcher("restablecerPassword.jsp").forward(request, response);
            return;
        }

        if (!nuevaPassword.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Las contraseñas no coinciden.");
            request.getRequestDispatcher("restablecerPassword.jsp").forward(request, response);
            return;
        }

        // Llamar al servicio para cambiar la contraseña en la API
        boolean cambioExitoso = restablecerServicio.cambiarPassword(token, nuevaPassword);

        if (cambioExitoso) {
            response.sendRedirect("inicioSesion.jsp"); // Redirigir al login si la recuperación fue exitosa
        } else {
            request.setAttribute("errorMessage", "Error al cambiar la contraseña. Inténtelo de nuevo.");
            request.getRequestDispatcher("restablecerPassword.jsp").forward(request, response);
        }
    }
}
