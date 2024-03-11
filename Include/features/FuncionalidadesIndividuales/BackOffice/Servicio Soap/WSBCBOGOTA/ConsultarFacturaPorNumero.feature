#language: es
#Author: kevin.muentes@quipux.com
@WSBogotaConsultaFacturaPorNumero
Característica: Como funcionario del banco de bogota querio realizar la consulta de la factura 
								a pagar por la entidad mediante un numero dado 
								
	
  Antecedentes: 
    Dado que se realizo la solicitud de liquidar un comparendo por medio de la plataforma de simit

   @Exitoso @V3.0 @SOL:24356 @REQ:47952
   Escenario: realizo una consulta de una factura 
   Dado que realizo la consulta por el servicio soap espuesto para el banco de bogota 
   Entonces el sistema me entrega los datos asociados a la factura 
   
   