package dtos;

public class RegistroDto {
	//Atributos
	private long idUsuario;
	private String nombreCompletoUsuario;
	private String telefonoUsuario;
	private String rolUsuario;
	private String emailUsuario;
	private String passwordUsuario;
	private String dniUsuario;
	private byte[] fotoDniFrontalUsuario;
	private byte[] fotoDniTraseroUsuario;
	private byte[] fotoUsuario;
	
	//Getters & Setters
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombreCompletoUsuario() {
		return nombreCompletoUsuario;
	}
	public void setNombreCompletoUsuario(String nombreCompletoUsuario) {
		this.nombreCompletoUsuario = nombreCompletoUsuario;
	}
	public String getTelefonoUsuario() {
		return telefonoUsuario;
	}
	public void setTelefonoUsuario(String telefonoUsuario) {
		this.telefonoUsuario = telefonoUsuario;
	}
	public String getRolUsuario() {
		return rolUsuario;
	}
	public void setRolUsuario(String rolUsuario) {
		this.rolUsuario = rolUsuario;
	}
	public String getEmailUsuario() {
		return emailUsuario;
	}
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	public String getPasswordUsuario() {
		return passwordUsuario;
	}
	public void setPasswordUsuario(String passwordUsuario) {
		this.passwordUsuario = passwordUsuario;
	}
	public String getDniUsuario() {
		return dniUsuario;
	}
	public void setDniUsuario(String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}
	public byte[] getFotoDniFrontalUsuario() {
		return fotoDniFrontalUsuario;
	}
	public void setFotoDniFrontalUsuario(byte[] fotoDniFrontalUsuario) {
		this.fotoDniFrontalUsuario = fotoDniFrontalUsuario;
	}
	public byte[] getFotoDniTraseroUsuario() {
		return fotoDniTraseroUsuario;
	}
	public void setFotoDniTraseroUsuario(byte[] fotoDniTraseroUsuario) {
		this.fotoDniTraseroUsuario = fotoDniTraseroUsuario;
	}
	public byte[] getFotoUsuario() {
		return fotoUsuario;
	}
	public void setFotoUsuario(byte[] fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}
	
}
