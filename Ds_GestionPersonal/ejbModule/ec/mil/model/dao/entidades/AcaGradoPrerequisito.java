package ec.mil.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the aca_grado_prerequisito database table.
 * 
 */
@Entity
@Table(name="aca_grado_prerequisito")
@NamedQuery(name="AcaGradoPrerequisito.findAll", query="SELECT a FROM AcaGradoPrerequisito a")
public class AcaGradoPrerequisito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ACA_GRADO_PREREQUISITO_ID_GENERATOR", sequenceName="SEQ_ACA_GRADO_PREREQUISITO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACA_GRADO_PREREQUISITO_ID_GENERATOR")
	private long id;

	//bi-directional many-to-one association to AcaCurso
	@ManyToOne
	@JoinColumn(name="id_curso")
	private AcaCurso acaCurso;

	//bi-directional many-to-one association to GesGrado
	@ManyToOne
	@JoinColumn(name="id_grado")
	private GesGrado gesGrado;

	public AcaGradoPrerequisito() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AcaCurso getAcaCurso() {
		return this.acaCurso;
	}

	public void setAcaCurso(AcaCurso acaCurso) {
		this.acaCurso = acaCurso;
	}

	public GesGrado getGesGrado() {
		return this.gesGrado;
	}

	public void setGesGrado(GesGrado gesGrado) {
		this.gesGrado = gesGrado;
	}

}