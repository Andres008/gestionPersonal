package ec.mil.controladores.session;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import ec.mil.model.modulos.ModelUtil.ModelUtil;
import ec.mil.model.modulos.autorizaciones.Credencial;
import ec.mil.model.modulos.log.ManagerLog;

@Named("beanLogin")
@ApplicationScoped
public class BeanLogin implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	ManagerLog managerLog ;

		
	 /* Token de seguridad.
	 */
	private Credencial credencial;

	
	
	public BeanLogin(){

	}
	
	/**
	 * Cierre de sesion.
	 * @return redireccion a index.xhtml
	 */
	public String actionSalirSistema(){
		if(credencial!=null)
			managerLog.generarLogAuditoria(credencial, this.getClass(), "actionSalirSistema", "Cierre de sesion");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index.xhtml?faces-redirect=true"; 
	}
	
	
	
	public int anioActual()
	{
		return ModelUtil.getAnioActual();
	}
	
	
	
	public String actionSeleccionarSistema(/*AutSistema sistemaSeleccion*/) {
		/*sistemaSeleccionado = new AutSistema();
		sistemaSeleccionado=sistemaSeleccion;
		/*model = new DefaultMenuModel();
		System.out.println("LLEGA");
		DefaultMenuItem item;
		for (AutSubsistema autSistema : sistemaSeleccion.getAutSubsistemas()) {
			 //Second submenu
	        DefaultSubMenu subMenu = new DefaultSubMenu(autSistema.getDescripcion());
	        
	        	for (AutModulo autSistema2 : autSistema.getAutModulos()) {
	        		item = new DefaultMenuItem(autSistema2.getDescripcion());
	    	        item.setIcon("ui-icon-disk");
	    	        subMenu.addElement(item);
				}
	        	model.addElement(subMenu);
		}
		System.out.println("Principal");*/
		return "/modulos/menu.xhtml?faces-redirect=true";
	}
	
	public String actionMostrarMenuPrincipal(){
	/*	if(credencial==null)
			return actionSalirSistema();*/
		return "/modulos/menu?faces-redirect=true";
	}
	
	public String actionMostrarMenuInicial(){
		if(credencial==null)
			return actionSalirSistema();
		return "/modulos/gestion/plantilla?faces-redirect=true";
	}
	
	public String actionRuta(/*AutPerfil autPerfil*/){
		//return autPerfil.getRuta()+"?faces-redirect=true";
		return "";
	}
	
	
	
	
	/*******************************************************************
	    *********************Manejo de Path de reportes*********************
	    ********************************************************************
	    ******************************************************************** 
	     */
	    
	    /*******************************************************************
	     * Metodo que retorna el path de almacenamiento de los reportes.
	     * @return valor Path
	     */
	     
	    public String getPathReportes(){
	        String valorPath="";
	       /* try {
	            valorPath = managerParametros.getValorParametro("PATH_REPORTE");
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }*/
	        return valorPath;
	    }
	    
	    /*******************************************************************
		    *********************Manejo de Path de Imagenes*********************
		    ********************************************************************
		    ******************************************************************** 
		     */
		    
		    /*******************************************************************
		     * Metodo que retorna el path de almacenamiento de Imagenes de los reportes
		     * @return valor Path
		     */
		     
		    public String getPathImagesReportes(){
		        String valorPath="";
		       /* try {
		            valorPath = managerParametros.getValorParametro("PATH_IMAGES_REPORTES");
		        } catch (Exception e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }*/
		        return valorPath;
		    }
	
	/*******************************************************************
	****************Manejo de Sesion para todo el sistema***************
	********************************************************************
	******************************************************************** 
	 */
	
	/*******************************************************************
	 * Metodo que retorna en minutos el tiempo maximo de inactividad para cerrar la session a un usuario.
	 * @return valor en segundos del tiempo
	 */
	 
	public int getTiempoCierreSesion(){
		int valorMinutos=0;
		/*try {
			valorMinutos = managerParametros.getParametroInteger("TIEMPO_CIERRE_SESION");
			valorMinutos= (valorMinutos*60)*1000;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return valorMinutos;
	}
	
	/**
	 * Metodo que retorna la frase de para el cierre de sesion.
	 * @return String de la frase del mensaje
	 */
	public String getFraseCierreSesion(){
		return "Usted ha excedido el tiempo de inactividad, confirme la solicitud para volver a ingresar al sistema.";
	}
	
	/**
	 * Metodo que retorna el titulo del cierre de sesion.
	 * @return Frase del titulo del mensaje
	 */
	public String getTituloCierreSesion(){
		return "Cierre de Sesion";
	}
	
	/**
	 * Metodo que retorna el titulo del boton.
	 * @return Frase del titulo del boton
	 */
	public String getTituloBotonCierreSesion(){
		return "Confirmar";
	}

	public Credencial getCredencial() {
		return credencial;
	}

	public void setCredencial(Credencial credencial) {
		this.credencial = credencial;
	}

}
