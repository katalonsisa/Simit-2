/* groovylint-disable PropertyName */
package model.cargaArchivos.csv

class ResolucionDTO {

	static String RESCONSECUTIVO
	static String RESNUMERO
	static String RESNUMANT
	static String RESFECHA
	static String RESTIPORES
	static String RESFHASTA
	static String RESCOMP
	static String RESCOMPF
	static String RESNIPINFRAC
	static String RESTIPODOC
	static String RESNOMBRE
	static String RESAPELLIDO
	static String RESDIRINFRACTOR
	static String RESTELEINFRACTOR
	static String RESIDCIUDAD
	static String RESVALOR
	static String RESVALAD
	static String FOTOMULTA
	static String RESFOTODETECCION
	static String RESORGANISMO
	static String RESCOMPOLCA
	static String RESINFRACCION
	static String RESVALINF
	static String RESVALPAG
	static String ID_TIPO_DOC_TUTOR
	static String NRO_DOC_TUTOR
	static String NOMBRE_TUTOR
	static String APELLIDO_TUTOR
	static String RESFECHAMANDTO
	static String RESFECHA_PAGO_CUOTA_AP

	ResolucionDTO() {
		this.RESCONSECUTIVO = ''
		this.RESNUMERO = ''
		this.RESNUMANT = ''
		this.RESFECHA = ''
		this.RESTIPORES = ''
		this.RESFHASTA = ''
		this.RESCOMP = ''
		this.RESCOMPF = ''
		this.RESNIPINFRAC = ''
		this.RESTIPODOC = ''
		this.RESNOMBRE = ''
		this.RESAPELLIDO = ''
		this.RESDIRINFRACTOR = ''
		this.RESTELEINFRACTOR = ''
		this.RESIDCIUDAD = ''
		this.RESVALOR = ''
		this.RESVALAD = ''
		this.FOTOMULTA = ''
		this.RESORGANISMO = ''
		this.RESCOMPOLCA = ''
		this.RESINFRACCION = ''
		this.RESVALINF = ''
		this.RESVALPAG = ''
		this.ID_TIPO_DOC_TUTOR = ''
		this.NRO_DOC_TUTOR = ''
		this.NOMBRE_TUTOR = ''
		this.APELLIDO_TUTOR = ''
		this.RESFECHAMANDTO = ''
		this.RESFECHA_PAGO_CUOTA_AP = ''
	}

	@Override
	String toString() {
		return "${this.RESCONSECUTIVO},${this.RESNUMERO},${this.RESNUMANT},${this.RESFECHA},${this.RESTIPORES}," +
				"${this.RESFHASTA},${this.RESCOMP},${this.RESCOMPF},${this.RESNIPINFRAC},${this.RESTIPODOC},"+
				"${this.RESNOMBE},${this.RESAPELLIDO},${this.RESDIRINFRACTOR},${this.RESTELEINFRACTOR},"+
				"${this.RESIDCIUDAD},${this.RESVALOR},${this.RESVALAD},${this.FOTOMULTA},${this.RESORGANISMO},"+
				"${this.RESCOMPOLCA},${this.RESINFRACCION},${this.RESVALINF},${this.RESVALPAG},"+
				//"${this.ID_TIPO_DOC_TUTOR},${this.NRO_DOC_TUTOR},${this.NOMBRE_TUTOR},${this.APELLIDO_TUTOR},"+
				"${this.RESFECHAMANDTO}"
		//,${this.RESFECHA_PAGO_CUOTA_AP}"
	}
}
