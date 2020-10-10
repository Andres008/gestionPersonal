package ec.mil.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ges_estimulo database table.
 * 
 */
@Entity
@Table(name="ges_estimulo")
@NamedQuery(name="GesEstimulo.findAll", query="SELECT g FROM GesEstimulo g")
public class GesEstimulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GES_ESTIMULO_ID_GENERATOR", sequenceName="SEQ_GES_ESTIMULO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GES_ESTIMULO_ID_GENERATOR")
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

	//bi-directional many-to-one association to GesTipoEstimulo
	@ManyToOne
	@JoinColumn(name="id_tipo_estimulo")
	private GesTipoEstimulo gesTipoEstimulo;

	//bi-directional many-to-one association to GesEstimuloPersona
	@OneToMany(mappedBy="gesEstimulo")
	private List<GesEstimuloPersona> gesEstimuloPersonas;

	public GesEstimulo() {
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

	public GesTipoEstimulo getGesTipoEstimulo() {
		return this.gesTipoEstimulo;
	}

	public void setGesTipoEstimulo(GesTipoEstimulo gesTipoEstimulo) {
		this.gesTipoEstimulo = gesTipoEstimulo;
	}

	public List<GesEstimuloPersona> getGesEstimuloPersonas() {
		return this.gesEstimuloPersonas;
	}

	public void setGesEstimuloPersonas(List<GesEstimuloPersona> gesEstimuloPersonas) {
		this.gesEstimuloPersonas = gesEstimuloPersonas;
	}

	public GesEstimuloPersona addGesEstimuloPersona(GesEstimuloPersona gesEstimuloPersona) {
		getGesEstimuloPersonas().add(gesEstimuloPersona);
		gesEstimuloPersona.setGesEstimulo(this);

		return gesEstimuloPersona;
	}

	public GesEstimuloPersona removeGesEstimuloPersona(GesEstimuloPersona gesEstimuloPersona) {
		getGesEstimuloPersonas().remove(gesEstimuloPersona);
		gesEstimuloPersona.setGesEstimulo(null);

		return gesEstimuloPersona;
	}

}