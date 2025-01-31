package servicios;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import dtos.RecuperarPasswordDto;

public class RecuperarPasswordServicio {
	
	public String enviarSolicitudRecuperacion(String email) {
	    try {
	        URL url = new URL("http://localhost:8081/api/password/recuperar");
	        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
	        conexion.setRequestMethod("POST");
	        conexion.setRequestProperty("Content-Type", "application/json");
	        conexion.setDoOutput(true);

	        ObjectMapper mapper = new ObjectMapper();
	        String jsonInput = mapper.writeValueAsString(new RecuperarPasswordDto(email));

	        try (OutputStream os = conexion.getOutputStream()) {
	            os.write(jsonInput.getBytes());
	            os.flush();
	        }

	        int responseCode = conexion.getResponseCode();

	        if (responseCode == HttpURLConnection.HTTP_OK) {
	            return "Correo enviado correctamente. Revisa tu bandeja.";
	        } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
	            return "El correo no está registrado.";
	        } else {
	            return "Error al enviar la solicitud.";
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Error en el servidor.";
	    }
	}

}
