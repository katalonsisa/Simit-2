package util

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper
import internal.GlobalVariable


public class Cookie{
	String name
	String vaule


	Cookie(String name, String vaule) {
		this.name = name
		this.vaule = vaule
	}
}

public class WebServicesUtil {

	/**
	 * Metodo para iniciar sesion en un servicio web y retornar el token de la conexion
	 * @param user <br> usuario con el que se ingresara
	 * @param pass <br> contraseña 
	 * @param login <br> ruta donde esta el servicio web del login 
	 * @return Retorna el token de la sesion 
	 * @author kevin.muentes
	 */
	public static String retornarToken(String user,String pass,String login ){
		ResponseObject respuesta
		RequestObject response = findTestObject(login,[('url'):GlobalVariable.dominio,('contexto'):GlobalVariable.contexto])
		response.setHttpBody('{"usuario":"'+user+'","password":"'+pass+'"}')
		respuesta = WS.sendRequest(response)
		verifyCodeResponst(respuesta)
		def jsonSlurper = new JsonSlurper()
		def result = jsonSlurper.parseText(respuesta.getResponseBodyContent())
		result instanceof Map
		if(result.token.equals("")){
			KeywordUtil.markFailedAndStop("Error de autentificacion")
		}
		return result.token.toString()
	}

	/**
	 * 	 
	 * @param token <br> token de las sesion activa
	 * @return <b>HTPPHeader</b> <b>Retorna una lista de tipo TestObjectProperty con las HTPPHeader
	 * @author kevin.muentes
	 */
	public static ArrayList<TestObjectProperty> HTTPHeaderCookiesToken(String token){
		def HTTPHeader = new ArrayList<>()
		if(!token.equalsIgnoreCase("") || !token.isEmpty()){
			HTTPHeader.add(new TestObjectProperty("Cookie", ConditionType.EQUALS, GlobalVariable.tokenName + "=" + token + ";path=/;domain=." + GlobalVariable.dominio))
		}
		HTTPHeader.add(new TestObjectProperty("Content-Type", ConditionType.EQUALS, "application/json"))
		return HTTPHeader
	}


	public static ArrayList<TestObjectProperty> HTTPHeaderCookies(ArrayList<Cookie> cookie ){
		def HTTPHeader = new ArrayList<>()
		for (var in cookie) {
			HTTPHeader.add(new TestObjectProperty(var.name, ConditionType.EQUALS, var.vaule))
		}
		HTTPHeader.add(new TestObjectProperty("Content-Type", ConditionType.EQUALS, "application/json"))
		return HTTPHeader
	}



	/**
	 * Verifica que el codigo de respuesta del servicio
	 * @param respuesta
	 * @author kevin.muentes
	 */
	public static void verifyCodeResponst(ResponseObject respuesta){
		if(WS.verifyResponseStatusCodeInRange(respuesta, 200, 300, FailureHandling.OPTIONAL)){
			KeywordUtil.markPassed("Servicio activo");
		}
		if(WS.verifyResponseStatusCodeInRange(respuesta, 300, 400, FailureHandling.OPTIONAL)){
			KeywordUtil.markWarning("Se realizo un cambio de pagina")
		}
		if(WS.verifyResponseStatusCodeInRange(respuesta, 400, 500, FailureHandling.OPTIONAL)){
			KeywordUtil.markFailedAndStop("fallo de estructura o falta ingresar al sistema: \n" + respuesta.getResponseBodyContent())
		}
		if(WS.verifyResponseStatusCodeInRange(respuesta, 500, 599,FailureHandling.OPTIONAL)){
			KeywordUtil.markErrorAndStop("El servidor esta inactivo")
		}
	}
}
