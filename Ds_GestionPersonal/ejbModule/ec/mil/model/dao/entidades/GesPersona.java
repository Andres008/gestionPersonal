package ec.mil.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ges_personas database table.
 * 
 */
@Entity
@Table(name="ges_personas")
@NamedQuery(name="GesPersona.findAll", query="SELECT g FROM GesPersona g")
public class GesPersona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cedula;

	private String apellido;

	private String direccion;
	
	private String correo;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_alta")
	private Date fechaAlta;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_baja")
	private Date fechaBaja;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	private String nombre;

	//bi-directional many-to-one association to AcaPersonasCurso
	@OneToMany(mappedBy="gesPersona")
	private List<AcaPersonasCurso> acaPersonasCursos;

	//bi-directional one-to-one association to AutUsuario
	@OneToOne(mappedBy="gesPersona")
	private AutUsuario autUsuario;

	//bi-directional many-to-one association to GesDependenciaPersona
	@OneToMany(mappedBy="gesPersona")
	private List<GesDependenciaPersona> gesDependenciaPersonas;

	//bi-directional many-to-one association to GesEstimuloPersona
	@OneToMany(mappedBy="gesPersona")
	private List<GesEstimuloPersona> gesEstimuloPersonas;

	//bi-directional many-to-one association to GesGradosPersona
	@OneToMany(mappedBy="gesPersona")
	private List<GesGradosPersona> gesGradosPersonas;

	//bi-directional many-to-one association to GesEstadoCivil
	@ManyToOne
	@JoinColumn(name="id_estado_civil")
	private GesEstadoCivil gesEstadoCivil;

	//bi-directional many-to-one association to GesGrado
	@ManyToOne
	@JoinColumn(name="id_grado_actual")
	private GesGrado gesGrado;

	//bi-directional many-to-one association to GesTipoSangre
	@ManyToOne
	@JoinColumn(name="id_tipo_sangre")
	private GesTipoSangre gesTipoSangre;

	public GesPersona() {
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return this.fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<AcaPersonasCurso> getAcaPersonasCursos() {
		return this.acaPersonasCursos;
	}

	public void setAcaPersonasCursos(List<AcaPersonasCurso> acaPersonasCursos) {
		this.acaPersonasCursos = acaPersonasCursos;
	}

	public AcaPersonasCurso addAcaPersonasCurso(AcaPersonasCurso acaPersonasCurso) {
		getAcaPersonasCursos().add(acaPersonasCurso);
		acaPersonasCurso.setGesPersona(this);

		return acaPersonasCurso;
	}

	public AcaPersonasCurso removeAcaPersonasCurso(AcaPersonasCurso acaPersonasCurso) {
		getAcaPersonasCursos().remove(acaPersonasCurso);
		acaPersonasCurso.setGesPersona(null);

		return acaPersonasCurso;
	}

	public AutUsuario getAutUsuario() {
		return this.autUsuario;
	}

	public void setAutUsuario(AutUsuario autUsuario) {
		this.autUsuario = autUsuario;
	}

	public List<GesDependenciaPersona> getGesDependenciaPersonas() {
		return this.gesDependenciaPersonas;
	}

	public void setGesDependenciaPersonas(List<GesDependenciaPersona> gesDependenciaPersonas) {
		this.gesDependenciaPersonas = gesDependenciaPersonas;
	}

	public GesDependenciaPersona addGesDependenciaPersona(GesDependenciaPersona gesDependenciaPersona) {
		getGesDependenciaPersonas().add(gesDependenciaPersona);
		gesDependenciaPersona.setGesPersona(this);

		return gesDependenciaPersona;
	}

	public GesDependenciaPersona removeGesDependenciaPersona(GesDependenciaPersona gesDependenciaPersona) {
		getGesDependenciaPersonas().remove(gesDependenciaPersona);
		gesDependenciaPersona.setGesPersona(null);

		return gesDependenciaPersona;
	}

	public List<GesEstimuloPersona> getGesEstimuloPersonas() {
		return this.gesEstimuloPersonas;
	}

	public void setGesEstimuloPersonas(List<GesEstimuloPersona> gesEstimuloPersonas) {
		this.gesEstimuloPersonas = gesEstimuloPersonas;
	}

	public GesEstimuloPersona addGesEstimuloPersona(GesEstimuloPersona gesEstimuloPersona) {
		getGesEstimuloPersonas().add(gesEstimuloPersona);
		gesEstimuloPersona.setGesPersona(this);

		return gesEstimuloPersona;
	}

	public GesEstimuloPersona removeGesEstimuloPersona(GesEstimuloPersona gesEstimuloPersona) {
		getGesEstimuloPersonas().remove(gesEstimuloPersona);
		gesEstimuloPersona.setGesPersona(null);

		return gesEstimuloPersona;
	}

	public List<GesGradosPersona> getGesGradosPersonas() {
		return this.gesGradosPersonas;
	}

	public void setGesGradosPersonas(List<GesGradosPersona> gesGradosPersonas) {
		this.gesGradosPersonas = gesGradosPersonas;
	}

	public GesGradosPersona addGesGradosPersona(GesGradosPersona gesGradosPersona) {
		getGesGradosPersonas().add(gesGradosPersona);
		gesGradosPersona.setGesPersona(this);

		return gesGradosPersona;
	}

	public GesGradosPersona removeGesGradosPersona(GesGradosPersona gesGradosPersona) {
		getGesGradosPersonas().remove(gesGradosPersona);
		gesGradosPersona.setGesPersona(null);

		return gesGradosPersona;
	}

	public GesEstadoCivil getGesEstadoCivil() {
		return this.gesEstadoCivil;
	}

	public void setGesEstadoCivil(GesEstadoCivil gesEstadoCivil) {
		this.gesEstadoCivil = gesEstadoCivil;
	}

	public GesGrado getGesGrado() {
		return this.gesGrado;
	}

	public void setGesGrado(GesGrado gesGrado) {
		this.gesGrado = gesGrado;
	}

	public GesTipoSangre getGesTipoSangre() {
		return this.gesTipoSangre;
	}

	public void setGesTipoSangre(GesTipoSangre gesTipoSangre) {
		this.gesTipoSangre = gesTipoSangre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}