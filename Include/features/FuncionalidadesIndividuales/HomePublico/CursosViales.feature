#language: es
@CursosViales
Característica: Como ciudadano quiero consultar y generar los certificados de mis cursos viales 

Antecedentes: 
		Cuando Abro el navegador y me dirigo al home pulbico de simit
		
		
		@FlujoExitoso 
		Escenario: Consultar 
		Dado que realizo una consulta de estado de cuenta con curso 
		Cuando el sistema recupera la informacion asociada
		Entonces consulto mi historial de cursos
		Y mando al correo del comprovante del curso cursado
		Y entro al correo
		
		
		
		
		@FlujoExitoso 
		Escenario: descargar 
		Dado que realizo una consulta de estado de cuenta con curso 
		Cuando el sistema recupera la informacion asociada
		Entonces consulto mi historial de cursos
		Y descargo el comprovante de del curso

		
		
#		@FlujoExitoso 
#		Escenario: Consultar 
#		Dado que realizo una consulta de estado de cuenta con curso 
#		Cuando el sistema recupera la informacion asociada
#		Entonces Seleciono el en la tabla de comparendos el curso que esto cursado
#		Y mando el comprovante del curso que estoy cursando 