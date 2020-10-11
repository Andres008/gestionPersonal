/**
 * 
 */
package ec.mil.controladores.gestionPersonal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import ec.mil.controladores.session.BeanLogin;
import ec.mil.model.dao.entidades.GesEstadoCivil;
import ec.mil.model.dao.entidades.GesGrado;
import ec.mil.model.dao.entidades.GesPersona;
import ec.mil.model.dao.entidades.GesTipoSangre;
import ec.mil.model.modulos.ModelUtil.JSFUtil;
import ec.mil.model.modulos.ModelUtil.ModelUtil;
import ec.mil.model.modulos.gestioPersonal.ManagerGestionPersonal;
import ec.mil.model.modulos.log.ManagerLog;

@Named("controllerPersonal")
@SessionScoped
/**
 * @author acardenas
 *
 */
public class ControllerPersonal implements Serializable {

	/**
	 * 
	 */
	@EJB
	private ManagerGestionPersonal managerGestionPersonal;
	@EJB
	private ManagerLog managerLog;
	@ManagedProperty(value = "#{beanLogin}")
	private BeanLogin beanLogin;
	private static final long serialVersionUID = 1L;
	private GesPersona objGesPersona;
	private List<GesPersona> lstGesPersona;

	/**
	 * 
	 */
	public ControllerPersonal() {
		// TODO Auto-generated constructor stub
	}

	public void inicializarPersona() {
		System.out.println(beanLogin);
		objGesPersona = new GesPersona();
		objGesPersona.setGesEstadoCivil(new GesEstadoCivil());
		objGesPersona.setGesGrado(new GesGrado());
		objGesPersona.setGesTipoSangre(new GesTipoSangre());
		try {
			lstGesPersona = managerGestionPersonal.buscarPersonas();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR("Error", e.getMessage());
			managerLog.generarLogErrorGeneral(beanLogin.getCredencial(), this.getClass(), "inicializarPersona",
					e.getMessage());
			e.printStackTrace();
		}
	}

	public List<SelectItem> SIestadoCivil() {
		List<SelectItem> lstSiEstadoCilvil = new ArrayList<SelectItem>();
		try {
			List<GesEstadoCivil> lstEstadoCivil = managerGestionPersonal.buscarEstadoCivilActivo();
			for (GesEstadoCivil gesEstadoCivil : lstEstadoCivil) {
				SelectItem siEstCivil = new SelectItem();
				siEstCivil.setLabel(gesEstadoCivil.getNombre());
				siEstCivil.setValue(gesEstadoCivil.getId());
				lstSiEstadoCilvil.add(siEstCivil);
			}
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR("Error", e.getMessage());
			managerLog.generarLogErrorGeneral(beanLogin.getCredencial(), this.getClass(), "SIestadoCivil",
					e.getMessage());
			e.printStackTrace();
		}
		return lstSiEstadoCilvil;
	}

	public List<SelectItem> SIgrados() {
		List<SelectItem> lstSiGrados = new ArrayList<SelectItem>();
		try {
			List<GesGrado> lstGrados = managerGestionPersonal.buscarGradoActivo();
			for (GesGrado gesEstadoCivil : lstGrados) {
				SelectItem siEstCivil = new SelectItem();
				siEstCivil.setLabel(gesEstadoCivil.getGrado());
				siEstCivil.setValue(gesEstadoCivil.getId());
				lstSiGrados.add(siEstCivil);
			}
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR("Error", e.getMessage());
			managerLog.generarLogErrorGeneral(beanLogin.getCredencial(), this.getClass(), "SIgrados", e.getMessage());
			e.printStackTrace();
		}
		return lstSiGrados;
	}

	public List<SelectItem> SITipoSangre() {
		List<SelectItem> lstSiTipoSangre = new ArrayList<SelectItem>();
		try {
			List<GesTipoSangre> lstTipoSangre = managerGestionPersonal.buscarTipoSangreActivo();
			for (GesTipoSangre gesEstadoCivil : lstTipoSangre) {
				SelectItem siTipSangre = new SelectItem();
				siTipSangre.setLabel(gesEstadoCivil.getNombre());
				siTipSangre.setValue(gesEstadoCivil.getId());
				lstSiTipoSangre.add(siTipSangre);
			}
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR("Error", e.getMessage());
			managerLog.generarLogErrorGeneral(beanLogin.getCredencial(), this.getClass(), "SITipoSangre",
					e.getMessage());
			e.printStackTrace();
		}
		return lstSiTipoSangre;
	}

	public void ingresarPersona() {
		try {
			objGesPersona.setApellido(ModelUtil.cambiarMayusculas( objGesPersona.getApellido()));
			objGesPersona.setNombre(ModelUtil.cambiarMayusculas(objGesPersona.getNombre()));
			objGesPersona.setCorreo(ModelUtil.cambiarMinusculas(objGesPersona.getCorreo()));
			managerGestionPersonal.ingresarPersona(objGesPersona);
			/*managerLog.generarLogUsabilidad(beanLogin.getCredencial(), this.getClass(), "ingresarPersona",
					"Se ingreso persona id " + objGesPersona.getCedula());*/
			JSFUtil.crearMensajeINFO("Mensaje", "Ingreso Correcto.");
			inicializarPersona();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JSFUtil.crearMensajeERROR("Error", e.getMessage());
			managerLog.generarLogErrorGeneral(beanLogin.getCredencial(), this.getClass(), "ingresarPersona",
					e.getMessage());
			e.printStackTrace();
		}
	}
	

	/*
	 * 
	 * Metodos Accesores y modificadores
	 * 
	 */
	public GesPersona getObjGesPersona() {
		return objGesPersona;
	}

	public void setObjGesPersona(GesPersona objGesPersona) {
		this.objGesPersona = objGesPersona;
	}

	public List<GesPersona> getLstGesPersona() {
		return lstGesPersona;
	}

	public void setLstGesPersona(List<GesPersona> lstGesPersona) {
		this.lstGesPersona = lstGesPersona;
	}

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}

}
