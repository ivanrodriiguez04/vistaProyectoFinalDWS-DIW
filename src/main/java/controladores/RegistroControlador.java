package controladores;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import dtos.RegistroDto;
import servicios.RegistroServicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/registro")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) // Tamaño máximo de archivo: 5 MB
public class RegistroControlador extends HttpServlet {

    private RegistroServicio registroServicio;

    @Override
    public void init() throws ServletException {
        this.registroServicio = new RegistroServicio();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (request.getContentType() == null || !request.getContentType().toLowerCase().startsWith("multipart/")) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El contenido de la solicitud no es multipart/form-data.");
                return;
            }

            String nombre = request.getParameter("nombreCompletoUsuario");
            String telefono = request.getParameter("telefonoUsuario");
            String correo = request.getParameter("emailUsuario");
            String password = request.getParameter("passwordUsuario");
            String dni = request.getParameter("dniUsuario");
            byte[] fotoDniFrontal = obtenerBytesDeArchivo(request.getPart("fotoDniFrontalUsuario"));
            byte[] fotoDniTrasero = obtenerBytesDeArchivo(request.getPart("fotoDniTraseroUsuario"));
            byte[] fotoUsuario = obtenerBytesDeArchivo(request.getPart("fotoUsuario"));

            if (fotoDniFrontal == null || fotoDniTrasero == null || fotoUsuario == null) {
                request.setAttribute("errorMessage", "Debes cargar todas las fotos requeridas.");
                request.getRequestDispatcher("registro.jsp").forward(request, response);
                return;
            }

            RegistroDto registroDto = new RegistroDto();
            registroDto.setNombreCompletoUsuario(nombre);
            registroDto.setTelefonoUsuario(telefono);
            registroDto.setEmailUsuario(correo);
            registroDto.setPasswordUsuario(password);
            registroDto.setDniUsuario(dni);
            registroDto.setFotoDniFrontalUsuario(fotoDniFrontal);
            registroDto.setFotoDniTraseroUsuario(fotoDniTrasero);
            registroDto.setFotoUsuario(fotoUsuario);

            boolean registroExitoso = registroServicio.registrarUsuario(registroDto);

            if (registroExitoso) {
                response.sendRedirect("inicioSesion.jsp");
            } else {
                request.setAttribute("errorMessage", "El correo ya está registrado.");
                request.getRequestDispatcher("registro.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error interno del servidor.");
        }
    }

    private byte[] obtenerBytesDeArchivo(Part archivo) throws IOException {
        if (archivo != null && archivo.getSize() > 0) {
            try (InputStream inputStream = archivo.getInputStream();
                 ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                return outputStream.toByteArray();
            }
        }
        return null;
    }
}
