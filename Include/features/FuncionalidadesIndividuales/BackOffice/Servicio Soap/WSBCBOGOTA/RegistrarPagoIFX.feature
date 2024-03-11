#language: es
#Author: kevin.muentes@quipux.com
@WSBogotaRegistrarPagoIFX
Característica: Como funcionario del banco de bogota quiero registrar los pagos de las facturas,
							por el servicio soap expuesto por el sistema de SIMIT	
								
	
  Antecedentes: 
    Dado que se realizo la solicitud de liquidar un comparendo por medio de la plataforma de simit

   @Exitoso @V3.0 @SOL:24356 @REQ:47952
   Escenario: realizo una consulta de una factura 
   Dado que realizo la consulta por el servicio soap espuesto para el banco de bogota 
   Entonces el sistema me entrega los datos asociados a la factura 
   
   