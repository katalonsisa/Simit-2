#language: es
#Author: kevin.muentes@quipux.com
@ConsultarFacturaEstandar
Característica: Como funcionario de AVVillas deseo realizar la consulta de las facturas 
								realizadas en el sistema de SIMIT

  Antecedentes: 
    Dado que se realizo la solicitud de liquidar un comparendo por medio de la plataforma de simit

    @Exitoso @Consulta @V3.0 @SOL:24356 @REQ:47953
    Escenario: consulta de factura estandar   
    	Dado que realizo el consumo para consultar la factura
    	Entonces el sistema me retorna los datos relacionados con la factura 