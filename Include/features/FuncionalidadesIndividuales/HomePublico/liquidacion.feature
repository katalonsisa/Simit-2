#language: es
@Liquidacion
Característica: Como usuario quero realizar una liquidacion de las diferentes obligaciones
  				que tengo pendientes ante las secretarias de transito

  Antecedentes: 
    Cuando Abro el navegador y me dirigo al home pulbico de simit
   	
	
		@Exitoso @CuponPago 
  	Escenario: Descargar cupon de pago
  	Dado que realizo una consulta de estado de cuenta
    Cuando el sistema recupera la informacion asociada
    Y seleciono las obligaciones que voy a pagar
    Entonces seleciono la opcion de imprimir documento para pago
    Y descargo el documento de cupon de pago


		@Extoso @EnvioCorreo
	 	Escenario: Enviar al correo
    Dado que realizo una consulta de estado de cuenta
    Cuando el sistema recupera la informacion asociada
    Y seleciono las obligaciones que voy a pagar    
    Entonces seleciono la opcion de imprimir documento para pago
    Y envio al correo el cupon de pago
    #Y entro al correo
    #Y reviso la llegada del cupon de pago
