package util

import java.lang.ref.SoftReference
import java.lang.reflect.Field

import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

import org.codehaus.groovy.reflection.ClassInfo
import org.codehaus.groovy.runtime.HandleMetaClass
import org.w3c.dom.Document
import org.w3c.dom.Element

public class GenerateXml {

	/**
	 * Rellena un xml apartir de un objecto dado 
	 * @param doc
	 * @param parent
	 * @param obj
	 * @return null
	 */
	public static fieldsObjectToXml(Document doc, Element parent, Object obj) {
		List<Field> fields = obj.getClass().getDeclaredFields()
		for(Field field: fields) {
			String fieldName = field.getName()

			String objectClass = obj."${fieldName}".getClass().getName()

			switch(objectClass) {
				case String.class.getName():
					Element newElement = doc.createElement(fieldName)
					newElement.appendChild(doc.createTextNode(obj."${fieldName}"))
					parent.appendChild(newElement)
					break
				case Long.class.getName():
					Element newElement = doc.createElement(fieldName)
					newElement.appendChild(doc.createTextNode(obj."${fieldName}".toString()))
					parent.appendChild(newElement)
					break
				case Integer.class.getName():
					Element newElement = doc.createElement(fieldName)
					newElement.appendChild(doc.createTextNode(obj."${fieldName}".toString()))
					parent.appendChild(newElement)
					break
				case Double.class.getName():
					Element newElement = doc.createElement(fieldName)
					newElement.appendChild(doc.createTextNode(obj."${fieldName}".toString()))
					parent.appendChild(newElement)
					break
				case ArrayList.class.getName():
					Element arrayElements = doc.createElement(fieldName)
					parent.appendChild(arrayElements)
					for (Object arrayObj: obj."${fieldName}") {
						Element arrayElement = doc.createElement("element")
						arrayElements.appendChild(arrayElement)
						fieldsObjectToXml(doc, arrayElement, arrayObj)
					}
					break
				case Boolean.class.getName():
					if (!fieldName.equals('__$stMC')) {
						Element newElement = doc.createElement(fieldName)
						newElement.appendChild(doc.createTextNode(obj."${fieldName}".toString()))
						parent.appendChild(newElement)
					}
					break
				case ClassInfo.class.getName():
					break
				case HandleMetaClass.class.getName():
					break
				case SoftReference.class.getName():
					break
				default:
					if (objectClass.startsWith('model')) {
						Element newElement = doc.createElement(fieldName)
						parent.appendChild(newElement)
						fieldsObjectToXml(doc, newElement, obj."${fieldName}")
					} else {
						println "Field ${fieldName} has an unsupported class ${objectClass}"
					}
					break
			}
		}
	}

	/**
	 * genera un archivo de tipo xml apartir de un objecto 
	 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
	 * @param folderPath </br> ruta donde se almacenara el archivo
	 * @param parentName </br> nombre del archivo ademas sera el nombre de la primera etiqueta 
	 * @param obj </br> objecto del cual se creara el archivo
	 * @return null 
	 */

	public static generarXML(String folderPath,String parentName, Object obj) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance()
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder()

			Document doc = docBuilder.newDocument()
			Element parent = doc.createElement(parentName)
			doc.appendChild(parent)

			if (obj.getClass().getName().equals(ArrayList.class.getName())){
				int i = 0
				println obj.size()
				for (Object arrayObj: ((List<Object>) obj)) {
					println i++
					Element arrayElement = doc.createElement("element")
					parent.appendChild(arrayElement)
					fieldsObjectToXml(doc, arrayElement, arrayObj)
				}
			} else {
				fieldsObjectToXml(doc, parent, obj)
			}


			TransformerFactory transformerFactory = TransformerFactory.newInstance()
			Transformer transformer = transformerFactory.newTransformer()
			DOMSource source = new DOMSource(doc)

			String path = folderPath + parentName + ".xml"
			File file = new File(path)
			if (!file.exists()) {
				file.createNewFile()
			}
			StreamResult result = new StreamResult(new FileWriter(file))
			transformer.transform(source, result)
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace()
		} catch (TransformerException tfe) {
			tfe.printStackTrace()
		}
	}
}
