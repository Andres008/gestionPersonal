package ec.mil.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ges_dependencias database table.
 * 
 */
@Entity
@Table(name="ges_dependencias")
@NamedQuery(name="GesDependencia.findAll", query="SELECT g FROM GesDependencia g")
public class GesDependencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GES_DEPENDENCIAS_ID_GENERATOR", sequenceName="SEQ_GES_DEPENDENCIAS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GES_DEPENDENCIAS_ID_GENERATOR")
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

	//bi-directional many-to-one association to GesDependenciaPersona
	@OneToMany(mappedBy="gesDependencia")
	private List<GesDependenciaPersona> gesDependenciaPersonas;

	//bi-directional many-to-one association to GesRegione
	@ManyToOne
	@JoinColumn(name="id_region")
	private GesRegione gesRegione;

	//bi-directional many-to-one association to GesReparto
	@ManyToOne
	@JoinColumn(name="id_reparto")
	private GesReparto gesReparto;

	public GesDependencia() {
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

	public List<GesDependenciaPersona> getGesDependenciaPersonas() {
		return this.gesDependenciaPersonas;
	}

	public void setGesDependenciaPersonas(List<GesDependenciaPersona> gesDependenciaPersonas) {
		this.gesDependenciaPersonas = gesDependenciaPersonas;
	}

	public GesDependenciaPersona addGesDependenciaPersona(GesDependenciaPersona gesDependenciaPersona) {
		getGesDependenciaPersonas().add(gesDependenciaPersona);
		gesDependenciaPersona.setGesDependencia(this);

		return gesDependenciaPersona;
	}

	public GesDependenciaPersona removeGesDependenciaPersona(GesDependenciaPersona gesDependenciaPersona) {
		getGesDependenciaPersonas().remove(gesDependenciaPersona);
		gesDependenciaPersona.setGesDependencia(null);

		return gesDependenciaPersona;
	}

	public GesRegione getGesRegione() {
		return this.gesRegione;
	}

	public void setGesRegione(GesRegione gesRegione) {
		this.gesRegione = gesRegione;
	}

	public GesReparto getGesReparto() {
		return this.gesReparto;
	}

	public void setGesReparto(GesReparto gesReparto) {
		this.gesReparto = gesReparto;
	}

}