#language: es
#Author: kevin.muentes@quipux.com
@CargaArchivosResoluciones @CargaResolucion
Característica: Como funcionario de transito quiero cargar diferentes tipos de resoluciones

  Antecedentes: valido que tengo almenos un comparendo previa mente
    Dado que tengo comparendos por pagar

  #
  @Exitoso @CargaResolucion @ResolucionTipo1
  Esquema del escenario: Como funcionario del transito quiero cargar resoluciones de tipo 1 sanciones
  Dado que realizo una carga de resoluciones de tipo 1 con mi usuario <usuario> y clave <clave>
  Cuando el sistema resive y procesa el archivo con exito
  Entonces resivo una respuesta exitosa de la carga de la resolucion
  
  	Ejemplos:
  | usuario | clave |
  | kevin   | admin |
  
  
  #@Exitoso @CargaResolucion @ResolucionTipo1 @PruebaCargasMaximas
  #Esquema del escenario: Como funcionario del transito quiero cargar resoluciones de tipo 1 sanciones
    #Dado subo <cantidad> de comparendos al sistema con el usuario <usuario> y clave <clave>
    #Y el sistema procesa el archivo
    #Y realizo la carga maxiva de las resoluciones con los comparendos previamente cargados
    #Cuando el sistema resive y procesa el archivo con exito
    #Entonces resivo una respuesta exitosa de la carga de la resolucion
#
    #Ejemplos: 
      #| cantidad | usuario | clave |
      #|      100 | kevin   | admin |

      
      
      
      
    @Exitoso @CargaResolucion @ResolucionTipo 
    Escenario: Como funcionario del trasito quiero cargar resoluciones del tipo 16 
    Dado que realizo la carga de una resolucion de tipo 16 con usuario kevin y clave admin
    Cuando El sistema resive y procesa el archivo con exito
    Entonces resivo una respuesta exitosa de la carga de la resolucion 