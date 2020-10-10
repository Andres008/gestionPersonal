package ec.mil.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ges_dependencia_persona database table.
 * 
 */
@Entity
@Table(name="ges_dependencia_persona")
@NamedQuery(name="GesDependenciaPersona.findAll", query="SELECT g FROM GesDependenciaPersona g")
public class GesDependenciaPersona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GES_DEPENDENCIA_PERSONA_ID_GENERATOR", sequenceName="SEQ_GES_DEPENDENCIA_PERSONA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GES_DEPENDENCIA_PERSONA_ID_GENERATOR")
	private long id;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_final")
	private Date fechaFinal;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicial")
	private Date fechaInicial;

	//bi-directional many-to-one association to GesDependencia
	@ManyToOne
	@JoinColumn(name="id_dependencia")
	private GesDependencia gesDependencia;

	//bi-directional many-to-one association to GesPersona
	@ManyToOne
	@JoinColumn(name="cedula")
	private GesPersona gesPersona;

	public GesDependenciaPersona() {
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

	public GesDependencia getGesDependencia() {
		return this.gesDependencia;
	}

	public void setGesDependencia(GesDependencia gesDependencia) {
		this.gesDependencia = gesDependencia;
	}

	public GesPersona getGesPersona() {
		return this.gesPersona;
	}

	public void setGesPersona(GesPersona gesPersona) {
		this.gesPersona = gesPersona;
	}

}