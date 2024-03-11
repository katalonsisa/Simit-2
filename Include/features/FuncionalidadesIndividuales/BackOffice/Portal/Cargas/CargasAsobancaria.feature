#language: es
@CargaAsobancaria 
Característica: como funcionario quiero hacer cargas de archivos de pagos de Asobancaria

  Antecedentes: 
    Dado que ingreso al portal de backOffice
    Y selecciono la opcion Carga recaudo externo (Asobancaria) del modulo Cargas

  Escenario: voy a cargar un archivo simple de asobancaria
    Dado Subo el archivo de asobancaria
    Cuando hago el envío del archivo
    Entonces me muestra una ventana con el mensaje de que el archivo ha sido cargado
