/* groovylint-disable CompileStatic */
import org.apache.commons.lang.RandomStringUtils
import java.security.SecureRandom
import java.text.SimpleDateFormat
import java.io.File 


String charset = (('0'..'9')).join()
//def tipoDocumento = [0:11, 1:10, 2:10, 3:6, 4:10, 5:11, 6:14, 7:9, 8:11, 9:11]



SimpleDateFormat Fechaformateador = new SimpleDateFormat('dd/MM/yyyy')
SimpleDateFormat Horaformateador = new SimpleDateFormat('HHmm')


String fechaComparendo = Fechaformateador.format(new Date())
String Hora = Horaformateador.format(new Date())

int totalComparendos = (int) (new SecureRandom().nextDouble() * vars.get('limit_comparendos').toInteger())+1
String diviSecre = vars.get('Divipo')
String secretaria = vars.get('Secretaria')
String archivo = ''
for (int i = 1; i <= totalComparendos; i++) {
    def tipoDocumentoSelect =1
    String cedula = RandomStringUtils.random(11, charset.toCharArray())
    String randomNumero = RandomStringUtils.random(10, charset.toCharArray())
    String dire = vars.get('Direcion')
    String placa = vars.get('Placa')
    String nombre = vars.get('Nombre')

    archivo += "${i},${diviSecre}00${randomNumero},${fechaComparendo},${Hora},${dire},${diviSecre},${secretaria}" +
        ",${placa},${diviSecre},5,4,0,,${tipoDocumentoSelect},${cedula},2,Carlos,Zorzano,47,${dire}"+
        ",,4340181267,${diviSecre},1993233499,1,${diviSecre},09/10/2029,1,0334762271,${diviSecre},1584580241,4,"+
    "${nombre},,,,999,PRUEBA JMETER,N,N,N,N,,,,,,,,,45120,0,${diviSecre},1,N,C32,45120, \n"
}
def valorTotal = 45120 * totalComparendos
archivo += "${totalComparendos},${valorTotal},4618808826,8297969317"

def archivoFinal = archivo.toString().bytes.encodeBase64().toString()
vars.put('ArchivoCargaArchivo', archivoFinal)

log.info(vars.get('ArchivoCargaArchivo'))
