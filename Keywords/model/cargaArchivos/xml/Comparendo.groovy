package model.cargaArchivos.xml

import model.cargaArchivos.csv.ComparendoDTO

public class Comparendo {
	String clave
	String iNaccidente
	String iNapellidoInfractor
	String iNciudadInfractor
	String iNcodigoInfraccion
	String iNcodigoMo
	String iNcodigoPa
	String iNcodigoRa
	String iNcomparendoElectronico
	String iNconsecutivoComparendo
	String iNconsecutivoIn
	String iNdirPatioInmovi
	String iNdirecTesti
	String iNdireccion
	String iNdireccionInfractor
	String iNdivipoLicencia
	String iNdivipoMatricula
	String iNdivipoMunicipio
	String iNedadIn
	String iNemail
	String iNestado
	String iNestadoComparendoPolca
	String iNfecha
	String iNfechaNotificacion
	String iNfechaVence
	String iNfuga
	String iNgradoAlcohol
	String iNgruaNumero
	String iNhora
	String iNidInfractor
	String iNidSecretariaExpide
	String iNidTipoDocP
	String iNidentificacion
	String iNidentificacionTestigo
	String iNinmovilizacion
	String iNlicenciaConduccion
	String iNlicenciaTransito
	String iNlocalidadComuna
	String iNnitEmpresa
	String iNnombreEmpresa
	String iNnombreInfractor
	String iNnombreProp
	String iNnombreTestigo
	String iNnumeroComparendo
	String iNobservaciones
	String iNorganismoTransito
	String iNpatioInmoviliza
	String iNplaca
	String iNplacaAgente
	String iNplacaGrua
	String iNtarjetaOperacion
	String iNteleTestigo
	String iNtelefonoInfractor
	String iNtipoC
	String iNtipoD
	String iNtipoInfractor
	String iNtipoS
	String iNtipoV
	String iNvalorAdi
	String iNvalorComp
	String iNvalorInf1
	String secretaria
	String usuario

	def  Comparendo(ComparendoDTO ComparendoCSV,String usuario,String password) {
		this.clave = password
		this.iNaccidente = ComparendoCSV.COMACCI
		this.iNapellidoInfractor = ComparendoCSV.COMAPELLIDO
		this.iNciudadInfractor = ComparendoCSV.COMIDCIUDAD
		this.iNcodigoInfraccion = ComparendoCSV.COMINFRACCION
		this.iNcodigoMo = ComparendoCSV.COMCODIGOMODALIDAD
		this.iNcodigoPa = ComparendoCSV.COMCODIGOPASAJEROS
		this.iNcodigoRa = ComparendoCSV.COMCODIGORADIO
		this.iNcomparendoElectronico = ComparendoCSV.COMFOTODETECCION
		this.iNconsecutivoComparendo = ComparendoCSV.COMCONSECUTIVO
		this.iNconsecutivoIn = ComparendoCSV.COMCONSECUTIINMOVI
		this.iNdirPatioInmovi = ComparendoCSV.COMDIRPATIOINMOVI
		this.iNdirecTesti = ComparendoCSV.COMDIRECRESTESTI
		this.iNdireccion = ComparendoCSV.COMDIR
		this.iNdireccionInfractor = ComparendoCSV.COMDIRINFRACTOR
		this.iNdivipoLicencia = ComparendoCSV.COMDIVIPOLICEN
		this.iNdivipoMatricula = ComparendoCSV.COMDIVIPOMATRI
		this.iNdivipoMunicipio = ComparendoCSV.COMDIVIPOMUNI
		this.iNedadIn = ComparendoCSV.COMEDADINFRACTOR
		this.iNemail = ComparendoCSV.COMEMAIL
		this.iNestado = ComparendoCSV.COMESTADOCOM
		this.iNestadoComparendoPolca = ComparendoCSV.COMPOLCA
		this.iNfecha = ComparendoCSV.COMFECHA
		this.iNfechaNotificacion = ComparendoCSV.COMFECHANOTIFICACION
		this.iNfechaVence = ComparendoCSV.COMFECHAVENCE
		this.iNfuga = ComparendoCSV.COMFUGA
		this.iNgradoAlcohol = ComparendoCSV.COMGRADOALCOHOL
		this.iNgruaNumero = ComparendoCSV.COMGRUANUMERO
		this.iNhora = ComparendoCSV.COMHORA
		this.iNidInfractor = ComparendoCSV.COMINFRACTOR
		this.iNidSecretariaExpide = ComparendoCSV.COMSECREEXPIDE
		this.iNidTipoDocP = ComparendoCSV.COMTIPODOC
		this.iNidentificacion = ComparendoCSV.COMIDENTIFICACION
		this.iNidentificacionTestigo = ComparendoCSV.COMIDENTIFICACIONTEST
		this.iNinmovilizacion = ComparendoCSV.COMINMOV
		this.iNlicenciaConduccion = ComparendoCSV.COMLICENCIA
		this.iNlicenciaTransito = ComparendoCSV.COMPLICTRANSITO
		this.iNlocalidadComuna = ComparendoCSV.COMLOCALIDADCOMUNA
		this.iNnitEmpresa = ComparendoCSV.COMNITEMPRESA
		this.iNnombreEmpresa = ComparendoCSV.COMNOMBREEMPRESA
		this.iNnombreInfractor = ComparendoCSV.COMNOMBRE
		this.iNnombreProp = ComparendoCSV.COMNOMBREPROP
		this.iNnombreTestigo = ComparendoCSV.COMNOMBREPROP
		this.iNnumeroComparendo = ComparendoCSV.COMNUMERO
		this.iNobservaciones = ComparendoCSV.COMOBSERVA
		this.iNorganismoTransito = ComparendoCSV.COMORGANISMO
		this.iNpatioInmoviliza = ComparendoCSV.COMPATIOINMOVILIZA
		this.iNplaca = ComparendoCSV.COMPLACA
		this.iNplacaAgente = ComparendoCSV.COMPPLACAAGENTE
		this.iNplacaGrua = ComparendoCSV.COMPLACAGRUA
		this.iNtarjetaOperacion = ComparendoCSV.COMTARJETAOPERACION
		this.iNteleTestigo = ComparendoCSV.COMTELETESTIGO
		this.iNtelefonoInfractor = ComparendoCSV.COMTELEINFRACTOR
		this.iNtipoC = ComparendoCSV.COMTIPODOC
		this.iNtipoD = ComparendoCSV.COMTIPODOC
		this.iNtipoInfractor = ComparendoCSV.COMTIPOINFRAC
		this.iNtipoS = ComparendoCSV.COMTIPOSER
		this.iNtipoV = ComparendoCSV.COMTIPOVEHI
		this.iNvalorAdi = ComparendoCSV.COMVALAD
		this.iNvalorComp = ComparendoCSV.COMVALOR
		this.iNvalorInf1 = ComparendoCSV.COMVALINFRA
		this.secretaria = ComparendoCSV.COMORGANISMO
		this.usuario = usuario
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.qxmultas.quipux.com.co\" xmlns:xsd=\"http://webservices.qxmultas.quipux.com.co/xsd\">"+
				"\n<soapenv:Header/>\n<soapenv:Body>\n<web:cargaArchivoComparendos>\n<web:comparendo>"+
				"\n<xsd:clave>${this.clave}</xsd:clave>\n<xsd:iNaccidente>${this.iNaccidente}</xsd:iNaccidente>\n<xsd:iNapellidoInfractor>${this.iNapellidoInfractor}</xsd:iNapellidoInfractor>"+
				"\n<xsd:iNciudadInfractor>${this.iNciudadInfractor}</xsd:iNciudadInfractor>\n<xsd:iNcodigoInfraccion1>${this.iNcodigoInfraccion}</xsd:iNcodigoInfraccion1>"+
				"\n<xsd:iNcodigoMo>${this.iNcodigoMo}</xsd:iNcodigoMo>\n<xsd:iNcodigoPa>${this.iNcodigoPa}</xsd:iNcodigoPa>\n<xsd:iNcodigoRa>${this.iNcodigoRa}</xsd:iNcodigoRa>"+
				"\n<xsd:iNcomparendoElectronico>${this.iNcomparendoElectronico}</xsd:iNcomparendoElectronico>\n<xsd:iNconsecutivoComparendo>${this.iNconsecutivoComparendo}</xsd:iNconsecutivoComparendo>"+
				"\n<xsd:iNconsecutivoIn>${this.iNconsecutivoIn}</xsd:iNconsecutivoIn>\n<xsd:iNdirPatioInmovi>${this.iNdirPatioInmovi}</xsd:iNdirPatioInmovi>\n<xsd:iNdirecTesti>${this.iNdirecTesti}</xsd:iNdirecTesti>"+
				"\n<xsd:iNdireccion>${this.iNdireccion}</xsd:iNdireccion>\n<xsd:iNdireccionInfractor>${this.iNdireccionInfractor}</xsd:iNdireccionInfractor>\n<xsd:iNdivipoLicencia>${this.iNdivipoLicencia}</xsd:iNdivipoLicencia>"+
				"\n<xsd:iNdivipoMatricula>${this.iNdivipoMatricula}</xsd:iNdivipoMatricula>\n<xsd:iNdivipoMunicipio>${this.iNdivipoMunicipio}</xsd:iNdivipoMunicipio>"+
				"\n<xsd:iNedadIn>${this.iNedadIn}</xsd:iNedadIn>\n<xsd:iNemail>${this.iNemail}</xsd:iNemail>\n<xsd:iNestado>${this.iNestado}</xsd:iNestado>"+
				"\n<xsd:iNestadoComparendoPolca>${this.iNestadoComparendoPolca}</xsd:iNestadoComparendoPolca>\n<xsd:iNfecha>${this.iNfecha}</xsd:iNfecha>"+
				"\n<xsd:iNfechaNotificacion>${this.iNfechaNotificacion}</xsd:iNfechaNotificacion>\n<xsd:iNfechaVence>${this.iNfechaVence}</xsd:iNfechaVence>\n<xsd:iNfuga>${this.iNfuga}</xsd:iNfuga>\n<xsd:iNgradoAlcohol>${this.iNgradoAlcohol}</xsd:iNgradoAlcohol>"+
				"\n<xsd:iNgruaNumero>${this.iNgruaNumero}</xsd:iNgruaNumero>\n<xsd:iNhora>${this.iNhora}</xsd:iNhora>\n<xsd:iNidInfractor>${this.iNidInfractor}</xsd:iNidInfractor>"+
				"\n<xsd:iNidSecretariaExpide>${this.iNidSecretariaExpide}</xsd:iNidSecretariaExpide>\n<xsd:iNidTipoDocP>${this.iNidTipoDocP}</xsd:iNidTipoDocP>\n<xsd:iNidentificacion>${this.iNidentificacion}</xsd:iNidentificacion>"+
				"\n<xsd:iNidentificacionTestigo>${this.iNidentificacionTestigo}</xsd:iNidentificacionTestigo>\n<xsd:iNinmovilizacion>${this.iNinmovilizacion}</xsd:iNinmovilizacion>\n<xsd:iNlicenciaConduccion>${this.iNlicenciaConduccion}</xsd:iNlicenciaConduccion>"+
				"\n<xsd:iNlicenciaTransito>${this.iNlicenciaTransito}</xsd:iNlicenciaTransito>\n<xsd:iNlocalidadComuna>${this.iNlocalidadComuna}</xsd:iNlocalidadComuna>\n<xsd:iNnitEmpresa>${this.iNnitEmpresa}</xsd:iNnitEmpresa>\n<xsd:iNnombreEmpresa>${this.iNnombreEmpresa}</xsd:iNnombreEmpresa>"+
				"\n<xsd:iNnombreInfractor>${this.iNnombreInfractor}</xsd:iNnombreInfractor>\n<xsd:iNnombreProp>${this.iNnombreProp}</xsd:iNnombreProp>\n<xsd:iNnombreTestigo>${this.iNnombreTestigo}</xsd:iNnombreTestigo>\n<xsd:iNnumeroComparendo>${this.iNnumeroComparendo}</xsd:iNnumeroComparendo>"+
				"\n<xsd:iNobservaciones>${this.iNobservaciones}</xsd:iNobservaciones>\n<xsd:iNorganismoTransito>${this.iNorganismoTransito}</xsd:iNorganismoTransito>\n<xsd:iNpatioInmoviliza>${this.iNpatioInmoviliza}</xsd:iNpatioInmoviliza>\n<xsd:iNplaca>${this.iNplaca}</xsd:iNplaca><xsd:iNplacaAgente>${this.iNplacaAgente}</xsd:iNplacaAgente>"+
				"\n<xsd:iNplacaGrua>${this.iNplacaGrua}</xsd:iNplacaGrua>\n<xsd:iNtarjetaOperacion>${this.iNtarjetaOperacion}</xsd:iNtarjetaOperacion>\n<xsd:iNteleTestigo>${this.iNteleTestigo}</xsd:iNteleTestigo>\n<xsd:iNtelefonoInfractor>${this.iNtelefonoInfractor}</xsd:iNtelefonoInfractor>\n<xsd:iNtipoC>${this.iNtipoC}</xsd:iNtipoC><xsd:iNtipoD>${this.iNtipoD}</xsd:iNtipoD>"+
				"\n<xsd:iNtipoInfractor>${this.iNtipoInfractor}</xsd:iNtipoInfractor>\n<xsd:iNtipoS>${this.iNtipoS}</xsd:iNtipoS>\n<xsd:iNtipoV>${this.iNtipoV}</xsd:iNtipoV>\n<xsd:iNvalorAdi>${this.iNvalorAdi}</xsd:iNvalorAdi>\n<xsd:iNvalorComp>${this.iNvalorComp}</xsd:iNvalorComp>\n<xsd:iNvalorInf1>${this.iNvalorInf1}</xsd:iNvalorInf1>\n<xsd:secretaria>${this.secretaria}</xsd:secretaria>\n<xsd:usuario>${this.usuario}</xsd:usuario>"+
				"\n</web:comparendo>\n</web:cargaArchivoComparendos>\n</soapenv:Body>\n</soapenv:Envelope>"

	}
}
