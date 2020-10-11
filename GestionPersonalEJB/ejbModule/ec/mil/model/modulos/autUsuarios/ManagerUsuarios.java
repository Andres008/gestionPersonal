package ec.mil.model.modulos.autUsuarios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.mil.model.dao.entidades.AutRole;
import ec.mil.model.dao.entidades.AutUsuario;
import ec.mil.model.dao.manager.ManagerDAOGestionPersonal;

/**
 * Session Bean implementation class ManagerUsuarios
 */
@Stateless(mappedName = "managerUsuarios")
@LocalBean
public class ManagerUsuarios {

    /**
     * Default constructor. 
     */
	@EJB
	ManagerDAOGestionPersonal managerDAOGestionPersonal;
    public ManagerUsuarios() {
        // TODO Auto-generated constructor stub
    }
	@SuppressWarnings("unchecked")
	public List<AutUsuario> buscarTodosUsuarios() throws Exception {
		try {
			return managerDAOGestionPersonal.findAll(AutUsuario.class, "o.gesPersona.apellido ASC");
		} catch (Exception e) {
			throw new Exception("Error al buscar usuarios.");
		}
	}
	@SuppressWarnings("unchecked")
	public List<AutRole> findRoleActivo() throws Exception {
		try {
			return managerDAOGestionPersonal.findWhere(AutRole.class, "o.estado='A'", "o.nombre ASC");
		} catch (Exception e) {
			throw new Exception("Error al buscar Roles activos.");
		}
	}
	public void ingresarUsuario(AutUsuario objAutUsuario) throws Exception {
		try {
			managerDAOGestionPersonal.insertar(objAutUsuario);
		} catch (Exception e) {
			throw new Exception("Error al insertar usuario");
		}		
	}
	public void actualizarUsuario(AutUsuario usuario) throws Exception {
		try {
			managerDAOGestionPersonal.actualizar(usuario);
		} catch (Exception e) {
			throw new Exception("Error an actualizar "+usuario.getCedula());
		}		
	}
    
    

}
