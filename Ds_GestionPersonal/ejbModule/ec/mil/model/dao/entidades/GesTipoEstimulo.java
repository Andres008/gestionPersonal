package ec.mil.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ges_tipo_estimulo database table.
 * 
 */
@Entity
@Table(name="ges_tipo_estimulo")
@NamedQuery(name="GesTipoEstimulo.findAll", query="SELECT g FROM GesTipoEstimulo g")
public class GesTipoEstimulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GES_TIPO_ESTIMULO_ID_GENERATOR", sequenceName="SEQ_GES_TIPO_ESTIMULO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GES_TIPO_ESTIMULO_ID_GENERATOR")
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

	//bi-directional many-to-one association to GesEstimulo
	@OneToMany(mappedBy="gesTipoEstimulo")
	private List<GesEstimulo> gesEstimulos;

	public GesTipoEstimulo() {
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

	public List<GesEstimulo> getGesEstimulos() {
		return this.gesEstimulos;
	}

	public void setGesEstimulos(List<GesEstimulo> gesEstimulos) {
		this.gesEstimulos = gesEstimulos;
	}

	public GesEstimulo addGesEstimulo(GesEstimulo gesEstimulo) {
		getGesEstimulos().add(gesEstimulo);
		gesEstimulo.setGesTipoEstimulo(this);

		return gesEstimulo;
	}

	public GesEstimulo removeGesEstimulo(GesEstimulo gesEstimulo) {
		getGesEstimulos().remove(gesEstimulo);
		gesEstimulo.setGesTipoEstimulo(null);

		return gesEstimulo;
	}

}