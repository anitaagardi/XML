package org.iit.xml.dom.practice2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMApp {
	
	private static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	private static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
	private static final String NAMESPACE_URI = "http://iit.uni-miskolc.hu/xml/autok";
	private static final String INPUT_FILE_PATH =  "src/org/iit/xml/dom/xml/autoDB.xml";
	private static final String OUTPUT_FILE_PATH =  "src/org/iit/xml/dom/xml/autoDB-output.xml";
	private static final boolean PRINT_TO_CONSOLE = true;
	
	public static void main(String[] args) {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(true);
		documentBuilderFactory.setValidating(true);
		documentBuilderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
		
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			documentBuilder.setErrorHandler(new AutoErrorHandler());
			Document document = documentBuilder.parse(new File(INPUT_FILE_PATH));
			
			atlagfizetes(document);
			torolUtolsoTulajdonos(document);
			hozzadAuto(document);
			
			if (PRINT_TO_CONSOLE) {
				printDocument(document);
			}
			else {
				printDocument(document, new File(OUTPUT_FILE_PATH));
			}
		}
		catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void atlagfizetes(Document document) {
		List<String> rendszamok = new ArrayList<>();
		double atlagFizetes = 0;
		int darabszam = 0;
		
		Node autokNode = document.getElementsByTagNameNS(NAMESPACE_URI, "autok").item(0);
		NodeList autoNodeList = autokNode.getChildNodes();
		
		for (int i = 0; i < autoNodeList.getLength(); i++) {
			Node autoNode = autoNodeList.item(i);
			
			if (autoNode.getNodeName().equals("a:auto")) {
				String rsz = autoNode.getAttributes().getNamedItem("a:rsz").getNodeValue();
				NodeList autoChildNodes = autoNode.getChildNodes();
				
				for (int j = 0; j < autoChildNodes.getLength(); j++) {
					Node autoChildNode = autoChildNodes.item(j);
					
					if (autoChildNode.getNodeName().equals("a:szin")) {
						String szin = autoChildNode.getTextContent();
						
						if (szin.equals("kek")) {
							rendszamok.add(rsz);
						}
					}
				}
			}
		}
		
		Node tulajdonosokNode = document.getElementsByTagNameNS(NAMESPACE_URI, "tulajdonosok").item(0);
		NodeList tulajdonosNodeList = tulajdonosokNode.getChildNodes();
		
		for (int i = 0; i < tulajdonosNodeList.getLength(); i++) {
			Node tulajdonosNode = tulajdonosNodeList.item(i);
			double fizetes = 0;
			boolean tulajdonosFeldolgozva = false;
			
			if (tulajdonosNode.getNodeName().equals("a:tulajdonos")) {
				NodeList tulajdonosChildNodes = tulajdonosNode.getChildNodes();
				
				for (int j = 0; j < tulajdonosChildNodes.getLength(); j++) {
					Node tulajdonosChildNode = tulajdonosChildNodes.item(j);
					
					switch (tulajdonosChildNode.getNodeName()) {
						case "a:fizetes":
							fizetes = Double.parseDouble(tulajdonosChildNode.getTextContent());
							break;
						
						case "a:autok":
							NodeList tulajdonosAutoNodeList = tulajdonosChildNode.getChildNodes();
							
							for (int k = 0; k < tulajdonosAutoNodeList.getLength(); k++) {
								Node tulajdonosAuto = tulajdonosAutoNodeList.item(k);
								
								if (tulajdonosAuto.getNodeName().equals("a:auto")) {
									String auto = tulajdonosAuto.getTextContent();
									
									if (rendszamok.contains(auto) && !tulajdonosFeldolgozva) {
										tulajdonosFeldolgozva = true;
										atlagFizetes += fizetes;
										darabszam++;
									}
								}
							}
							break;
					}
				}
			}
		}
		
		atlagFizetes /= darabszam;
		
		System.out.printf("A kek autok tulajdonosainak atlagfizetese: %.2f%n%n", atlagFizetes);
	}
	
	public static void torolUtolsoTulajdonos(Document document) {
		Node currentNode = document.getElementsByTagNameNS(NAMESPACE_URI, "tulajdonos").item(0);
		Node lastTulajdonosNode = null;
		
		while (currentNode != null) {
			if ("tulajdonos".equals(currentNode.getLocalName())) {
				lastTulajdonosNode = currentNode;
			}
			
			currentNode = currentNode.getNextSibling();
		}
		
		Node parentNode = lastTulajdonosNode.getParentNode();
		parentNode.removeChild(lastTulajdonosNode);
	}
	
	public static void hozzadAuto(Document document) {
		Node autokNode = document.getElementsByTagNameNS(NAMESPACE_URI, "autok").item(0);
		
		// <a:auto/>
		Element autoElement = document.createElementNS(NAMESPACE_URI, "a:auto");
		autokNode.appendChild(autoElement);
		
		// <a:auto a:rsz="ABC-129"/>
		Attr rszAttribute = document.createAttributeNS(NAMESPACE_URI, "a:rsz");
		rszAttribute.setNodeValue("ABC-129");
		autoElement.setAttributeNode(rszAttribute);
		
		// <a:auto a:rsz="ABC-129">
        //   <a:gyarto>Trabant</a:gyarto>
        // </a:auto>
		Element gyartoElement = document.createElementNS(NAMESPACE_URI, "a:gyarto");
		gyartoElement.setTextContent("Trabant");
		autoElement.appendChild(gyartoElement);
		
		// <a:auto a:rsz="ABC-129">
        //   <a:gyarto>Trabant</a:gyarto>
		//   <a:szin>zold</a:szin>
        // </a:auto>
		Element szinElement = document.createElementNS(NAMESPACE_URI, "a:szin");
		szinElement.setTextContent("zold");
		autoElement.appendChild(szinElement);
	}
	
	public static void printDocument(Document document) {
		printDocument(document, null);
	}
	
	public static void printDocument(Document document, File outputFile) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			DOMSource source = new DOMSource(document);
			StreamResult result;
			if (outputFile == null) {
				result = new StreamResult(System.out);
			}
			else {
				result = new StreamResult(outputFile);
			}
			
			transformer.transform(source, result);
		}
		catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
}