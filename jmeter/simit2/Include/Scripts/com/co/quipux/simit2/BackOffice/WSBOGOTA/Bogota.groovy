import java.text.SimpleDateFormat

/* groovylint-disable-next-line CompileStatic */
SimpleDateFormat fechasimpleformat = new SimpleDateFormat('YYYYMMdd')
SimpleDateFormat fechacomplejaformat = new SimpleDateFormat('YYYYMMDDHHmmS')

String fechasimple = fechasimpleformat.format(new Date())
String fechacompleja = fechacomplejaformat.format(new Date())

vars.put('FechaBogotaSimple', fechasimple.toString())
vars.put('FechaBogotaCompleja', fechacompleja.toString())
