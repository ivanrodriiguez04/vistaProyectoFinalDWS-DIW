package servicios;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.springframework.stereotype.Service;

@Service
public class RestablecerPasswordServicio {

	public boolean cambiarPassword(String token, String nuevaPassword) {
	    try {
	        // Verificar si los datos son nulos antes de enviarlos
	        if (token == null || token.isEmpty()) {
	            System.err.println("ERROR: El token es nulo o vacío.");
	            return false;
	        }
	        if (nuevaPassword == null || nuevaPassword.isEmpty()) {
	            System.err.println("ERROR: La nueva contraseña es nula o vacía.");
	            return false;
	        }

	        // JSON correctamente formateado
	        String jsonInputString = "{"
	            + "\"token\": \"" + token + "\", "
	            + "\"passwordUsuario\": \"" + nuevaPassword + "\""
	            + "}";

	        System.out.println("Enviando solicitud a la API con JSON: " + jsonInputString);

	        // Configurar la conexión a la API
	        URL url = new URL("http://localhost:8081/api/password/restablecer");
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setDoOutput(true);

	        // Enviar datos al backend
	        try (OutputStream os = conn.getOutputStream()) {
	            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
	            os.write(input, 0, input.length);
	        }

	        // Leer respuesta del backend
	        int responseCode = conn.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_OK) {
	            return true;
	        } else {
	            // Leer el mensaje de error del backend
	            Scanner scanner = new Scanner(conn.getErrorStream(), StandardCharsets.UTF_8);
	            String responseBody = scanner.useDelimiter("\\A").next();
	            scanner.close();

	            System.err.println("Error en la API: " + responseBody);
	            return false;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}
