package ec.mil.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ges_grados_persona database table.
 * 
 */
@Entity
@Table(name="ges_grados_persona")
@NamedQuery(name="GesGradosPersona.findAll", query="SELECT g FROM GesGradosPersona g")
public class GesGradosPersona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GES_GRADOS_PERSONA_ID_GENERATOR", sequenceName="SEQ_GES_GRADOS_PERSONA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GES_GRADOS_PERSONA_ID_GENERATOR")
	private long id;

	private BigDecimal antiguedad;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_final")
	private Date fechaFinal;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicial")
	private Date fechaInicial;

	//bi-directional many-to-one association to GesGrado
	@ManyToOne
	@JoinColumn(name="id_grado")
	private GesGrado gesGrado;

	//bi-directional many-to-one association to GesPersona
	@ManyToOne
	@JoinColumn(name="cedula")
	private GesPersona gesPersona;

	public GesGradosPersona() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getAntiguedad() {
		return this.antiguedad;
	}

	public void setAntiguedad(BigDecimal antiguedad) {
		this.antiguedad = antiguedad;
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

	public GesGrado getGesGrado() {
		return this.gesGrado;
	}

	public void setGesGrado(GesGrado gesGrado) {
		this.gesGrado = gesGrado;
	}

	public GesPersona getGesPersona() {
		return this.gesPersona;
	}

	public void setGesPersona(GesPersona gesPersona) {
		this.gesPersona = gesPersona;
	}

}