package ec.mil.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ges_estado_civil database table.
 * 
 */
@Entity
@Table(name="ges_estado_civil")
@NamedQuery(name="GesEstadoCivil.findAll", query="SELECT g FROM GesEstadoCivil g")
public class GesEstadoCivil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GES_ESTADO_CIVIL_ID_GENERATOR", sequenceName="SEQ_GES_ESTADO_CIVIL")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GES_ESTADO_CIVIL_ID_GENERATOR")
	private long id;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_final")
	private Date fechaFinal;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicial")
	private Date fechaInicial;

	private String nombre;

	//bi-directional many-to-one association to GesPersona
	@OneToMany(mappedBy="gesEstadoCivil")
	private List<GesPersona> gesPersonas;

	public GesEstadoCivil() {
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

	public List<GesPersona> getGesPersonas() {
		return this.gesPersonas;
	}

	public void setGesPersonas(List<GesPersona> gesPersonas) {
		this.gesPersonas = gesPersonas;
	}

	public GesPersona addGesPersona(GesPersona gesPersona) {
		getGesPersonas().add(gesPersona);
		gesPersona.setGesEstadoCivil(this);

		return gesPersona;
	}

	public GesPersona removeGesPersona(GesPersona gesPersona) {
		getGesPersonas().remove(gesPersona);
		gesPersona.setGesEstadoCivil(null);

		return gesPersona;
	}

}