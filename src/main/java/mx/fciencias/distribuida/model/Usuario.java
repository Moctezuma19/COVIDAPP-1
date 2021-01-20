package mx.fciencias.distribuida.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
	
	@Column(name = "pct", nullable = true)
	float pct;
	
	@Column(name = "pcg", nullable = true)
	float pcg;
	
	@Column(name = "contagiado",nullable = false)
	boolean contagiado;
	
	@OneToMany(mappedBy = "id_usuario", targetEntity = Contacto.class)
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<Contacto> contactos = new ArrayList<>();
	
	@OneToOne(mappedBy = "id_usuario_c", targetEntity = Contacto.class)
	@LazyCollection(LazyCollectionOption.TRUE)
	private Contacto contacto = null;
	
	@OneToMany(mappedBy = "id_usuario", targetEntity = Notificacion.class)
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<Notificacion> notificaciones = new ArrayList<>();
	
	@OneToMany(mappedBy = "id_usuario_c", targetEntity = Notificacion.class)
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<Notificacion> notificados = new ArrayList<>();

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	public List<Contacto> getContactos() {
		return contactos;
	}

	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}

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

	public List<Notificacion> getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(List<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}

	public List<Notificacion> getNotificados() {
		return notificados;
	}

	public void setNotificados(List<Notificacion> notificados) {
		this.notificados = notificados;
	}

	public boolean isContagiado() {
		return contagiado;
	}

	public void setContagiado(boolean contagiado) {
		this.contagiado = contagiado;
	}
	
	
}
