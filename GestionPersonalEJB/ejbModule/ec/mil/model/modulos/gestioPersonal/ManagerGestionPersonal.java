package ec.mil.model.modulos.gestioPersonal;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.mil.model.dao.entidades.GesEstadoCivil;
import ec.mil.model.dao.entidades.GesGrado;
import ec.mil.model.dao.entidades.GesPersona;
import ec.mil.model.dao.entidades.GesTipoEstimulo;
import ec.mil.model.dao.entidades.GesTipoSangre;
import ec.mil.model.dao.manager.ManagerDAOGestionPersonal;

/**
 * Session Bean implementation class ManagerGestionPersonal
 */
@Stateless(mappedName = "managerGestionPersonal")
@LocalBean
public class ManagerGestionPersonal {

	@EJB
	private ManagerDAOGestionPersonal managerDAOGestionPersonal;

	/**
	 * Default constructor.
	 */
	public ManagerGestionPersonal() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<GesTipoEstimulo> buscarTipoEstimulo() throws Exception {
		List<GesTipoEstimulo> lstTipoEstimulo;
		try {
			lstTipoEstimulo = managerDAOGestionPersonal.findAll(GesTipoEstimulo.class, null);
		} catch (Exception e) {
			throw new Exception("Error al buscar lista Tipo estimulo.");
		}
		return lstTipoEstimulo;
	}

	@SuppressWarnings("unchecked")
	public List<GesPersona> buscarPersonas() throws Exception {
		try {
			return managerDAOGestionPersonal.findAll(GesPersona.class, "o.apellido ASC, o.nombre ASC ");
		} catch (Exception e) {
			throw new Exception("Error al buscar personas");
		}
	}

	@SuppressWarnings("unchecked")
	public List<GesEstadoCivil> buscarEstadoCivilActivo() throws Exception {
		try {
			return managerDAOGestionPersonal.findWhere(GesEstadoCivil.class, "o.estado='A'", "o.nombre ASC");
		} catch (Exception e) {
			throw new Exception("Error al buscar estado Civil");
		}
	}

	@SuppressWarnings("unchecked")
	public List<GesGrado> buscarGradoActivo() throws Exception {
		try {
			return managerDAOGestionPersonal.findWhere(GesGrado.class, "o.estado='A'",
					"o.gesTipoGrado.id ASC, o.orden ASC");
		} catch (Exception e) {
			throw new Exception("Error al buscar Grados.");
		}
	}

	@SuppressWarnings("unchecked")
	public List<GesTipoSangre> buscarTipoSangreActivo() throws Exception {
		try {
			return managerDAOGestionPersonal.findWhere(GesTipoSangre.class, "o.estado='A'", "o.nombre ASC");
		} catch (Exception e) {
			throw new Exception("Error al buscar tipos de sangre");
		}
	}

	public void ingresarPersona(GesPersona objGesPersona) throws Exception {
		try {
			managerDAOGestionPersonal.insertar(objGesPersona);
		} catch (Exception e) {
			throw new Exception("Error al ingresar persona. " + objGesPersona.getCedula());
		}

	}

	public GesPersona buscarPersonaByCedula(String cedula) throws Exception {
		try {
			GesPersona v_persona = (GesPersona) managerDAOGestionPersonal.findById(GesPersona.class, cedula);
			if (v_persona == null)
				throw new Exception("Atención, persona no existe.");
			return v_persona;
		} catch (Exception e) {
			throw new Exception("Persona no registrada.");
		}
	}

}
