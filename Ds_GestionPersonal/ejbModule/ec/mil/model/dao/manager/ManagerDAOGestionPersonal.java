package ec.mil.model.dao.manager;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 * Session Bean implementation class ManagerGestionPersonal
 */
@Stateless(mappedName = "managerGestionPersonal")
@LocalBean
public class ManagerDAOGestionPersonal {

	@PersistenceContext (unitName = "Ds_GestionPersonal")
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerDAOGestionPersonal() {
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * finder Generico que devuelve todas las entidades de una tabla.
	 * 
	 * @param clase
	 *            La clase que se desea consultar. Por ejemplo:
	 *            <ul>
	 *            <li>Usuario.class</li>
	 *            </ul>
	 * @param orderBy
	 *            Expresión que indica la propiedad de la entidad por la que se
	 *            desea ordenar la consulta. Debe utilizar el alias "o" para
	 *            nombrar a la(s) propiedad(es) por la que se va a ordenar. por
	 *            ejemplo:
	 *            <ul>
	 *            <li>o.nombre</li>
	 *            <li>o.codigo,o.nombre</li>
	 *            </ul>
	 *            Puede aceptar null o una cadena vacia, en este caso no
	 *            ordenara el resultado.
	 * @return Listado de entidades resultante.
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List findAll(Class clase, String orderBy) throws Exception {
		Query q;
		List listado;
		String sentenciaSQL;

		if (orderBy == null || orderBy.length() == 0)
			sentenciaSQL = "SELECT o FROM " + clase.getSimpleName() + " o";
		else
			sentenciaSQL = "SELECT o FROM " + clase.getSimpleName()
					+ " o ORDER BY " + orderBy;

		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		// mLog.MostrarLog(this.getClass(),"findAll",sentenciaSQL);
		return listado;
	}

	/**
	 * finder Generico que devuelve todos las entidades de una tabla.
	 * 
	 * @param clase
	 *            La clase que se desea consultar. Por ejemplo:
	 *            <ul>
	 *            <li>Usuario.class</li>
	 *            </ul>
	 * 
	 * @return Listado resultante.
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	
	public List findAll(Class clase) throws Exception {
		return findAll(clase, null);
	}

	/**
	 * Finder genérico para buscar un objeto especifico.
	 * 
	 * @param clase
	 *            La clase sobre la que se desea consultar, ejemplo:
	 *            Usuario.class
	 * @param pID
	 *            Identificador (la clave primaria) que permitira la busqueda.
	 * @return El objeto solicitado (si existiera).
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	
	public Object findById(Class clase, Object pID) throws Exception {
		if (pID == null)
			throw new Exception(
					"Debe especificar el código para buscar el dato.");
		Object o;
		try {
			o = em.find(clase, pID);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al buscar la información especificada ("
					+ pID + ") : " + e.getMessage());
		}
		return o;
	}

	/**
	 * Finder generico que permite aplicar clausulas where y order by.
	 * 
	 * @param clase
	 *            La entidad sobre la que se desea consultar. Ej: Usuario.class
	 * @param pClausulaWhere
	 *            Clausula where de tipo JPQL (sin la palabra reservada WHERE).
	 *            Ejemplo:
	 *            <ul>
	 *            <li>o.nombre='Antonio'</li>
	 *            <li>o.nombre='Antonio' and o.telefono='0444-434'</li>
	 *            <li>o.nombre like 'Ant%'</li>
	 *            </ul>
	 * @param pOrderBy
	 *            Clausula order by de tipo JPQL (sin la palabra reservada ORDER
	 *            BY). Puede ser null para no ordenar. por ejemplo:
	 *            <ul>
	 *            <li>o.nombre</li>
	 *            <li>o.codigo,o.nombre</li>
	 *            </ul>
	 *            Tanto para la clausula <b>where</b> como <b>order by</b> debe
	 *            utilizarse el alias de entidad "o".
	 * @return Listado resultante.
	 */
	@SuppressWarnings("rawtypes")
	
	public List findWhere(Class clase, String pClausulaWhere, String pOrderBy)
			throws Exception {
		// mostrarLog("findAll(where): "+clase.getSimpleName());
		Query q;
		List listado;
		String sentenciaSQL;

		if (pOrderBy == null || pOrderBy.length() == 0)
			sentenciaSQL = "SELECT o FROM " + clase.getSimpleName()
					+ " o WHERE " + pClausulaWhere;

		else
			sentenciaSQL = "SELECT o FROM " + clase.getSimpleName()
					+ " o WHERE " + pClausulaWhere + " ORDER BY " + pOrderBy;
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		// managerLog.MostrarLog(this.getClass(),"findWhere",sentenciaSQL);
		return listado;
	}

	/**
	 * Almacena un objeto en la base de datos.
	 * 
	 * @param pObjeto
	 *            El objeto a insertar.
	 * @throws Exception
	 */
	
	public void insertar(Object pObjeto) throws Exception {
		if (pObjeto == null)
			throw new Exception("No se puede insertar un objeto null.");
		try {
			em.persist(pObjeto);
			// mostrarLog("Objeto insertado: "+pObjeto.getClass().getSimpleName());
		} catch (PersistenceException e) {
			Throwable a=e.getCause();
			
			// mostrarLog("No se pudo insertar el objeto especificado: "+pObjeto.getClass().getSimpleName());
		
			throw new Exception("No se pudo insertar el objeto especificado ("
					+ pObjeto.getClass().getCanonicalName() + ") "
					+ e.getMessage());
		}
	}

	/**
	 * Elimina un objeto de la persistencia.
	 * 
	 * @param clase
	 *            La clase correspondiente al objeto que se desea eliminar.
	 * @param pID
	 *            El identificador del objeto que se desea eliminar.
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	
	public void eliminar(Class clase, Object pID) throws Exception {
		if (pID == null) {
			// mostrarLog("Debe especificar un identificador para eliminar el dato solicitado: "+clase.getSimpleName());
			throw new Exception(
					"Debe especificar un identificador para eliminar el dato solicitado.");
		}
		Object o = findById(clase, pID);

		try {
			em.remove(o);
			// mostrarLog("Dato eliminado: "+clase.getSimpleName()+" "
			// +pID.toString());
		} catch (Exception e) {
			// mostrarLog("No se pudo eliminar el dato: "+clase.getSimpleName());
			throw new Exception("No se pudo eliminar el dato (" + pID + ")("
					+ clase.getCanonicalName() + ") : " + e.getMessage());
		}
	}

	/**
	 * Actualiza la información de un objeto en la persistencia.
	 * 
	 * @param pObjeto
	 *            Objeto que contiene la información que se debe actualizar.
	 * @throws Exception
	 */
	
	public void actualizar(Object pObjeto) throws Exception {
		if (pObjeto == null)
			throw new Exception("No se puede actualizar un objeto null");
		/*
		 * if(!em.getTransaction().isActive()) em.getTransaction().begin();
		 */
		try {
			em.merge(pObjeto);
			// mostrarLog("Dato actualizado: "+pObjeto.getClass().getSimpleName());
		} catch (Exception e) {
			// em.getTransaction().rollback();
			throw new Exception("No se pudo actualizar el dato solicitado ("
					+ pObjeto.getClass().getCanonicalName() + "): "
					+ e.getMessage());
		}
		// em.getTransaction().commit();
	}

	
	public Long obtenerSecuenciaPostgresql(String nombreSecuencia)
			throws Exception {
		String sentenciaSQL;
		Query q;
		BigInteger valSeq;
		Long valorSeq = null;

		try {
			sentenciaSQL = "SELECT nextval('" + nombreSecuencia + "')";
			q = em.createNativeQuery(sentenciaSQL);
			valSeq = (BigInteger) q.getSingleResult();
			valorSeq = valSeq.longValue();
		} catch (Exception e) {
			// managerLog.MostrarLog(this.getClass(),
			// "obtenerSecuencia","Error al obtener valor de secuencia: "+e.getMessage());
			e.printStackTrace();
			throw new Exception("Error al obtener valor de secuencia: "
					+ nombreSecuencia);
		}
		return valorSeq;
	}

	
	public Long obtenerSecuenciaOracle(String nombreSecuencia) throws Exception {
		String sentenciaSQL;
		Query q;
		Long valorSeq = null;

		try {
			sentenciaSQL = "SELECT " + nombreSecuencia + ".nextval FROM DUAL";
			q = em.createNativeQuery(sentenciaSQL);
			BigDecimal valTmp = (BigDecimal) q.getSingleResult();
			valorSeq = valTmp.longValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al obtener valor de secuencia: "
					+ nombreSecuencia);
		}
		return valorSeq;
	}

	
	public Long obtenerCantTramitesEjecutados(long idTramitePadre)
			throws Exception {
		String sentenciaSQL;
		Query q;
		int valTmp=0;
		try {
			sentenciaSQL = "select * from TRM_TRAMITES where ID_TRAMITE "
					     + " in (select ID_TRAMITE from TRM_TRAMITE_PADRE where ID_PADRE = "+idTramitePadre+" ) "
					     + " and id_ultimo_estado_tramite = 88";
			//System.out.println(sentenciaSQL);
			q = em.createNativeQuery(sentenciaSQL);
			valTmp =q.getResultList().size();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al obtener la cantidad de trámites ejecutados del Tramite Padre: "
					+ idTramitePadre);
		}
		return Long.valueOf(valTmp);
	}
	
	/**
	 * Obtiene el valor maximo de una propiedad correspondiente a una entidad.
	 * @param clase La clase sobre la que se quiere consultar.
	 * @param nombrePropiedad El nombre de la propiedad sobre la que se quiere obtener el valor maximo.
	 * @return El valor maximo.
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Long obtenerValorMax(Class clase,String nombrePropiedad) throws Exception{
		Query q;
		String sentenciaSQL;
		Long valorMax;
		try {
			sentenciaSQL="SELECT MAX(o."+nombrePropiedad+") FROM "+clase.getSimpleName()+" o";
			q = em.createQuery(sentenciaSQL);
			if(q.getSingleResult()==null) {
				valorMax=(long) 0;
			}
			else {
			valorMax=(Long)q.getSingleResult();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al obtener valor max: "+clase.getCanonicalName()+"["+nombrePropiedad+"]. "+e.getMessage());
		}
		return valorMax;
	}
	
	/*
	 * FUNCION QUE NOS PERMITE EJECUTAR UN PROCEDIMIENTO DE LA BASE DE DATOS
	 * PARAMETRO: RECIBE UNA STRING CON EL NOMBRE Y LOS PARAMETROS PARA EJECUTAR LA FUNCION
	 * EJEMPLO SIN PARAMETROS: THMCP_PRO_DESC_DIAVACACIONES();
	 * EJEMPLO CON PARAMETROS: THMCP_PRO_MARC_TO_ASIST2('01','2015');
	 * OBSERVACION: EL PARAMETRO DEBE SER RECIBIDO INCLUYENDO EL ; 
	 */
	public String ejecutarFuncion(String procedimiento) throws Exception{
		Query q;
		String sentenciaSQL;
		try {
			sentenciaSQL="select "+ procedimiento+" from dual";
			System.out.println(sentenciaSQL);
			q = em.createNativeQuery(sentenciaSQL);
			sentenciaSQL= (String) q.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al ejecutar la sentencia");
		}
		return sentenciaSQL;
	}

}
