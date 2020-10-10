package ec.mil.model.modulos.log;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.mil.model.dao.entidades.LogCategoriaEvento;
import ec.mil.model.dao.entidades.LogGeneral;
import ec.mil.model.dao.manager.ManagerDAOGestionPersonal;
import ec.mil.model.modulos.autorizaciones.Credencial;

/**
 * Session Bean implementation class ManagerLog
 */
@Stateless(mappedName = "managerLog")
@LocalBean
public class ManagerLog {
	
	public static final long LOG_GENERAL=1;
	public static final long LOG_USABILIDAD=2;
	public static final long LOG_AUDITORIA=3;
	public static final long LOG_ERROR_GENERAL=4;
	public static final long LOG_HISTORICO_GENERAL=4;
	
	@EJB
	ManagerDAOGestionPersonal managerDAOGestionPersonal;

    /**
     * Default constructor. 
     */
    public ManagerLog() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Genera una entrada en el log general del sistema.
     * @param idTipoEvento El tipo de evento. Deben utilizarse las siguientes constantes:
     * 					<ul>
     * 						<li><b>ManagerLog.LOG_GENERAL</b> Bitacora general del sistema. Incluye eventos sin clasificar.
     * 						<li><b>ManagerLog.LOG_USABILIDAD</b>Bitacora para estadisticas de uso del sistema. Permitira a los
     * 								desarrolladores verificar el porcentaje de uso de los diversas funciones del sistema.
     * 						<li><b>ManagerLog.LOG_AUDITORIA</b>Bitacora para auditoria informatica del sistema.
     * 						<li><b>ManagerLog.LOG_ERROR_GENERAL</b>Bitacora para errores en general del sistema.
     *  					<li><b>ManagerLog.LOG_HISTORICO_GENERAL</b>Bitacora para datos historicos en general del sistema.
     *  					<li><b>ManagerLog.LOG_HISTORICO_FICHA_SOCIOEC</b>Bitacora para datos historicos de fichas socioeconomicas.
     * 					</ul>
     * @param credencial Credencial del usuario que accedio al sistema.
     * @param clase Clase java que genera el evento.
     * @param metodo Nombre del metodo que genera el evento.
     * @param detalle Detalle del evento.
     */
    @SuppressWarnings("rawtypes")
	private void generarLog(long idTipoEvento,Credencial credencial,Class clase,String metodo,String detalle){
    	LogGeneral nuevoEvento;
    	nuevoEvento=new LogGeneral();
    	LogCategoriaEvento tipoEvento=null;
    	Date fecha=new Date();
    	
    	//buscamos el tipo de evento correspondiente:
    	try {
			tipoEvento=findByIdLogTiposEvento(idTipoEvento);
			if(tipoEvento==null)
				System.out.println("ERROR LOG NO EXISTE TIPO EVENTO: "+idTipoEvento);
		} catch (Exception e1) {
			System.out.println("ERROR BUSCANDO LOG TIPO EVENTO: "+e1.getMessage());
		}
    	nuevoEvento.setDireccionIp(credencial.getDireccionIP());
    	nuevoEvento.setFechaEvento(fecha);
    	nuevoEvento.setUsuario(credencial.getIdUsuario());
    	nuevoEvento.setMensaje(detalle);
    	nuevoEvento.setMetodo(clase.getCanonicalName()+"["+ metodo +"]");
    	nuevoEvento.setLogCategoriaEvento(tipoEvento);
    	
    	try {
			managerDAOGestionPersonal.insertar(nuevoEvento);
		} catch (Exception e) {
			System.out.println("ERROR LOG: "+e.getMessage());
		}
    }
    
    
    public LogCategoriaEvento findByIdLogTiposEvento(long idTipoEvento) throws Exception{
    	LogCategoriaEvento evento=(LogCategoriaEvento) managerDAOGestionPersonal.findById(LogCategoriaEvento.class, idTipoEvento);
    	return evento;
    }
    
    
    @SuppressWarnings("rawtypes")
  	public void generarLogGeneral(Credencial credencial,Class clase,String metodo,String detalle){
      	generarLog(LOG_GENERAL, credencial, clase, metodo, detalle);
      }
      @SuppressWarnings("rawtypes")
  	public void generarLogUsabilidad(Credencial credencial,Class clase,String metodo,String detalle){
      	generarLog(LOG_USABILIDAD, credencial, clase, metodo, detalle);
      }
      @SuppressWarnings("rawtypes")
  	public void generarLogAuditoria(Credencial credencial,Class clase,String metodo,String detalle){
      	generarLog(LOG_AUDITORIA, credencial, clase, metodo, detalle);
      }
      @SuppressWarnings("rawtypes")
  	public void generarLogErrorGeneral(Credencial credencial,Class clase,String metodo,String detalle){
      	generarLog(LOG_ERROR_GENERAL, credencial, clase, metodo, detalle);
      }
    
    

}
