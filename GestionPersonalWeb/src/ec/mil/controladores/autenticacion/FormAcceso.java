package ec.mil.controladores.autenticacion;

/**
 * 
 */
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import ec.mil.controladores.session.BeanLogin;
import ec.mil.model.dao.entidades.AutPerfile;
import ec.mil.model.dao.entidades.AutRolMenu;
import ec.mil.model.dao.entidades.AutRole;
import ec.mil.model.dao.entidades.AutUsuario;
import ec.mil.model.modulos.ModelUtil.JSFUtil;
import ec.mil.model.modulos.ModelUtil.ModelUtil;
import ec.mil.model.modulos.autUsuarios.ManagerUsuarios;
import ec.mil.model.modulos.autorizaciones.Credencial;
import ec.mil.model.modulos.autorizaciones.ManagerAutorizacion;
import ec.mil.model.modulos.gestioPersonal.ManagerGestionPersonal;
import ec.mil.model.modulos.log.ManagerLog;

import java.io.Serializable;

/**
 * @author Acardenas
 * 
 */
@Named("formAcceso")
@SessionScoped
public class FormAcceso implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AutUsuario objAutUsuario;
	private String idUsuario;
	private String clave;
	private MenuModel model;
	private Boolean panelCambioContr;

	@EJB
	ManagerAutorizacion managerAutorizacion;

	@EJB
	ManagerLog managerLog;

	@EJB
	ManagerUsuarios managerUsuario;

	@ManagedProperty("#{beanLogin}")
	private BeanLogin beanLogin;

	@PostConstruct
	public void inicializar() {
		beanLogin = new BeanLogin();
	}

	public void cambiarContraseñaPrimerAcceso() {
		try {
			if (!objAutUsuario.getCedula().equals(objAutUsuario.getClave()))
			{
				objAutUsuario.setClave(ModelUtil.md5(objAutUsuario.getClave()));
				objAutUsuario.setPrimerInicio("NO");
				managerUsuario.actualizarUsuario(objAutUsuario);
				managerLog.generarLogUsabilidad(beanLogin.getCredencial(), this.getClass(), "cambiarContraseñaPrimerAcceso",
						"Se cambio la contrasenia por primer uso de usuario: " + objAutUsuario.getCedula());
				JSFUtil.crearMensajeINFO("Atención", "Contraseña Actualizada.");
				inicializarCredenciales();
			}else
			{
				JSFUtil.crearMensajeWARN("Advertencia", "La clave no puede ser igual al usuario (Número de cédula).");
			}
			
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR("Error", e.getMessage());
			managerLog.generarLogErrorGeneral(beanLogin.getCredencial(), this.getClass(),
					"cambiarContraseñaPrimerAcceso",
					"Error al cambiar contraseña primer uso: " + objAutUsuario.getCedula());
			e.printStackTrace();
		}
	}

	public void inicializarCredenciales() {
		panelCambioContr = false;
		objAutUsuario = new AutUsuario();
	}

	public void menuByRol(AutRole objAutRol) throws Exception {
		List<AutRolMenu> lstAutRolMenu = managerAutorizacion.findRolMenuByRol(objAutRol);
		model = new DefaultMenuModel();
		for (AutRolMenu autRolMenu : lstAutRolMenu) {
			// First submenu
			DefaultSubMenu submenu = DefaultSubMenu.builder().label(autRolMenu.getAutMenu().getNombre()).build();
			for (AutPerfile autPerfile : autRolMenu.getAutMenu().getAutPerfiles()) {
				DefaultMenuItem item = DefaultMenuItem.builder().value(autPerfile.getNombre()).icon("pi pi-save")
						.command("#{formAcceso.acceso('" + autPerfile.getUrl() + "')}").update("messages").build();
				submenu.getElements().add(item);
			}
			model.getElements().add(submenu);
		}
	}

	public String acceso(String ruta) {
		System.out.println(ruta + "?faces-redirect=true");
		return ruta + "?faces-redirect=true";
	}

	/*
	 * public String getBrowserName() { ExternalContext externalContext =
	 * FacesContext.getCurrentInstance().getExternalContext(); String userAgent =
	 * externalContext.getRequestHeaderMap().get("User-Agent");
	 * 
	 * if(userAgent.contains("MSIE")){ return "Internet Explorer"; }
	 * if(userAgent.contains("Firefox")){ return "Firefox"; }
	 * if(userAgent.contains("Chrome")){ return "Chrome"; }
	 * if(userAgent.contains("Opera")){ return "Opera"; }
	 * if(userAgent.contains("Safari")){ return "Safari"; } return "Unknown"; }
	 */
	public String actionObtenerAcceso() {

		try {
			Credencial credencial = managerAutorizacion.obtenerAcceso(idUsuario, ModelUtil.md5(clave));
			objAutUsuario = managerAutorizacion.findByIdAutUsuario(idUsuario);
			// se configura la direccion IP del cliente:
			menuByRol(managerAutorizacion.findByIdAutUsuario(idUsuario).getAutRole());
			HttpServletRequest request;
			request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String remoteAddr = request.getRemoteAddr() + " " + request.getHeader("user-agent");
			if (remoteAddr.length() > 200)
				credencial.setDireccionIP(remoteAddr.substring(0, 199));
			else
				credencial.setDireccionIP(remoteAddr);
			// una vez que se obtiene la credencial, se llenan datos en el
			// BeanLogin para la sesion:
			beanLogin.setCredencial(credencial);
			// cargamos el menu de opciones
			// beanLogin.setMenuRaiz(managerAutorizacion.crearAutMenu());
			// para el resto de generacion de bitacoras unicamente almacenamos la direccion
			// IP:
			credencial.setDireccionIP(request.getRemoteAddr());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("credencial", credencial);
			if (credencial.getPrimerInicio().equals("SI")) {
				objAutUsuario.setClave("");
				panelCambioContr = true;
				return "";
			}
			managerLog.generarLogUsabilidad(beanLogin.getCredencial(), this.getClass(), "actionObtenerAcceso",
					"Se ingresa al sistema");
			return "/modulos/menu?faces-redirect=true";

		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage(), null);
			e.printStackTrace();
			return "";
		}

	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}

	public AutUsuario getObjAutUsuario() {
		return objAutUsuario;
	}

	public void setObjAutUsuario(AutUsuario objAutUsuario) {
		this.objAutUsuario = objAutUsuario;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public MenuModel getMenuModel() {
		return model;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.model = menuModel;
	}

	public Boolean getPanelCambioContr() {
		return panelCambioContr;
	}

	public void setPanelCambioContr(Boolean panelCambioContr) {
		this.panelCambioContr = panelCambioContr;
	}

}
