package ec.mil.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the aut_usuario database table.
 * 
 */
@Entity
@Table(name="aut_usuario")
@NamedQuery(name="AutUsuario.findAll", query="SELECT a FROM AutUsuario a")
public class AutUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cedula;

	private String clave;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="primer_inicio")
	private String primerInicio;

	//bi-directional many-to-one association to AutRole
	@ManyToOne
	@JoinColumn(name="id_rol")
	private AutRole autRole;

	//bi-directional one-to-one association to GesPersona
	@OneToOne
	@JoinColumn(name="cedula")
	private GesPersona gesPersona;

	public AutUsuario() {
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getPrimerInicio() {
		return this.primerInicio;
	}

	public void setPrimerInicio(String primerInicio) {
		this.primerInicio = primerInicio;
	}

	public AutRole getAutRole() {
		return this.autRole;
	}

	public void setAutRole(AutRole autRole) {
		this.autRole = autRole;
	}

	public GesPersona getGesPersona() {
		return this.gesPersona;
	}

	public void setGesPersona(GesPersona gesPersona) {
		this.gesPersona = gesPersona;
	}

}