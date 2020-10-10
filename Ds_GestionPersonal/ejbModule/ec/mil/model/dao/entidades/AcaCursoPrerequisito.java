package ec.mil.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the aca_curso_prerequisito database table.
 * 
 */
@Entity
@Table(name="aca_curso_prerequisito")
@NamedQuery(name="AcaCursoPrerequisito.findAll", query="SELECT a FROM AcaCursoPrerequisito a")
public class AcaCursoPrerequisito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ACA_CURSO_PREREQUISITO_IDPREREQUISITO_GENERATOR", sequenceName="SEQ_ACA_CURSO_PREREQUISITO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACA_CURSO_PREREQUISITO_IDPREREQUISITO_GENERATOR")
	@Column(name="id_prerequisito")
	private long idPrerequisito;

	@Temporal(TemporalType.DATE)
	private Date estado;

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

	public AcaCursoPrerequisito() {
	}

	public long getIdPrerequisito() {
		return this.idPrerequisito;
	}

	public void setIdPrerequisito(long idPrerequisito) {
		this.idPrerequisito = idPrerequisito;
	}

	public Date getEstado() {
		return this.estado;
	}

	public void setEstado(Date estado) {
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

	public AcaCurso getAcaCurso() {
		return this.acaCurso;
	}

	public void setAcaCurso(AcaCurso acaCurso) {
		this.acaCurso = acaCurso;
	}

}