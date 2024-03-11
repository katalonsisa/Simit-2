import org.apache.commons.lang.RandomStringUtils
import java.security.SecureRandom


def tipoDocumentoSelect = new SecureRandom().nextInt(1) + 1
def tipoDocumento = [0:11, 1:10, 2:10, 3:6, 4:10, 5:11, 6:14, 7:9, 8:11, 9:11]
String charset = (('0'..'9')).join()
String randomString = RandomStringUtils.random(tipoDocumento[tipoDocumentoSelect], charset.toCharArray())

vars.put('tipoDocumento', tipoDocumentoSelect.toString())
vars.put('DocumentoIdentidad', randomString.toString())
