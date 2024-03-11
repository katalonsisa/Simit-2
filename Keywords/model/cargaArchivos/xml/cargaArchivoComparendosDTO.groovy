package model.cargaArchivos.xml

import model.cargaArchivos.csv.ComparendoDTO
import model.cargaArchivos.xml.Comparendo

public class cargaArchivoComparendosDTO {

	Comparendo comparendo

	def cargaArchivoComparendosDTO(ComparendoDTO ComparendoCSV,String usuario,String password) {
		this.comparendo = new Comparendo(ComparendoCSV, usuario, password)
		println comparendo
	}
}
