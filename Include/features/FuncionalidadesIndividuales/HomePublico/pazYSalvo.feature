#language: es
@PazYSalvo
Característica: Como ciudadano quiero verificar que no tenga muiltas pendientes ante las secretarias de transito


Antecedentes: 
		Cuando Abro el navegador y me dirigo al home pulbico de simit
		Y salto el tutorial
		
		
		@FlujoExitoso 
		Esquema del escenario: Consultar y generar comprobante de paz y salvo
		Dado que realizo una consulta de estado de cuenta con el siguiente dato <dato>
		Entonces me muestra la informacion de que no tengo multas asociadas
		Y Descargo el comprobante de paz y salvo 
		
		Ejemplos: 
		|dato|
		|3764269436|
		
		
		@FlujoExitoso 
		Esquema del escenario: Consultar y enviar al correo el comprobante de paz y salvo
		Dado que realizo una consulta de estado de cuenta con el siguiente dato <dato>
		Entonces me muestra la informacion de que no tengo multas asociadas
		Y Envio al correo el comprobante de paz y salvo
		
		Ejemplos: 
		|dato|
		|3764269436|
		
		
		
		@FlujoExitoso 
		Escenario: Consular desde la opcion solicitar paz y salvo 
		Dado que seleciono la opcion de solicitar paz y salvo
		Y realizo la solicitud con un documento sin obligaciones pendientes
		Cuando el sistema al realizar la consulta de paz y salvo 
		Entonces Descargo el comprobante de paz y salvo 
		
		
		
		@FlujoExitoso 
		Esquema del escenario: Consultar una placa y que no muestre la opcion de generrar paz y salvo 
		Dado que realizo una consulta de estado de cuenta con el siguiente dato <dato>
		Entonces me muestra la informacion de que no tengo multas asociadas 
		Y reviso que no me muestre las opciones de generar paz y salvo 
		
		Ejemplos: 
		|dato|
		|AMT123|