package ec.mil.model.dao.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the log_categoria_eventos database table.
 * 
 */
@Entity
@Table(name="log_categoria_eventos")
@NamedQuery(name="LogCategoriaEvento.findAll", query="SELECT l FROM LogCategoriaEvento l")
public class LogCategoriaEvento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LOG_CATEGORIA_EVENTOS_ID_GENERATOR", sequenceName="SEQ_LOG_CATEGORIA_EVENTOS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOG_CATEGORIA_EVENTOS_ID_GENERATOR")
	private long id;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to LogGeneral
	@OneToMany(mappedBy="logCategoriaEvento")
	private List<LogGeneral> logGenerals;

	public LogCategoriaEvento() {
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<LogGeneral> getLogGenerals() {
		return this.logGenerals;
	}

	public void setLogGenerals(List<LogGeneral> logGenerals) {
		this.logGenerals = logGenerals;
	}

	public LogGeneral addLogGeneral(LogGeneral logGeneral) {
		getLogGenerals().add(logGeneral);
		logGeneral.setLogCategoriaEvento(this);

		return logGeneral;
	}

	public LogGeneral removeLogGeneral(LogGeneral logGeneral) {
		getLogGenerals().remove(logGeneral);
		logGeneral.setLogCategoriaEvento(null);

		return logGeneral;
	}

}