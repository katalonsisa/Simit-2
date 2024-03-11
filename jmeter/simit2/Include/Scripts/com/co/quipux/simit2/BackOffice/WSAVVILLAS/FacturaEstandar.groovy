import java.text.SimpleDateFormat

/* groovylint-disable-next-line CompileStatic */
SimpleDateFormat Fechaformateador = new SimpleDateFormat('YYYYMMdd')
SimpleDateFormat horaformat = new SimpleDateFormat('HHmm')

String fechafactura = Fechaformateador.format(new Date())
String Horafactura = horaformat.format(new Date())

vars.put('FechaFacturaEstandar', fechafactura.toString())
vars.put('HoraFacturaEstandar', Horafactura.toString())
