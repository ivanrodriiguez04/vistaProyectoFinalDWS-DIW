package servicios;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import dtos.RegistroDto;

public class RegistroServicio {
	public boolean registrarUsuario(RegistroDto registroDto) {
        boolean registroExitoso = false;

        try {
            // Configurar la URL de la API para el registro
            URL url = new URL("http://localhost:8081/api/registro/usuario");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setDoOutput(true);

            // Convertir el DTO de usuario a JSON
            ObjectMapper mapper = new ObjectMapper();
            String jsonInput = mapper.writeValueAsString(registroDto);

            // Enviar la solicitud al servidor
            try (OutputStream os = conexion.getOutputStream()) {
                os.write(jsonInput.getBytes());
                os.flush();
            }

            // Obtener el código de respuesta
            int responseCode = conexion.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_CREATED) { // 201 Created
                // Si el usuario se creó correctamente
                registroExitoso = true;
            } else if (responseCode == HttpURLConnection.HTTP_CONFLICT) { // 409 Conflict
                // Si hubo un conflicto, como correo duplicado
                System.out.println("Error: El correo ya está registrado.");
            } else {
                System.out.println("Error: Código de respuesta no esperado. Código: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        return registroExitoso;
    }
}
