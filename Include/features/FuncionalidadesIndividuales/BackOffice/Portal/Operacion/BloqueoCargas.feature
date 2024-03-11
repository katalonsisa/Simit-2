#language: es
#Author: kevin.muentes@quipux.com
@BloqueoCargas
Característica: Como funcionario quiero realizar el bloqueo de archivos para no permitir 
								que se suban ciertos tipos de archivos por parte de las secretarias    
								
	Antecedentes: 
    Dado que ingreso al portal de backOffice
    Y selecciono la opcion Bloqueo de cargas del modulo Operación
    
    #@BloqueoComparendo @SinSecretaria
    #Esquema del escenario: Quiero hacer le bloqueo de un tipo de archivo de comparendo para todas las secretarias
    #Dado  que selecciono el tipo de carga Resolucion
    #Cuando realizo la busqueda del tipo de carga
    #Entonces busco y desactivo el tipo de carga <tipoarchivo> 
     #
   #	Ejemplos:
   #	|tipoarchivo|
   #	|Anulado|
   #	
   	
   	@BloqueoComparendo @Secretaria @sol:24938
    Esquema del escenario: Quiero hacer le bloqueo de un tipo de archivo de comparendo para todas las secretarias
    Dado  que selecciono el tipo de carga Resolucion
    Y que selecciono la siguiente secretaria <secretaria>
    Cuando realizo la busqueda del tipo de carga
    Entonces busco y desactivo el tipo de carga <tipoarchivo> 
     
   	Ejemplos:
   	|tipoarchivo|secretaria|
   	|Anulado| 387|
   	
   	