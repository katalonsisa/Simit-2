import java.text.SimpleDateFormat

/* groovylint-disable-next-line CompileStatic */
SimpleDateFormat Fechaformateador = new SimpleDateFormat('YYYY-MM-dd')

String fechaComparendo = Fechaformateador.format(new Date())

vars.put('FechaLiquidacion', fechaComparendo.toString())
