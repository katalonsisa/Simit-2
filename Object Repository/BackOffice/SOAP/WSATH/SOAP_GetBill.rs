<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>SOAP_GetBill</name>
   <tag></tag>
   <elementGuidId>f3f3b016-0838-4a30-b940-72f57cfa5696</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent></httpBodyContent>
   <httpBodyType></httpBodyType>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>text/xml; charset=utf-8</value>
      <webElementGuid>71623e70-e1cd-41f3-bf21-3eea4c17a78e</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>8.4.1</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <restRequestMethod></restRequestMethod>
   <restUrl></restUrl>
   <serviceType>SOAP</serviceType>
   <soapBody>&lt;soapenv:Envelope xmlns:soapenv=&quot;http://schemas.xmlsoap.org/soap/envelope/&quot; xmlns:web=&quot;http://webservices.qxmultas.quipux.com.co&quot; xmlns:xsd=&quot;http://onlinebilling.biller.webservices.qxmultas.quipux.com.co/xsd&quot;>
   &lt;soapenv:Header/>
   &lt;soapenv:Body>
      &lt;web:getBill>
         &lt;web:billRequest>
            &lt;xsd:agreementId>100&lt;/xsd:agreementId>
            &lt;xsd:currentDatetime>2022-03-08T00:00:00.000Z&lt;/xsd:currentDatetime> &lt;!-- Fecha generacion de liquidacion -->
            &lt;xsd:inqDate>2022-03-08T00:00:00.000Z&lt;/xsd:inqDate> &lt;!-- Fecha generacion de liquidacion -->
            &lt;xsd:invoiceId>100738230125&lt;/xsd:invoiceId> &lt;!-- Codigo de liquidacion puede ser el 8020 o Numero de liquidacion -->
            &lt;xsd:reference>
               &lt;xsd:message>1&lt;/xsd:message>
               &lt;xsd:name>1&lt;/xsd:name>
            &lt;/xsd:reference>
            &lt;xsd:requestId>1&lt;/xsd:requestId>
            &lt;xsd:searchType>1&lt;/xsd:searchType>
         &lt;/web:billRequest>
      &lt;/web:getBill>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope>
</soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>https://${GlobalVariable.dominio}/Simit/services/WsATH?wsdl</soapServiceEndpoint>
   <soapServiceFunction>getBill</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()</verificationScript>
   <wsdlAddress>https://${GlobalVariable.dominio}/Simit/services/WsATH?wsdl</wsdlAddress>
</WebServiceRequestEntity>
