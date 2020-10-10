package ec.mil.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the aut_menu database table.
 * 
 */
@Entity
@Table(name="aut_menu")
@NamedQuery(name="AutMenu.findAll", query="SELECT a FROM AutMenu a")
public class AutMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="AUT_MENU_ID_GENERATOR", sequenceName="SEQ_AUT_MENU")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUT_MENU_ID_GENERATOR")
	private long id;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_final")
	private Date fechaFinal;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicial")
	private Date fechaInicial;

	private String nombre;

	private String observacion;

	private BigDecimal orden;

	//bi-directional many-to-one association to AutPerfile
	@OneToMany(mappedBy="autMenu")
	private List<AutPerfile> autPerfiles;

	//bi-directional many-to-one association to AutRolMenu
	@OneToMany(mappedBy="autMenu")
	private List<AutRolMenu> autRolMenus;

	public AutMenu() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public BigDecimal getOrden() {
		return this.orden;
	}

	public void setOrden(BigDecimal orden) {
		this.orden = orden;
	}

	public List<AutPerfile> getAutPerfiles() {
		return this.autPerfiles;
	}

	public void setAutPerfiles(List<AutPerfile> autPerfiles) {
		this.autPerfiles = autPerfiles;
	}

	public AutPerfile addAutPerfile(AutPerfile autPerfile) {
		getAutPerfiles().add(autPerfile);
		autPerfile.setAutMenu(this);

		return autPerfile;
	}

	public AutPerfile removeAutPerfile(AutPerfile autPerfile) {
		getAutPerfiles().remove(autPerfile);
		autPerfile.setAutMenu(null);

		return autPerfile;
	}

	public List<AutRolMenu> getAutRolMenus() {
		return this.autRolMenus;
	}

	public void setAutRolMenus(List<AutRolMenu> autRolMenus) {
		this.autRolMenus = autRolMenus;
	}

	public AutRolMenu addAutRolMenus(AutRolMenu autRolMenus) {
		getAutRolMenus().add(autRolMenus);
		autRolMenus.setAutMenu(this);

		return autRolMenus;
	}

	public AutRolMenu removeAutRolMenus(AutRolMenu autRolMenus) {
		getAutRolMenus().remove(autRolMenus);
		autRolMenus.setAutMenu(null);

		return autRolMenus;
	}

}