package ec.mil.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ges_grados database table.
 * 
 */
@Entity
@Table(name="ges_grados")
@NamedQuery(name="GesGrado.findAll", query="SELECT g FROM GesGrado g")
public class GesGrado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GES_GRADOS_ID_GENERATOR", sequenceName="SEQ_GES_GRADOS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GES_GRADOS_ID_GENERATOR")
	private long id;

	private String descripcion;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_final")
	private Date fechaFinal;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicial")
	private Date fechaInicial;

	private String grado;

	private BigDecimal tiempo;
	
	private BigDecimal orden;

	//bi-directional many-to-one association to AcaGradoPrerequisito
	@OneToMany(mappedBy="gesGrado")
	private List<AcaGradoPrerequisito> acaGradoPrerequisitos;

	//bi-directional many-to-one association to GesTipoGrado
	@ManyToOne
	@JoinColumn(name="id_tipo_grado")
	private GesTipoGrado gesTipoGrado;

	//bi-directional many-to-one association to GesGradosPersona
	@OneToMany(mappedBy="gesGrado")
	private List<GesGradosPersona> gesGradosPersonas;

	//bi-directional many-to-one association to GesPersona
	@OneToMany(mappedBy="gesGrado")
	private List<GesPersona> gesPersonas;

	public GesGrado() {
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

	public String getGrado() {
		return this.grado;
	}

	public void setGrado(String grado) {
		this.grado = grado;
	}

	public BigDecimal getTiempo() {
		return this.tiempo;
	}

	public void setTiempo(BigDecimal tiempo) {
		this.tiempo = tiempo;
	}

	public List<AcaGradoPrerequisito> getAcaGradoPrerequisitos() {
		return this.acaGradoPrerequisitos;
	}

	public void setAcaGradoPrerequisitos(List<AcaGradoPrerequisito> acaGradoPrerequisitos) {
		this.acaGradoPrerequisitos = acaGradoPrerequisitos;
	}

	public AcaGradoPrerequisito addAcaGradoPrerequisito(AcaGradoPrerequisito acaGradoPrerequisito) {
		getAcaGradoPrerequisitos().add(acaGradoPrerequisito);
		acaGradoPrerequisito.setGesGrado(this);

		return acaGradoPrerequisito;
	}

	public AcaGradoPrerequisito removeAcaGradoPrerequisito(AcaGradoPrerequisito acaGradoPrerequisito) {
		getAcaGradoPrerequisitos().remove(acaGradoPrerequisito);
		acaGradoPrerequisito.setGesGrado(null);

		return acaGradoPrerequisito;
	}

	public GesTipoGrado getGesTipoGrado() {
		return this.gesTipoGrado;
	}

	public void setGesTipoGrado(GesTipoGrado gesTipoGrado) {
		this.gesTipoGrado = gesTipoGrado;
	}

	public List<GesGradosPersona> getGesGradosPersonas() {
		return this.gesGradosPersonas;
	}

	public void setGesGradosPersonas(List<GesGradosPersona> gesGradosPersonas) {
		this.gesGradosPersonas = gesGradosPersonas;
	}

	public GesGradosPersona addGesGradosPersona(GesGradosPersona gesGradosPersona) {
		getGesGradosPersonas().add(gesGradosPersona);
		gesGradosPersona.setGesGrado(this);

		return gesGradosPersona;
	}

	public GesGradosPersona removeGesGradosPersona(GesGradosPersona gesGradosPersona) {
		getGesGradosPersonas().remove(gesGradosPersona);
		gesGradosPersona.setGesGrado(null);

		return gesGradosPersona;
	}

	public List<GesPersona> getGesPersonas() {
		return this.gesPersonas;
	}

	public void setGesPersonas(List<GesPersona> gesPersonas) {
		this.gesPersonas = gesPersonas;
	}

	public GesPersona addGesPersona(GesPersona gesPersona) {
		getGesPersonas().add(gesPersona);
		gesPersona.setGesGrado(this);

		return gesPersona;
	}

	public GesPersona removeGesPersona(GesPersona gesPersona) {
		getGesPersonas().remove(gesPersona);
		gesPersona.setGesGrado(null);

		return gesPersona;
	}

	public BigDecimal getOrden() {
		return orden;
	}

	public void setOrden(BigDecimal orden) {
		this.orden = orden;
	}

}