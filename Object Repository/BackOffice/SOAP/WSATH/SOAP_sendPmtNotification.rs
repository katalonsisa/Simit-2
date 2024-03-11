<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>SOAP_SendPmtNotification</name>
   <tag></tag>
   <elementGuidId>3e0b49f8-b7b4-4eb7-ac89-187dd5543d33</elementGuidId>
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
      <name>SOAPAction</name>
      <type>Main</type>
      <value>urn:sendPmtNotification</value>
      <webElementGuid>f35384a5-60d0-456e-b0c3-ff9590a1a741</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>text/xml; charset=utf-8</value>
      <webElementGuid>b7bd632c-378d-4499-b462-007ebf2c48b2</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>8.4.1</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <restRequestMethod></restRequestMethod>
   <restUrl></restUrl>
   <serviceType>SOAP</serviceType>
   <soapBody>&lt;soapenv:Envelope xmlns:soapenv=&quot;http://schemas.xmlsoap.org/soap/envelope/&quot; xmlns:web=&quot;http://webservices.qxmultas.quipux.com.co&quot; xmlns:xsd=&quot;http://onlinebilling.biller.webservices.qxmultas.quipux.com.co/xsd&quot;>
   &lt;soapenv:Header/>
   &lt;soapenv:Body>
      &lt;web:sendPmtNotification>
         &lt;web:pmtNotificationRequest>
            &lt;xsd:currentDatetime>2008-09-28T20:49:45&lt;/xsd:currentDatetime>
            &lt;xsd:inqDate>2014-09-18T18:18:33&lt;/xsd:inqDate>
            &lt;xsd:paidInvoices>
               &lt;xsd:agreementId>3&lt;/xsd:agreementId>
               &lt;xsd:bankAuthCode>verrantque per auras&lt;/xsd:bankAuthCode>
               &lt;xsd:bankSrc>per auras&lt;/xsd:bankSrc>
               &lt;xsd:invoiceId>10&lt;/xsd:invoiceId>
               &lt;xsd:paidValue>1000.00&lt;/xsd:paidValue>
               &lt;xsd:valuesDetail>
                  &lt;xsd:description>circum claustra&lt;/xsd:description>
                  &lt;xsd:value>1000.00&lt;/xsd:value>
               &lt;/xsd:valuesDetail>
            &lt;/xsd:paidInvoices>
            &lt;xsd:requestId>nimborum in&lt;/xsd:requestId>
         &lt;/web:pmtNotificationRequest>
      &lt;/web:sendPmtNotification>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>https://${GlobalVariable.dominio}/Simit/services/WsATH?wsdl</soapServiceEndpoint>
   <soapServiceFunction>sendPmtNotification</soapServiceFunction>
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
