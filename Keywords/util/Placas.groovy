package util

public class Placas {


	/**
	 * genera una placa de forma aleatoria 
	 * @return
	 */
	public static String generarPlaca() {

		String placa = ""

		for(int i = 0 ; i < 3 ; i++ ){
			int ramdon = (Math.random() * 3)
			switch(ramdon){
				case 0:
				case 1:
					placa += generarVocal();
					break
				case 2:
				case 3:
					placa += generarConsonante();
					break
				default:
					break
			}
		}
		placa += generarNumero();
		placa += generarNumero();

		int ramdon = (Math.random() * 4)
		switch(ramdon){
			case 1:
				placa += generarVocal();
				break
			case 2:
				placa += generarConsonante();
				break
			case 3:
				placa += generarNumero();
				break
			default:
				break
		}
		return placa
	}

	/**
	 * @return Una consonante de forma aleatoria
	 */
	public static char generarConsonante() {
		return generarRandomChar("BCDFGHJKLMNPQRSTVWXYZ");
	}

	/**
	 * @return Una vocal de forma aleatoria
	 */
	public static char generarVocal() {
		return generarRandomChar("AEIOU");
	}

	/**
	 * @return Un numero aleatorio entre 0-9
	 */
	public static char generarNumero() {
		return generarRandomChar("0123456789");
	}

	/**
	 * De una cadena retorna el valor de una de sus pocisiones de forma aleatoria
	 * @param str 
	 * @return valor de una posicion de la cadena 
	 */
	private static char generarRandomChar(String str) {
		def caracteres = str.toCharArray();
		int index = (int) (Math.random() * caracteres.length);
		return caracteres[index];
	}
}
