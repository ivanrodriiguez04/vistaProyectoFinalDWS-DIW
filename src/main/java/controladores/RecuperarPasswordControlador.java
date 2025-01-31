package controladores;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.RecuperarPasswordServicio;

@WebServlet("/recuperar")
public class RecuperarPasswordControlador extends HttpServlet {

    private RecuperarPasswordServicio recuperarServicio;

    @Override
    public void init() throws ServletException {
        this.recuperarServicio = new RecuperarPasswordServicio();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("emailUsuario");

        if (email == null || email.isEmpty()) {
            request.setAttribute("errorMessage", "El correo es obligatorio.");
            request.getRequestDispatcher("recuperarPassword.jsp").forward(request, response);
            return;
        }

        String mensaje = recuperarServicio.enviarSolicitudRecuperacion(email);

        if (mensaje.equals("Correo enviado correctamente. Revisa tu bandeja.")) {
            request.setAttribute("successMessage", mensaje);
        } else {
            request.setAttribute("errorMessage", mensaje);
        }

        // Redirige de nuevo a recuperar.jsp mostrando el mensaje correspondiente
        request.getRequestDispatcher("recuperarPassword.jsp").forward(request, response);
    }
}
