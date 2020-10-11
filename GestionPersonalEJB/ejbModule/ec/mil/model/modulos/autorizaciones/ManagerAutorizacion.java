package ec.mil.model.modulos.autorizaciones;



import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.mil.model.dao.entidades.AutRolMenu;
import ec.mil.model.dao.entidades.AutRole;
import ec.mil.model.dao.entidades.AutUsuario;
import ec.mil.model.dao.manager.ManagerDAOGestionPersonal;

/**
 * Session Bean implementation class ManagerAutorizacion
 */
@Stateless(mappedName = "managerAutorizacion")
@LocalBean
public class ManagerAutorizacion {

	@EJB
	ManagerDAOGestionPersonal managerDAOGestionPersonal;
	
    /**
     * Default constructor. 
     */
    public ManagerAutorizacion() {
        // TODO Auto-generated constructor stub
    }
    
    
    /***********************************************************************************
	 * METODOS PARA MANEJO DE ACCESOS
	 ***********************************************************************************/

	/**
	 * Comprueba la validez de codigo/clave para un usuario específico.
	 * 
	 * @param pIdUsuario Codigo del usuario.
	 * @param pClave     La clave de seguridad del usuario.
	 * @return Credencial de seguridad que permite el acceso a los métodos de los
	 *         diferentes modulos.
	 * @throws Exception
	 */
	public Credencial obtenerAcceso(String pIdUsuario, String pClave) throws Exception {
		AutUsuario usuario = findByIdAutUsuario(pIdUsuario);
		Credencial credencial = new Credencial();
		
		
		if (usuario == null)
			throw new Exception("Usuario no existe, verifique su código.");
		if (usuario.getEstado().equalsIgnoreCase("N"))
			throw new Exception("El usuario no está activo.");
		if (!pClave.equals(usuario.getClave()))
			throw new Exception("Verifique su contraseña.");
		
		// System.out.println(usuario.getApellidos()+" "+usuario.getNombres()+"
		// "+pIdUsuario);
		credencial = new Credencial();
		credencial.setIdUsuario(pIdUsuario);
		credencial.setCorreo(usuario.getGesPersona().getCorreo());
		credencial.setPrimerInicio(usuario.getPrimerInicio());
		return credencial;
	}
	
	/**
	 * Finder para buscar un usuario especifico.
	 * 
	 * @param idUsuario el codigo unico del usuario.
	 * @return El usuario buscado.
	 * @throws Exception
	 */
	public AutUsuario findByIdAutUsuario(String idUsuario) throws Exception {
		AutUsuario usuario = (AutUsuario) managerDAOGestionPersonal.findById(AutUsuario.class, idUsuario);
		return usuario;
	}


	@SuppressWarnings("unchecked")
	public List<AutRolMenu> findRolMenuByRol(AutRole objAutRol) throws Exception {
		try {
			List<AutRolMenu> lstAutRolMenu=managerDAOGestionPersonal.findWhere(AutRolMenu.class, "o.autRole.id="+objAutRol.getId(), "o.autMenu.orden ASC") ;
			for (AutRolMenu autRolMenu : lstAutRolMenu) {
				autRolMenu.getAutMenu().getAutPerfiles().forEach(perfiles->{
					perfiles.getNombre();
				});
			}
			return lstAutRolMenu;
		} catch (Exception e) {
			throw new Exception("Error al buscar Menu por Rol");
		}
	}

}
