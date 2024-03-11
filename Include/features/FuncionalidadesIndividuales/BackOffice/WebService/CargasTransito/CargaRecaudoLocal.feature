 #language: es
#Author: your.email@your.domain.com
@CargaArchivosRecaudoLocal @CargaRecaudoLocal
Característica: Como funcionario de transito quiero cargar el recaudo local

  #@Exitoso @CargaRecaudo
  #Esquema del escenario: Como funcionario del transito quiero realizar la carga de un recaudo local
    #Dado que realizo una carga de un recaudo local con mi <usuario> y <clave>
    #Cuando el sistema resive el y procesa el archivo con exito del recaudo
    #Entonces resivo una respuesta exitosa de la carga del recaudo

    #Ejemplos: 
      #| usuario | clave |
      #| kevin   | admin |

  Esquema del escenario: Como funcinario quiero hacer la prueba de cargar x cantidad de comparendos y realizarle el recaudo de estos comparendos
    Dado subo <cantidad> de comparendos al sistema con el usuario <usuario> y clave <clave>
    Cuando el sistema procesa el archivo
    Entonces resivo una respuesta de la carga
    Dado realizo la carga del recaudo local de los comparendos cargados previamente
    Y el sistema resive el y procesa el archivo con exito del recaudo
    Entonces resivo una respuesta exitosa de la carga del recaudo

    Ejemplos: 
      | usuario | clave | cantidad |
      | admin   | admin | 1000 |
