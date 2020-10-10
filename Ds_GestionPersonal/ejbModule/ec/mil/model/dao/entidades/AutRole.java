package ec.mil.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the aut_roles database table.
 * 
 */
@Entity
@Table(name="aut_roles")
@NamedQuery(name="AutRole.findAll", query="SELECT a FROM AutRole a")
public class AutRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="AUT_ROLES_ID_GENERATOR", sequenceName="SEQ_AUT_ROLES")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUT_ROLES_ID_GENERATOR")
	private long id;

	private String descripcion;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_final")
	private Date fechaFinal;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicial")
	private Date fechaInicial;

	private String nombre;

	//bi-directional many-to-one association to AutUsuario
	@OneToMany(mappedBy="autRole")
	private List<AutUsuario> autUsuarios;

	//bi-directional many-to-one association to AutRolMenu
	@OneToMany(mappedBy="autRole")
	private List<AutRolMenu> autRolMenus;

	public AutRole() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaFinal() {
		return this.fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Date getFechaInicial() {
		return this.fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<AutUsuario> getAutUsuarios() {
		return this.autUsuarios;
	}

	public void setAutUsuarios(List<AutUsuario> autUsuarios) {
		this.autUsuarios = autUsuarios;
	}

	public AutUsuario addAutUsuario(AutUsuario autUsuario) {
		getAutUsuarios().add(autUsuario);
		autUsuario.setAutRole(this);

		return autUsuario;
	}

	public AutUsuario removeAutUsuario(AutUsuario autUsuario) {
		getAutUsuarios().remove(autUsuario);
		autUsuario.setAutRole(null);

		return autUsuario;
	}

	public List<AutRolMenu> getAutRolMenus() {
		return this.autRolMenus;
	}

	public void setAutRolMenus(List<AutRolMenu> autRolMenus) {
		this.autRolMenus = autRolMenus;
	}

	public AutRolMenu addAutRolMenus(AutRolMenu autRolMenus) {
		getAutRolMenus().add(autRolMenus);
		autRolMenus.setAutRole(this);

		return autRolMenus;
	}

	public AutRolMenu removeAutRolMenus(AutRolMenu autRolMenus) {
		getAutRolMenus().remove(autRolMenus);
		autRolMenus.setAutRole(null);

		return autRolMenus;
	}

}