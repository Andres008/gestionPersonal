package ec.mil.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the aut_perfiles database table.
 * 
 */
@Entity
@Table(name="aut_perfiles")
@NamedQuery(name="AutPerfile.findAll", query="SELECT a FROM AutPerfile a")
public class AutPerfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="AUT_PERFILES_ID_GENERATOR", sequenceName="SEQ_AUT_PERFILES")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUT_PERFILES_ID_GENERATOR")
	private long id;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_final")
	private Date fechaFinal;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicial")
	private Date fechaInicial;

	private String nombre;

	private String url;

	//bi-directional many-to-one association to AutMenu
	@ManyToOne
	@JoinColumn(name="id_menu")
	private AutMenu autMenu;

	public AutPerfile() {
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public AutMenu getAutMenu() {
		return this.autMenu;
	}

	public void setAutMenu(AutMenu autMenu) {
		this.autMenu = autMenu;
	}

}