#language: es
#Author: kevin.muentes@quipux.com
@CargasComparendosCompleto
Característica: Como funcionario quiero hacer un procesos de carga masiva de comparendos y liquidarlos 

  #@CargasComparendosCompleto @PruebaCargasMasivas @BotonPagosMasivos
  #Esquema del escenario: probar la capacidad del sistema ante la carga maxiva de comparendos
    #Dado subo <cantidad> de comparendos al sistema con el usuario <usuario> y clave <clave>
    #Cuando el sistema procesa el archivo
    #Entonces resivo una respuesta de la carga
    #Y Abro el navegador y me dirigo al home pulbico de simit
   #	Y realizo el pago de los comparendos cargados al sistema
    #Y termino la prueba de carga y liquidacion Masiva 
#
    #Ejemplos: 
      #| cantidad | usuario | clave |
      #|      3   | kevin   | admin   |

            
  @CargasComparendosCompleto @PruebaCargasMasivas @pruebaLiquidacionMasiva
  Esquema del escenario: probar la capacidad del sistema ante la carga maxiva de comparendos
    Dado subo <cantidad> de comparendos al sistema con el usuario <usuario> y clave <clave>
    Cuando el sistema procesa el archivo
    Entonces resivo una respuesta de la carga
    Y Abro el navegador y me dirigo al home pulbico de simit
    Y realizo la liquidacion de los comparendos cargados por el sistema  
    Y termino la prueba de carga y liquidacion Masiva 
    Dado que genero datos para jmeter de liquidaciones

    Ejemplos: 
      | cantidad | usuario | clave |
      |     10  | admin   | admin |
      