#language: es 
#autor: kevin.muentes@quipux.com
@getbillATH
Característica: Como funcionario del banco ATH quiero tener la factura de la quidacion  


			Antecedentes:
			#Dado que se realizo la solicitud de liquidar un comparendo por medio de la plataforma de simit
			
			
			@Exitoso  @V3.0 @SOL:24356 @REQ:47951
			Escenario: quiero obtener la fectura de la liquidacion
			Dado que relaizo la consulta al servicio soap de ATH
			Entonces el obtengo la respuesta de la factura desde ATH
			
			 