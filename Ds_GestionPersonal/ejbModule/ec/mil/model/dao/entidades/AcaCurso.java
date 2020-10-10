package ec.mil.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the aca_curso database table.
 * 
 */
@Entity
@Table(name="aca_curso")
@NamedQuery(name="AcaCurso.findAll", query="SELECT a FROM AcaCurso a")
public class AcaCurso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ACA_CURSO_ID_GENERATOR", sequenceName="SEQ_ACA_CURSO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACA_CURSO_ID_GENERATOR")
	private long id;

	private String descripcion;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_final")
	private Date fechaFinal;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicial")
	private Date fechaInicial;

	private BigDecimal horas;

	private String nombre;

	//bi-directional many-to-one association to AcaTipoCurso
	@ManyToOne
	@JoinColumn(name="id_tipo_curso")
	private AcaTipoCurso acaTipoCurso;

	//bi-directional many-to-one association to AcaCursoPrerequisito
	@OneToMany(mappedBy="acaCurso")
	private List<AcaCursoPrerequisito> acaCursoPrerequisitos;

	//bi-directional many-to-one association to AcaGradoPrerequisito
	@OneToMany(mappedBy="acaCurso")
	private List<AcaGradoPrerequisito> acaGradoPrerequisitos;

	//bi-directional many-to-one association to AcaPersonasCurso
	@OneToMany(mappedBy="acaCurso")
	private List<AcaPersonasCurso> acaPersonasCursos;

	public AcaCurso() {
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

	public BigDecimal getHoras() {
		return this.horas;
	}

	public void setHoras(BigDecimal horas) {
		this.horas = horas;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public AcaTipoCurso getAcaTipoCurso() {
		return this.acaTipoCurso;
	}

	public void setAcaTipoCurso(AcaTipoCurso acaTipoCurso) {
		this.acaTipoCurso = acaTipoCurso;
	}

	public List<AcaCursoPrerequisito> getAcaCursoPrerequisitos() {
		return this.acaCursoPrerequisitos;
	}

	public void setAcaCursoPrerequisitos(List<AcaCursoPrerequisito> acaCursoPrerequisitos) {
		this.acaCursoPrerequisitos = acaCursoPrerequisitos;
	}

	public AcaCursoPrerequisito addAcaCursoPrerequisito(AcaCursoPrerequisito acaCursoPrerequisito) {
		getAcaCursoPrerequisitos().add(acaCursoPrerequisito);
		acaCursoPrerequisito.setAcaCurso(this);

		return acaCursoPrerequisito;
	}

	public AcaCursoPrerequisito removeAcaCursoPrerequisito(AcaCursoPrerequisito acaCursoPrerequisito) {
		getAcaCursoPrerequisitos().remove(acaCursoPrerequisito);
		acaCursoPrerequisito.setAcaCurso(null);

		return acaCursoPrerequisito;
	}

	public List<AcaGradoPrerequisito> getAcaGradoPrerequisitos() {
		return this.acaGradoPrerequisitos;
	}

	public void setAcaGradoPrerequisitos(List<AcaGradoPrerequisito> acaGradoPrerequisitos) {
		this.acaGradoPrerequisitos = acaGradoPrerequisitos;
	}

	public AcaGradoPrerequisito addAcaGradoPrerequisito(AcaGradoPrerequisito acaGradoPrerequisito) {
		getAcaGradoPrerequisitos().add(acaGradoPrerequisito);
		acaGradoPrerequisito.setAcaCurso(this);

		return acaGradoPrerequisito;
	}

	public AcaGradoPrerequisito removeAcaGradoPrerequisito(AcaGradoPrerequisito acaGradoPrerequisito) {
		getAcaGradoPrerequisitos().remove(acaGradoPrerequisito);
		acaGradoPrerequisito.setAcaCurso(null);

		return acaGradoPrerequisito;
	}

	public List<AcaPersonasCurso> getAcaPersonasCursos() {
		return this.acaPersonasCursos;
	}

	public void setAcaPersonasCursos(List<AcaPersonasCurso> acaPersonasCursos) {
		this.acaPersonasCursos = acaPersonasCursos;
	}

	public AcaPersonasCurso addAcaPersonasCurso(AcaPersonasCurso acaPersonasCurso) {
		getAcaPersonasCursos().add(acaPersonasCurso);
		acaPersonasCurso.setAcaCurso(this);

		return acaPersonasCurso;
	}

	public AcaPersonasCurso removeAcaPersonasCurso(AcaPersonasCurso acaPersonasCurso) {
		getAcaPersonasCursos().remove(acaPersonasCurso);
		acaPersonasCurso.setAcaCurso(null);

		return acaPersonasCurso;
	}

}