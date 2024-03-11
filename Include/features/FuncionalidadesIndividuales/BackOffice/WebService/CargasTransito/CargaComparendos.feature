#language: es
#Author: kevin.muentes@quipux.com
@CargaArchivos
Característica: Como funcionario quiero hacer cargas de los diferentes archivos al sistema
#
  @CargaComparendos @PruebaCargasMaximas
  Esquema del escenario: probar la capacidad del sistema ante la carga maxiva de comparendos simples
    Dado subo <cantidad> de comparendos al sistema con el usuario <usuario> y clave <clave>
    #Cuando el sistema procesa el archivo
    #Entonces resivo una respuesta de la carga

    Ejemplos: 
      | cantidad | usuario | clave |
      |     1000 	 | 1036942194   | admin |

  #@CargaComparendos @CargaComparendoSimple
  #Esquema del escenario: cargar un comparendo simple
    #Dado que cargo un comparendo con mi usuario <usuario> y clave <clave>
    #Cuando el sistema procesa el archivo
    #Entonces resivo una respuesta de la carga
#
    #Ejemplos: 
      #| usuario | clave |
      #| monitoreosimit | F1d2r3c45* |

  #@CargaComparendos @ComparendoTutor @CargaComparendoSimple
  #Esquema del escenario: Cargar un comparendo con tutor
    #Dado que cargo un comparendo con tutor con mi usuario <usuario> y clave <clave>
    #Cuando el sistema procesa el archivo
    #Entonces resivo una respuesta de la carga
#
    #Ejemplos: 
      #| usuario | clave |
      #| admin   | admin |
      #
      
      
      #@CargaComparendos @ComparendoInmovilizacion	@CargaComparendoSimple
      #Escenario: Como funcionario voy a cargar un comparendo con una inmovilizacion del vehiculo
      #Dado que cargo un comparendo con inmovilizacion de vehiculo con usuario (.*) y clave (.*)
      #Cuando el sistema procesa el archivo
    #	#Entonces resivo una respuesta de la carga
#
#		#@CargaComparendos @ComparendoIncidente	@CargaComparendoSimple
      #Escenario: Como funcionario voy a cargar un comparendo con una inmovilizacion del vehiculo
      #Dado que cargo un comparendo con incidente de vehiculo con usuario (.*) y clave (.*)
      #Cuando el sistema procesa el archivo
    #	#Entonces resivo una respuesta de la carga
#		
#
#
      #
     #@CargaComparendo @CampoFaltantes @CargaFallida
     #Esquema del escenario: Como funcionario quiero validar que cuando se envia un archivo 
     #con campos faltantes el sistema rechace la carga 
     #	Dado que cargo un comparendo sin la siguientes informacion <campos> con mi usuario y clave
     #	Cuando el sistema procese el archivo fallido
     #	Entonces presenta el mensaje de fallo de carga
     #
     #
     #Ejemplos:
     #|campos|
     #||