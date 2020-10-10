package ec.mil.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ges_estimulo_personas database table.
 * 
 */
@Entity
@Table(name="ges_estimulo_personas")
@NamedQuery(name="GesEstimuloPersona.findAll", query="SELECT g FROM GesEstimuloPersona g")
public class GesEstimuloPersona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GES_ESTIMULO_PERSONAS_ID_GENERATOR", sequenceName="SEQ_GES_ESTIMULO_PERSONAS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GES_ESTIMULO_PERSONAS_ID_GENERATOR")
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	//bi-directional many-to-one association to GesEstimulo
	@ManyToOne
	@JoinColumn(name="id_estimulo")
	private GesEstimulo gesEstimulo;

	//bi-directional many-to-one association to GesPersona
	@ManyToOne
	@JoinColumn(name="cedula")
	private GesPersona gesPersona;

	public GesEstimuloPersona() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public GesEstimulo getGesEstimulo() {
		return this.gesEstimulo;
	}

	public void setGesEstimulo(GesEstimulo gesEstimulo) {
		this.gesEstimulo = gesEstimulo;
	}

	public GesPersona getGesPersona() {
		return this.gesPersona;
	}

	public void setGesPersona(GesPersona gesPersona) {
		this.gesPersona = gesPersona;
	}

}