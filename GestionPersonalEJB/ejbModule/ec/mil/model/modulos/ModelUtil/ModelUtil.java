package ec.mil.model.modulos.ModelUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.application.FacesMessage;

public class ModelUtil {
	/**
	 * Verifica si una cadena es igual a null o tiene longitud igual a cero (0).
	 * 
	 * @param cadena Cadena que va a verificarse.
	 * @return
	 */
	public static boolean isEmpty(String cadena) {
		if (cadena == null || cadena.length() == 0)
			return true;
		return false;
	}

	/**
	 * Devuelve el valor del anio actual.
	 * 
	 * @return valor correspondiente al anio actual.
	 */
	public static int getAnioActual() {
		Date fechaActual = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy");
		int anioActual = Integer.parseInt(formato.format(fechaActual));
		return anioActual;
	}

	/**
	 * Devuelve la parte del anio de una fecha.
	 * 
	 * @param fecha La fecha a procesar.
	 * @return
	 */
	public static int getAnio(Date fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy");
		int anioActual = Integer.parseInt(formato.format(fecha));
		return anioActual;
	}

	/**
	 * Devuelve la parte del anio de una fecha.
	 * 
	 * @param fecha La fecha a procesar.
	 * @return
	 */
	public static int getMes(Date fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("MM");
		int anioActual = Integer.parseInt(formato.format(fecha));
		return anioActual;
	}

	/**
	 * Devuelve la hora.
	 * 
	 * @param fecha La fecha a procesar.
	 * @return
	 */
	public static int getHora(Date fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("HH");
		int anioActual = Integer.parseInt(formato.format(fecha));
		return anioActual;
	}

	/**
	 * Devuelve los minutos.
	 * 
	 * @param fecha La fecha a procesar.
	 * @return
	 */
	public static int getMinutos(Date fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("mm");
		int anioActual = Integer.parseInt(formato.format(fecha));
		return anioActual;
	}

	/**
	 * Devuelve la parte del anio de una fecha.
	 * 
	 * @param fecha La fecha a procesar.
	 * @return
	 */
	public static int getDia(Date fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("dd");
		int anioActual = Integer.parseInt(formato.format(fecha));
		return anioActual;
	}

	/**
	 * Devuelve el valor del mes actual.
	 * 
	 * @return valor correspondiente al mes actual.
	 */
	public static int getMesActual() {
		Date fechaActual = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("MM");
		int mesActual = Integer.parseInt(formato.format(fechaActual));
		return mesActual;
	}

	/**
	 * Suma o resta dias a una fecha.
	 * 
	 * @param Fecha  original.
	 * @param numero de dias a ser sumados.
	 * @return fecha sumada el numero de dias
	 */
	public static Date getSumarDias(Date fecha, int dias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha); // Configuramos la fecha que se recibe
		calendar.add(Calendar.DAY_OF_YEAR, dias); // numero de días a añadir, o restar en caso de días<0
		return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
	}

	// Diferencias entre dos fechas
	// @param fechaInicial La fecha de inicio
	// @param fechaFinal La fecha de fin
	// @return Retorna el numero de dias entre dos fechas
	public static int diferenciasDeFechas(Date fechaInicial, Date fechaFinal) {

		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		String fechaInicioString = df.format(fechaInicial);
		try {
			fechaInicial = df.parse(fechaInicioString);
		} catch (Exception ex) {
		}

		String fechaFinalString = df.format(fechaFinal);
		try {
			fechaFinal = df.parse(fechaFinalString);
		} catch (Exception ex) {
		}
		long fechaInicialMs = fechaInicial.getTime();
		long fechaFinalMs = fechaFinal.getTime();
		long diferencia = fechaFinalMs - fechaInicialMs;
		double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
		return ((int) dias);
	}

	public static String fecha(Date fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		return formato.format(fecha);
	}

	/**
	 * Devuelve el dia numerico.
	 * 
	 * @param fecha La fecha a procesar.
	 * @return
	 */
	public static String getDayAlfaNumerico(Date d) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(d);
		String diaAlfanumerico = "";

		switch (cal.get(Calendar.DAY_OF_WEEK)) {
		case 1:
			diaAlfanumerico = "Domingo";
			break;
		case 2:
			diaAlfanumerico = "Lunes";
			break;
		case 3:
			diaAlfanumerico = "Martes";
			break;
		case 4:
			diaAlfanumerico = "Miercoles";
			break;
		case 5:
			diaAlfanumerico = "Jueves";
			break;
		case 6:
			diaAlfanumerico = "Viernes";
			break;
		case 7:
			diaAlfanumerico = "Sabado";
			break;
		default:
			break;
		}
		return diaAlfanumerico;
	}
	
	public static String cambiarMayusculas(String frase) {
		return frase.toUpperCase();
	}
	
	public static String cambiarMinusculas(String frase) {
		return frase.toLowerCase();
	}

	/**
	 * Devuelve el mes alfanumerico.
	 * 
	 * @param fecha La fecha a procesar.
	 * @return
	 */
	public static String getMesAlfanumerico(Date fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("MM");
		int mesActual = Integer.parseInt(formato.format(fecha));
		String mesAlfanumerico = "";
		switch (mesActual) {
		case 1:
			mesAlfanumerico = "Enero";
			break;
		case 2:
			mesAlfanumerico = "Febrero";
			break;
		case 3:
			mesAlfanumerico = "Marzo";
			break;
		case 4:
			mesAlfanumerico = "Abril";
			break;
		case 5:
			mesAlfanumerico = "Mayo";
			break;
		case 6:
			mesAlfanumerico = "Junio";
			break;
		case 7:
			mesAlfanumerico = "Julio";
			break;
		case 8:
			mesAlfanumerico = "Agosto";
			break;
		case 9:
			mesAlfanumerico = "Septiembre";
			break;
		case 10:
			mesAlfanumerico = "Octubre";
			break;
		case 11:
			mesAlfanumerico = "Noviembre";
			break;
		case 12:
			mesAlfanumerico = "Diciembre";
			break;
		default:
			break;
		}
		return mesAlfanumerico;
	}

	public static BigDecimal diferenciaHoras(Date fechaHoraDesde, Date fechaHoraHasta) {
		long milliseconds = fechaHoraHasta.getTime() - fechaHoraDesde.getTime();
		double minutes = (double) ((milliseconds / (1000 * 60)) % 60);
		double hours = (double) ((milliseconds / (1000 * 60 * 60)) % 24);
		return new BigDecimal((hours + (minutes / 60)));
	}

	public String guardarAnexoPDF(InputStream inputstream,String nombreArchivo ,String ruta, String format) throws Exception {
		File fRuta = new File(ruta);
		if (!fRuta.exists()) {
			File directorio = new File(ruta);
			directorio.mkdirs();
			ruta = directorio.toString() + "/";
		}
		String nombreArchivoCompleto;
		if (ruta == null || ruta.length() == 0)
			throw new Exception("Error en parámetro RUTA_ARCHIVOS_ANEXOS, contacte al Administrador.");
		if (inputstream != null) {
			nombreArchivoCompleto = ruta + nombreArchivo + format;
			FileOutputStream fileOutput = new FileOutputStream(nombreArchivoCompleto);
			BufferedOutputStream bufferedOutput = new BufferedOutputStream(fileOutput);
			// Bucle para leer de un archivo y escribir en el otro.
			byte[] array = new byte[1000];
			int leidos = inputstream.read(array);
			while (leidos > 0) {
				bufferedOutput.write(array, 0, leidos);
				leidos = inputstream.read(array);
			}
			// Cierre de los ficheros
			inputstream.close();
			bufferedOutput.close();
		} else
			throw new Exception("Error guardando archivo: no existe el flujo indicado (inputStream)");
		return nombreArchivoCompleto;
	}
	
	/**
	 * 
	 * @param clave
	 * @return
	 * @throws Exception 
	 * @throws NoSuchAlgorithmException
	 */
	public static String md5(String clave) throws Exception {
		
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(clave.getBytes("UTF-8"), 0, clave.length());
				byte[] bt = md.digest();
				BigInteger bi = new BigInteger(1, bt);
				String md5 = bi.toString(16);
				return md5.toUpperCase();
			} catch (Exception e) {
				throw new Exception("Error al codificar Clave.");
			}
	}

}
