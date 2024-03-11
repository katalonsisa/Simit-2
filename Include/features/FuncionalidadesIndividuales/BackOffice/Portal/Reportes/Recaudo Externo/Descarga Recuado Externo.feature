#language: es
#Author: deisy.velasquez@quipux.com
@DescargaRecuadoExterno
Característica: Como funcionario quiero realizar descargas de los archivos de reporte de recuado externo.

Antecedentes:
	Dado que ingreso al portal de backOffice
	Y selecciono el modulo de Reportes y el submenu de Recaudo Externo y la opcion Descarga Recaudo Externo
	
	Escenario: Como funcinario quiero hacer la prueba de descarga de los reportes de recuado externo que fueron realizados este mes.
	Dado que se ingresa las fechas desde y hasta que se solicita el reporte de recuado externo.
	Cuando realizo la consulta
	Entonces se verifica la respuesta 