#language: es
#Author: kevin.muentes@quipux.com

@Kiosco
Característica: Como funcionario quiero realizar la asignacion de los turnos

  Antecedentes: 
    Dado que ingreso al portal de GA
    Y selecciono la opcion Kiosco del modulo Gestión de la atención    
    
    @Exitosos
    Escenario: Asignar un turno para paz y salvo a un ciudadano
    Dado que solicito un turno y ingreso un documento en el kiosco
    Cuando el sistema me pida el tipo de servicio seleccion Solicitar paz y salvo 
    Entonces solicito el turno 
    
