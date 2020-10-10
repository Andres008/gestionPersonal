package ec.mil.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ges_regiones database table.
 * 
 */
@Entity
@Table(name="ges_regiones")
@NamedQuery(name="GesRegione.findAll", query="SELECT g FROM GesRegione g")
public class GesRegione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GES_REGIONES_ID_GENERATOR", sequenceName="SEQ_GES_REGIONES")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GES_REGIONES_ID_GENERATOR")
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

	//bi-directional many-to-one association to GesDependencia
	@OneToMany(mappedBy="gesRegione")
	private List<GesDependencia> gesDependencias;

	public GesRegione() {
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

	public List<GesDependencia> getGesDependencias() {
		return this.gesDependencias;
	}

	public void setGesDependencias(List<GesDependencia> gesDependencias) {
		this.gesDependencias = gesDependencias;
	}

	public GesDependencia addGesDependencia(GesDependencia gesDependencia) {
		getGesDependencias().add(gesDependencia);
		gesDependencia.setGesRegione(this);

		return gesDependencia;
	}

	public GesDependencia removeGesDependencia(GesDependencia gesDependencia) {
		getGesDependencias().remove(gesDependencia);
		gesDependencia.setGesRegione(null);

		return gesDependencia;
	}

}