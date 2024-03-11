package util

import com.kms.katalon.core.annotation.Keyword

import util.utilsQX

public class DirrecionesAleatorias {

	private static final List<String> VIA = new ArrayList<String>() {
		{
			add(['Autopista','AUT']);add(['Avenida','AV']);add(['Avenida Carrera','AK']);add(['Calle','Cll']);add(['Carrera','CR'])
			;add(['Carretera','CARR']);add(['Circular','CIR']);add(['Circunvalar','CRV']);add(['Diagonal','DG']);add(['Kilometro','KM']);add(['Transversal','TV'])
		}
	}
	private static final int VIA_SIZE =  VIA.size()
	private static final List<String> PUNTOS_CARDINALES = ['SUR', 'NORTE', 'ESTE', 'OESTE', 'OCCIDENTE', 'ORIENTE']
	private static final int PUNTOS_CARDINALES_SIZE = PUNTOS_CARDINALES.size()
	private static final List<String> EDIFICACIONES  = new ArrayList<String>() {
		{
			add(['Almacen','ALM']);add(['Apartamento','AP']);add(['Bodega','BG']);add(['Departamento','DPTO']);add(['Deposito','DP']);add(['Finca','FCA']);add(['Local','LC']);add(['Unidad','UN']);add(['Unidad Residencial','UR']);
		}
	}
	private static final int EDIFICACIONES_SIZE =  EDIFICACIONES.size()


	/**
	 * 
	 * Retorna una via  aleatoria
	 * @param nomenclatura 0 para nombre completo y 1 para la nomenclatura
	 * @return
	 */
	@Keyword
	public static String getRandomVia(int nomenclatura) {
		if(nomenclatura < 0) {
			nomenclatura = 1
		}
		int randomVia = (int) (Math.random() * VIA_SIZE)
		def via = VIA.get(randomVia).get(nomenclatura) + ' ' + utilsQX.getNumero((int) (Math.random() * 3)+ 1)
		if (Math.random() < 0.6) {
			via += ' ' + utilsQX.getLetra((int) (Math.random() * 2)+ 1)
			if (Math.random() > 0.8) {
				via += ' ' + getRandomPuntoCardinal()
			}
		}
		return via
	}


	/**
	 * Retorna un punto cardinal 
	 * @return
	 */
	@Keyword
	public static String getRandomPuntoCardinal() {
		int randomPuntoCardinal = (int) (Math.random() * PUNTOS_CARDINALES_SIZE)
		return PUNTOS_CARDINALES.get(randomPuntoCardinal)
	}

	/**
	 * Retorna un tipo de edificacion 
	 * @param nomenclatura 0 para nombre completo y 1 para su nomenclatura 
	 * @return
	 */
	@Keyword
	public static String getRandomEdificacion(int nomenclatura) {
		if(nomenclatura < 0) {
			nomenclatura = 1
		}
		int randomEdificacion = (int) (Math.random() * EDIFICACIONES_SIZE)
		return EDIFICACIONES.get(randomEdificacion).get(nomenclatura)
	}


	/**
	 * Retorna una direcion aleatoria 
	 * @param isEdificacion pregunta si es una lugar de resicendia 
	 * @return
	 */
	@Keyword
	public static String getRandomDireccion(boolean isEdificacion) {
		int nomenclatura = 0
		if(Math.random() > 0.6) {
			nomenclatura = 1
		}
		String direccion = getRandomVia(nomenclatura)

		if (isEdificacion) {
			direccion += ' # ' + utilsQX.getNumero((int) (Math.random() * 3) + 1) + ' - ' + utilsQX.getNumero((int) (Math.random() * 2) + 1)
			if (Math.random() > 0.7) {
				direccion += ' ' + getRandomEdificacion(nomenclatura)
			}
		}else {
			direccion +=  ' ' +  getRandomVia(nomenclatura)
		}

		return direccion
	}
}


