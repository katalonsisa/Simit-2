#language: es 
#autor:kevin.muentes@Quipux.com
@WSATHPagoLiquidacion
Característica: Como funcionario de ATH quiero notificar el pago de una liquidacion realizada en el banco al sitema de simit  	

			Antecedentes:
			Dado que se realizo la solicitud de liquidar un comparendo por medio de la plataforma de simit
			
			@Exitoso @PagoLiquidacion @V3.0 @SOL:24356 @REQ:47951
			Escenario: Se realiza le pago exitoso de la liquidacion 
			Dado que realizo el consumo del pago de liquidacion expuesto para ATH
			Entonces El sistema me entrega un respuesta Exitosa del consumo del servicio para ATH
			  
			
			