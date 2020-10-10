package ec.mil.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the aca_tipo_curso database table.
 * 
 */
@Entity
@Table(name="aca_tipo_curso")
@NamedQuery(name="AcaTipoCurso.findAll", query="SELECT a FROM AcaTipoCurso a")
public class AcaTipoCurso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ACA_TIPO_CURSO_ID_GENERATOR", sequenceName="SEQ_ACA_TIPO_CURSO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACA_TIPO_CURSO_ID_GENERATOR")
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

	//bi-directional many-to-one association to AcaCurso
	@OneToMany(mappedBy="acaTipoCurso")
	private List<AcaCurso> acaCursos;

	public AcaTipoCurso() {
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

	public List<AcaCurso> getAcaCursos() {
		return this.acaCursos;
	}

	public void setAcaCursos(List<AcaCurso> acaCursos) {
		this.acaCursos = acaCursos;
	}

	public AcaCurso addAcaCurso(AcaCurso acaCurso) {
		getAcaCursos().add(acaCurso);
		acaCurso.setAcaTipoCurso(this);

		return acaCurso;
	}

	public AcaCurso removeAcaCurso(AcaCurso acaCurso) {
		getAcaCursos().remove(acaCurso);
		acaCurso.setAcaTipoCurso(null);

		return acaCurso;
	}

}