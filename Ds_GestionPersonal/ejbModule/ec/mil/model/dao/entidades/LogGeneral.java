package ec.mil.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the log_general database table.
 * 
 */
@Entity
@Table(name="log_general")
@NamedQuery(name="LogGeneral.findAll", query="SELECT l FROM LogGeneral l")
public class LogGeneral implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LOG_GENERAL_ID_GENERATOR", sequenceName="SEQ_LOG_GENERAL", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOG_GENERAL_ID_GENERATOR")
	private long id;

	@Column(name="direccion_ip")
	private String direccionIp;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_evento")
	private Date fechaEvento;

	private String mensaje;

	private String metodo;

	private String usuario;

	//bi-directional many-to-one association to LogCategoriaEvento
	@ManyToOne
	@JoinColumn(name="id_categoria")
	private LogCategoriaEvento logCategoriaEvento;

	public LogGeneral() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDireccionIp() {
		return this.direccionIp;
	}

	public void setDireccionIp(String direccionIp) {
		this.direccionIp = direccionIp;
	}

	public Date getFechaEvento() {
		return this.fechaEvento;
	}

	public void setFechaEvento(Date fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMetodo() {
		return this.metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public LogCategoriaEvento getLogCategoriaEvento() {
		return this.logCategoriaEvento;
	}

	public void setLogCategoriaEvento(LogCategoriaEvento logCategoriaEvento) {
		this.logCategoriaEvento = logCategoriaEvento;
	}

}