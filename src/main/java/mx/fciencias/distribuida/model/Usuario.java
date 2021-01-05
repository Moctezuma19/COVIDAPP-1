package mx.fciencias.distribuida.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;
	@Column(name = "password", nullable = true, length = 60)
	String password;
	
	@Column(name = "nombre", nullable = false, length = 250)
	String nombre;

	@Column(name = "apellido_paterno", nullable = false, length = 250)
	String apellido_paterno;

	@Column(name = "apellido_materno", nullable = false, length = 250)
	String apellido_materno;
	
	@Column(name = "correo", nullable = false, length = 250)
	String correo;
	
	@Column(name = "num_telefonico", nullable = false, length = 10)
	String num_telefonico;
	
	@Column(name = "edad", nullable = false)
	int edad;
	
	@Column(name = "sexo", nullable = false, length = 1)
	String sexo;
	
	@Column(name = "cp", nullable = false, length = 5)
	String cp;
	
	@Column(name = "pct", nullable = false)
	float pct;
	
	@Column(name = "pcg", nullable = false)
	float pcg;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido_paterno() {
		return apellido_paterno;
	}

	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}

	public String getApellido_materno() {
		return apellido_materno;
	}

	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNum_telefonico() {
		return num_telefonico;
	}

	public void setNum_telefonico(String num_telefonico) {
		this.num_telefonico = num_telefonico;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public float getPct() {
		return pct;
	}

	public void setPct(float pct) {
		this.pct = pct;
	}

	public float getPcg() {
		return pcg;
	}

	public void setPcg(float pcg) {
		this.pcg = pcg;
	}
	
	
}
