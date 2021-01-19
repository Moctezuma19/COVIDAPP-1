package mx.fciencias.distribuida.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import mx.fciencias.distribuida.model.Usuario;

@Entity
@Table(name = "Contacto")
public class Contacto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;
	
	@Column(name = "nombre", nullable = true, length = 250)
	String nombre;
	
	@Column(name = "edad", nullable = true)
	int edad;
	
	@Column(name = "sexo", nullable = true, length = 1)
	String sexo;

	@Column(name = "pct", nullable = true)
	float pct;
	
	@Column(name = "pcg", nullable = true)
	float pcg;
	
	@ManyToOne(targetEntity = Usuario.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
	private Usuario id_usuario;
	
	@OneToOne(targetEntity = Usuario.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "id_usuario_c", referencedColumnName = "id", nullable = true)
	private Usuario id_usuario_c;

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
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

	public Usuario getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Usuario id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Usuario getId_usuario_c() {
		return id_usuario_c;
	}

	public void setId_usuario_c(Usuario id_usuario_c) {
		this.id_usuario_c = id_usuario_c;
	}

	
	
}
