package ec.mil.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ges_tipo_grado database table.
 * 
 */
@Entity
@Table(name="ges_tipo_grado")
@NamedQuery(name="GesTipoGrado.findAll", query="SELECT g FROM GesTipoGrado g")
public class GesTipoGrado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GES_TIPO_GRADO_ID_GENERATOR", sequenceName="SEQ_GES_TIPO_GRADO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GES_TIPO_GRADO_ID_GENERATOR")
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

	//bi-directional many-to-one association to GesGrado
	@OneToMany(mappedBy="gesTipoGrado")
	private List<GesGrado> gesGrados;

	public GesTipoGrado() {
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

	public List<GesGrado> getGesGrados() {
		return this.gesGrados;
	}

	public void setGesGrados(List<GesGrado> gesGrados) {
		this.gesGrados = gesGrados;
	}

	public GesGrado addGesGrado(GesGrado gesGrado) {
		getGesGrados().add(gesGrado);
		gesGrado.setGesTipoGrado(this);

		return gesGrado;
	}

	public GesGrado removeGesGrado(GesGrado gesGrado) {
		getGesGrados().remove(gesGrado);
		gesGrado.setGesTipoGrado(null);

		return gesGrado;
	}

}