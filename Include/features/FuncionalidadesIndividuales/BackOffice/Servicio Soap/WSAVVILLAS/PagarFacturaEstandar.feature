#language: es
#Author: kevin.muentes@quipux.com
@PagarFacturaEstandar
Característica: Como funcionario de AVVillas Deseo realizar los pagos de las facturas estanadar de entrantes
  						al banco con relaciona las liquidaciones realizadas en el portal de SIMIT

  Antecedentes: 
    Dado que se realizo la solicitud de liquidar un comparendo por medio de la plataforma de simit

    @Exitoso @V3.0 @SOL:24356 @REQ:47953
    Escenario: Pago exitoso de la factura  
    	Dado que realizo el consumo del servicio para pagar la Factura 
    	Entonces el sistema me reporta un pago exitoso de la factura 