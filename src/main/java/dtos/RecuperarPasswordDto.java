package dtos;

public class RecuperarPasswordDto {
	//Atributos
	private String emailUsuario;
	//Constructores
		public RecuperarPasswordDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecuperarPasswordDto(String emailUsuario) {
		super();
		this.emailUsuario = emailUsuario;
	}
	//Getters & Setters
	public String getEmailUsuario() {
		return emailUsuario;
	}
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	
}
