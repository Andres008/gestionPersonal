package ec.mil.controladores.gestion;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ec.mil.model.dao.entidades.GesTipoEstimulo;
import ec.mil.model.modulos.ModelUtil.JSFUtil;
import ec.mil.model.modulos.gestioPersonal.ManagerGestionPersonal;

@Named("controladorGestion")
@SessionScoped
public class ControladorGestion implements Serializable {

	@EJB
	private ManagerGestionPersonal managerGestionPersonal;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControladorGestion() {
	}
	
	public void holaMundo()
	{
		try {
			System.out.println(managerGestionPersonal.buscarTipoEstimulo().size());
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR("Atención", e.getMessage());
			e.printStackTrace();
		}
	}

}
