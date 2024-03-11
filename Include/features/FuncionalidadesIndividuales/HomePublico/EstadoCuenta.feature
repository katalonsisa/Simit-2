#language: es
@EstadoCuenta
Característica: Como ciudano quiero consultar el estado de cuenta ante las secretarias del trasito
		
		Antecedentes: 
		Cuando Abro el navegador y me dirigo al home pulbico de simit
		#Y salto el tutorial
		
		
		@FlujoExitoso @DescargaEstadoCuenta @EstadoCuenta @SOL:23831 @VER:1.0
		Escenario: Consultar estado de cuentas con obligaciones pendientes y descargo el estado de cuenta 
		Dado que realizo una consulta de estado de cuenta
		Cuando el sistema recupera la informacion asociada
		Entonces descargo el estado de cuenta de mi consulta asociada
	
		@FlujoExitoso @EnvioCorreo @EstadoCuenta @SOL:23831 @VER:1.0
		Escenario: Consultar estado de cuentas y envio el estado de cuenta al correo 
		Dado que realizo una consulta de estado de cuenta
		Cuando el sistema recupera la informacion asociada
		Entonces envio a mi correo el estado de cuenta
		#Y entro al correo
		#Y reviso la llegada del estado de cuenta 
	
	@flujoexitoso @DetalleMulta  @EstadoCuenta @SOL:23831 @VER:1.0
		Escenario: consultar estado de cuentas con multas y verifico el detalle de la obligacion 
		Dado que realizo una consulta de estado de cuenta
		Cuando el sistema recupera la informacion asociada
		Entonces seleciono un multa para ver el detalle de la misma 
		Y reviso la informacion de la misma 

		
		
		@flujoExitoso @ConsultaDocumentoConDiferentesPersonas @EstadoCuenta @SOL:23831 @VER:1.0
		Escenario: Consultar con un documento que tenga dos o mas personas asociadas 
		Dado que consulto con un documento que tiene mas de una persona asociada
		Y Seleciono una persona para realizar la consulta
		Cuando el sistema recupera la informacion asociada
		
		
		@flujoExitoso @ConsultaDocumentoConDiferentesPersonas @EstadoCuenta @SOL:23831 @VER:1.0
		Esquema del escenario: Consultar con un documento con espacios 
		Dado que realizo una consulta de estado de cuenta con el siguiente dato <dato>
		Cuando el sistema recupera la informacion asociada 
		
		Ejemplos:
		|dato|
		|1027 88 05 81 |
		
		
		

##----------------------- Flujos de fallo ----------------


	@flujoFallido @ConsultaMinimo  @EstadoCuenta @SOL:23831 @VER:1.0
	Escenario: Consultar con el minimo de caracteres requeridos para realizar la consulta
	Dado que realizo una consulta con el minimo de caracteres requeridos para realizar una consulta
	Entonces el sistema me presenata una alerta de fallo en al consultar
	
	
	@FlujoFallido @ConsultarMaximo  @EstadoCuenta @SOL:23831 @VER:1.0
	Escenario: Cosultar con el maximo de caracteres requeridos para realizar la consulta
	Dado que realiazo una consulta con el maximo de caracteres permitidos en una consulta
	Entonces el sistema me presenata una alerta de fallo en al consultar
	
	@FlujosFallidos @ConsultaCaracteresEspeciales  @EstadoCuenta @SOL:23831 @VER:1.0
	Escenario: Consutar con caracteres especiales
	Dado que realizo una consulta con caracteres especiales no permitidos por la aplicacion
	Entonces el sistema me presenata una alerta de fallo en al consultar
