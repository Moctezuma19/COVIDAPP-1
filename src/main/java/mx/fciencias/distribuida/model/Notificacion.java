package mx.fciencias.distribuida.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "Notificacion")
public class Notificacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;
	
	@ManyToOne(targetEntity = Usuario.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
	private Usuario id_usuario;
	
	@ManyToOne(targetEntity = Usuario.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "id_usuario_c", referencedColumnName = "id", nullable = false)
	private Usuario id_usuario_c;
	
	@Column(name = "leido",nullable = false)
	boolean leido;
	
	@Column(name = "tipo",nullable = false)
	boolean tipo;
	
	@Column(name = "tiempo_creado", nullable = false)
	private long tiempo_creado;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Usuario id_usuario) {
		this.id_usuario = id_usuario;
	}

	public boolean isLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}

	public boolean isTipo() {
		return tipo;
	}

	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}

	public long getTiempo_creado() {
		return tiempo_creado;
	}

	public void setTiempo_creado(long tiempo_creado) {
		this.tiempo_creado = tiempo_creado;
	}

	public Usuario getId_usuario_c() {
		return id_usuario_c;
	}

	public void setId_usuario_c(Usuario id_usuario_c) {
		this.id_usuario_c = id_usuario_c;
	}
	
	
	
}
