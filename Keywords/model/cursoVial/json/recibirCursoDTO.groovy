package model.cursoVial.json

public class recibirCursoDTO {

	String idCia
	String idCiudadCia
	String codigoSedeCia
	String idFuncionarioPago
	String idFuncionarioRegistra
	int idTipoDocumentoInfractor
	String identificacionInfractor
	String numeroComparendo
	String fechaComparendo
	String idSecretaria
	int referenciaDescuento
	int valorCia
	int valorFCM
	String codigoCurso
	String fechaPago
	String fechaCurso
	String numeroCertificado

	recibirCursoDTO(){
	}

	recibirCursoDTO(recibirCursoDTO temp){
		this.idCia = temp.idCia
		this.idCiudadCia = temp.idCiudadCia
		this.codigoSedeCia = temp.codigoSedeCia
		this.idFuncionarioPago = temp.idFuncionarioPago
		this.idFuncionarioRegistra = temp.idFuncionarioRegistra
		this.idTipoDocumentoInfractor = temp.idTipoDocumentoInfractor
		this.identificacionInfractor = temp.identificacionInfractor
		this.numeroComparendo = temp.numeroComparendo
		this.fechaComparendo = temp.fechaComparendo
		this.idSecretaria = temp.idSecretaria
		this.referenciaDescuento = temp.referenciaDescuento
		this.valorCia = temp.valorCia
		this.valorFCM = temp.valorFCM
		this.codigoCurso = temp.codigoCurso
		this.fechaPago = temp.fechaPago
		this.fechaCurso = temp.fechaCurso
		this.numeroCertificado = temp.numeroCertificado
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{\"idCia\": \"${this.idCia}\",\"idCiudadCia\":\"${this.idCiudadCia}\",\"codigoSedeCia\":\"${this.codigoSedeCia}\",\"idFuncionarioPago\":\"${this.idFuncionarioPago}\",\"idFuncionarioRegistra\":\"${this.idFuncionarioRegistra}\"" +
				",\"idTipoDocumentoInfractor\": ${this.idTipoDocumentoInfractor},\"identificacionInfractor\":\"${this.identificacionInfractor}\",\"numeroComparendo\":\"${this.numeroComparendo}\",\"fechaComparendo\":\"${this.fechaComparendo}\",\"idSecretaria\":\"${this.idSecretaria}\""+
				",\"referenciaDescuento\": ${this.referenciaDescuento},\"valorCia\": ${this.valorCia},\"valorFCM\": ${this.valorFCM},\"codigoCurso\":\"${this.codigoCurso}\",\"fechaPago\":\"${this.fechaPago}\",\"fechaCurso\":\"${this.fechaCurso}\",\"numeroCertificado\":\"${this.numeroCertificado}\" }"
	}
}
