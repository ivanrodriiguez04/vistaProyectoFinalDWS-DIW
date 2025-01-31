package controladores;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import servicios.LoginServicio;

@WebServlet("/login")
public class LoginControlador extends HttpServlet {

    private LoginServicio servicio;

    @Override
    public void init() throws ServletException {
        // Inicializa el servicio de login
        this.servicio = new LoginServicio();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recoger los parámetros del formulario
        String correo = request.getParameter("emailUsuario");
        String password = request.getParameter("passwordUsuario");

        // Imprimir los valores para depuración
        System.out.println("Correo recibido: " + correo);
        System.out.println("Contraseña recibida: " + password);

        // Llamar al servicio para verificar el usuario
        boolean isValidUser = servicio.verificarUsuario(correo, password);

        if (isValidUser) {
            // Obtener el rol del usuario
            String rol = servicio.getRol();
            System.out.println("Rol del usuario: " + rol);

            // Crear o recuperar la sesión
            HttpSession session = request.getSession();
            session.setAttribute("emailUsuario", correo);
            session.setAttribute("rolUsuario", rol);
            
            // Configurar el tiempo de inactividad máximo de la sesión
            session.setMaxInactiveInterval(-1); // Hasta que se cierre el navegador

            // Verificar si la sesión está activa y mostrar por consola
            if (session != null) {
                System.out.println("Sesión activa para el usuario: " + correo);
            } else {
                System.out.println("No se pudo crear la sesión.");
            }

            // Redirigir según el rol del usuario
            if ("admin".equals(rol)) {
                // Redirigir al panel de administración
                response.sendRedirect("administrador.jsp");
            } else if ("usuario".equals(rol)) {
                // Redirigir al panel de usuario
                response.sendRedirect("index.jsp");
            } else {
                // Rol desconocido
                request.setAttribute("errorMessage", "Rol desconocido.");
                request.getRequestDispatcher("inicioSesion.jsp").forward(request, response);
            }
        } else {
            // Si las credenciales son incorrectas
            System.out.println("Usuario no válido. Credenciales incorrectas.");
            request.setAttribute("errorMessage", "Email o contraseña incorrectos.");
            request.getRequestDispatcher("inicioSesion.jsp").forward(request, response);
        }
    }
}