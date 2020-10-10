package ec.mil.model.modulos.ModelUtil;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class JSFUtil {
	/**
	 * Crea un mensaje JSF
	 * @param severidad Puede tomar el valor de:
	 * <ul>
	 * <li>FacesMessage.SEVERITY_FATAL
	 * <li>FacesMessage.SEVERITY_ERROR
	 * <li>FacesMessage.SEVERITY_WARN
	 * <li>FacesMessage.SEVERITY_INFO
	 * </ul>
	 * @param mensaje Contenido del mensaje
	 */
	public static void crearMensaje(Severity severidad,String mensaje){
		FacesMessage msg = new FacesMessage();
		msg.setSeverity(severidad);
		msg.setSummary(mensaje);
		//msg.setDetail(detalle);
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	/**
	 * Crea un mensaje JSF
	 * @param severidad Puede tomar el valor de:
	 * <ul>
	 * <li>FacesMessage.SEVERITY_FATAL
	 * <li>FacesMessage.SEVERITY_ERROR
	 * <li>FacesMessage.SEVERITY_WARN
	 * <li>FacesMessage.SEVERITY_INFO
	 * </ul>
	 * @param mensaje Contenido del mensaje.
	 * @param detalle Detalle del mensaje.
	 */
	public static void crearMensaje(Severity severidad,String mensaje,String detalle){
		FacesMessage msg = new FacesMessage();
		msg.setSeverity(severidad);
		msg.setSummary(mensaje);
		msg.setDetail(detalle);
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	/**
	 * Crea un mensaje JSF de tipo de severidad FATAL.
	 * @param mensaje Contenido del mensaje.
	 * @param detalle Detalle del mensaje. Puede ser null.
	 */
	public static void crearMensajeFATAL(String mensaje,String detalle){
		crearMensaje(FacesMessage.SEVERITY_FATAL, mensaje,detalle);
	}
	
	/**
	 * Crea un mensaje JSF de tipo de severidad ERROR.
	 * @param mensaje Contenido del mensaje.
	 * @param detalle Detalle del mensaje. Puede ser null.
	 */
	public static void crearMensajeERROR(String mensaje,String detalle){
		crearMensaje(FacesMessage.SEVERITY_ERROR, mensaje,detalle);
	}
	
	/**
	 * Crea un mensaje JSF de tipo de severidad WARNING.
	 * @param mensaje Contenido del mensaje.
	 * @param detalle Detalle del mensaje. Puede ser null.
	 */
	public static void crearMensajeWARN(String mensaje,String detalle){
		crearMensaje(FacesMessage.SEVERITY_WARN, mensaje,detalle);
	}
	
	/**
	 * Crea un mensaje JSF de tipo de severidad INFORMATIVE.
	 * @param mensaje Contenido del mensaje.
	 * @param detalle Detalle del mensaje. Puede ser null.
	 */
	public static void crearMensajeINFO(String mensaje,String detalle){
		crearMensaje(FacesMessage.SEVERITY_INFO, mensaje,detalle);
	}
}
