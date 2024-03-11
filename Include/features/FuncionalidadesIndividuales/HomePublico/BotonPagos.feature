#language: es
@BotonPagos
Característica: Como usuario quero realizar pagar mis obligaciones atra vez de la aplicacion de simit 

  Antecedentes: 
    Cuando Abro el navegador y me dirigo al home pulbico de simit
    #Y salto el tutorial

  	Escenario: pago con targeta de credito
    Dado que realizo una consulta de estado de cuenta
    Cuando el sistema recupera la informacion asociada
    Y seleciono las obligaciones que voy a pagar
    Y realizo la pasarela de pagos
    Y regreso al inicio


      
      
  Escenario: pago con targeta de credito rechazada  
    Dado que realizo una consulta de estado de cuenta
    Cuando el sistema recupera la informacion asociada
    Y seleciono las obligaciones que voy a pagar
    Y seleciono la opcion de pagar
    Y rechazo el pago por la pasarela 
     Y regreso al inicio

      
      