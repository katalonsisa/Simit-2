#language: es
@CargaArchivoComparendos
Característica: Como transito  quiero registrar comparendos por los servicios SOAP
	
	Antecedentes:

  Esquema del escenario: Carga comparendo
    Dado que registro un comparendo individual por el servicio soap con mi <usuario> y <clave>
    Cuando el sitema procesa el comparendo registrado por soap
   	

    Ejemplos: 
      | usuario | clave |
      | kevin   | admin |
