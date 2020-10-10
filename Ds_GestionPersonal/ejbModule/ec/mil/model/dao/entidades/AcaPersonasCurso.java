package ec.mil.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the aca_personas_cursos database table.
 * 
 */
@Entity
@Table(name="aca_personas_cursos")
@NamedQuery(name="AcaPersonasCurso.findAll", query="SELECT a FROM AcaPersonasCurso a")
public class AcaPersonasCurso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ACA_PERSONAS_CURSOS_IDPERSONASCURSOS_GENERATOR", sequenceName="SEQ_ACA_PERSONAS_CURSOS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACA_PERSONAS_CURSOS_IDPERSONASCURSOS_GENERATOR")
	@Column(name="id_personas_cursos")
	private long idPersonasCursos;

	private String adjunto;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_final")
	private Date fechaFinal;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicial")
	private Date fechaInicial;

	//bi-directional many-to-one association to AcaCurso
	@ManyToOne
	@JoinColumn(name="id_curso")
	private AcaCurso acaCurso;

	//bi-directional many-to-one association to GesPersona
	@ManyToOne
	@JoinColumn(name="cedula")
	private GesPersona gesPersona;

	public AcaPersonasCurso() {
	}

	public long getIdPersonasCursos() {
		return this.idPersonasCursos;
	}

	public void setIdPersonasCursos(long idPersonasCursos) {
		this.idPersonasCursos = idPersonasCursos;
	}

	public String getAdjunto() {
		return this.adjunto;
	}

	public void setAdjunto(String adjunto) {
		this.adjunto = adjunto;
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

	public AcaCurso getAcaCurso() {
		return this.acaCurso;
	}

	public void setAcaCurso(AcaCurso acaCurso) {
		this.acaCurso = acaCurso;
	}

	public GesPersona getGesPersona() {
		return this.gesPersona;
	}

	public void setGesPersona(GesPersona gesPersona) {
		this.gesPersona = gesPersona;
	}

}