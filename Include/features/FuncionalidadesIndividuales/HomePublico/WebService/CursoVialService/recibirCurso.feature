#language: es
@recibirCursos
Característica: Como CIA quiero reportar las personas que realizaron cursos en mi entidad al sistema de simit

  Antecedentes:
  @ExenarioExitoso @WebServices @RegistroCurso
  Escenario: Reportar un cursos de manera exitoza
    Dado que envio el json informando que una persona finalizo un curso de forma exitoza
    Entonces verifico que se halla reportado de manera exitoza

  #Esquema del escenario: 
    #Dado que envio un json sin los siguientes campos <campo>
    #Entonces verifico que muestre fallo al envio
#
    #Ejemplos: 
      #| campo                    |
      #| idCia                    |
      #| idCiudadCia              |
      #| codigoSedeCia            |
      #| idFuncionarioPago        |
      #| idFuncionarioRegistra    |
      #| idTipoDocumentoInfractor |
      #| identificacionInfractor  |
      #| numeroComparendo         |
      #| fechaComparendo          |
      #| idSecretaria             |
      #| referenciaDescuento      |
      #| valorCia                 |
      #| valorFCM                 |
      #| codigoCurso              |
      #| fechaPago                |
      #| fechaCurso               |
      #| numeroCertificado        |
#
  #Esquema del escenario: envio de valores maximo permitidos para cada campo
    #Dado que envio el valores maximos en el <campo> permitidos en el json
    #Entonces verifico que muestre fallo al envio
#
    #Ejemplos: 
      #| campo                    |
      #| idCia                    |
      #| idCiudadCia              |
      #| codigoSedeCia            |
      #| idFuncionarioPago        |
      #| idFuncionarioRegistra    |
      #| idTipoDocumentoInfractor |
      #| identificacionInfractor  |
      #| numeroComparendo         |
      #| fechaComparendo          |
      #| idSecretaria             |
      #| referenciaDescuento      |
      #| valorCia                 |
      #| valorFCM                 |
      #| codigoCurso              |
      #| fechaPago                |
      #| fechaCurso               |
      #| numeroCertificado        |
#
  #Esquema del escenario: envio de valores minimos permitidos en cada campo
    #Dado que envio el valor menor al minimo permitido en el campo <campo> en el json
    #Entonces verifico que muestre fallo al envio
#
    #Ejemplos: 
      #| campo                    |
      #| idCia                    |
      #| idCiudadCia              |
      #| codigoSedeCia            |
      #| idFuncionarioPago        |
      #| idFuncionarioRegistra    |
      #| idTipoDocumentoInfractor |
      #| identificacionInfractor  |
      #| numeroComparendo         |
      #| fechaComparendo          |
      #| idSecretaria             |
      #| referenciaDescuento      |
      #| valorCia                 |
      #| valorFCM                 |
      #| codigoCurso              |
      #| fechaPago                |
      #| fechaCurso               |
      #| numeroCertificado        |
