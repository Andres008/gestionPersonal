package ec.mil.controladores.webServices;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import ec.mil.controladores.session.BeanLogin;
import ec.mil.model.modulos.autorizaciones.Credencial;
import ec.mil.model.modulos.autorizaciones.ManagerAutorizacion;
import ec.mil.model.modulos.log.ManagerLog;

@WebService(name = "Credenciales")
@ApplicationScoped
public class WsAcceso {
	

	@EJB
	ManagerAutorizacion managerAutorizacion;
	
	@EJB
	ManagerLog managerLog;

	@ManagedProperty ("#{beanLogin}")
	private BeanLogin beanLogin;

	public WsAcceso() {
		// TODO Auto-generated constructor stub
	}
	
	@WebMethod
	public String verificarAcceso( @WebParam(name ="idUsuario") String idUsuario, @WebParam(name="clave")String clave ) {
		try {
			managerAutorizacion.obtenerAcceso(idUsuario, clave);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "Ok";
	}
	
	@WebMethod
	public String consultaPagoBySuministro(@WebParam(name = "suministro") BigDecimal suministro) 
	{
		return suministro.toString();
	}
	
}
