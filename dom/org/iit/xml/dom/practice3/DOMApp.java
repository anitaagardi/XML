package org.iit.xml.dom.practice3;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMApp {
	
	private static final String INPUT_FILE_PATH = "src/org/iit/xml/dom/xml/autoDB.xml";
	private static final String NAMESPACE_URI = "http://iit.uni-miskolc.hu/xml/autok";
	
	public static void main(String[] args) {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(true);
		
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(new File(INPUT_FILE_PATH));
			
			kiirOsszFizetes(document);
			kiirAutokDarabszama(document);
			kiirAtlagFizetes(document);
		}
		catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void kiirOsszFizetes(Document document) {
		int sum = 0;
		
		NodeList tulajdonosElements = document.getElementsByTagNameNS(NAMESPACE_URI, "tulajdonos");
		
		for (int i = 0; i < tulajdonosElements.getLength(); i++) {
			Node tulajdonosElement = tulajdonosElements.item(i);
			NodeList tulajdonosChildren = tulajdonosElement.getChildNodes();
			
			for (int j = 0; j < tulajdonosChildren.getLength(); j++) {
				Node tulajdonosChild = tulajdonosChildren.item(j);
				
				if (tulajdonosChild.getNodeType() != Node.ELEMENT_NODE) {
					continue;
				}
				
				String elementName = tulajdonosChild.getLocalName();
				
				switch (elementName) {
					case "fizetes":
						int salary = Integer.parseInt(tulajdonosChild.getTextContent());
						sum += salary;
						break;
				}
			}
		}
		
		System.out.printf("A tulajdonosok osszfizetese: %d%n%n", sum);
	}
	
	public static void kiirAutokDarabszama(Document document) {
		int count = 0;
		Node autokElement = document.getElementsByTagNameNS(NAMESPACE_URI, "autok").item(0);
		NodeList autoElements = autokElement.getChildNodes();
		
		for (int i = 0; i < autoElements.getLength(); i++) {
			Node childNode = autoElements.item(i);
			
			if (childNode.getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}
			
			String elementName = childNode.getLocalName();
			
			if ("auto".equals(elementName)) {
				count++;
			}
		}
		
		System.out.printf("Az autok darabszama: %d%n%n", count);
	}
	
	public static void kiirAtlagFizetes(Document document) {
		double average = 0;
		
		NodeList tulajdonosElements = document.getElementsByTagNameNS(NAMESPACE_URI, "tulajdonos");
		int count = tulajdonosElements.getLength();
		
		for (int i = 0; i < tulajdonosElements.getLength(); i++) {
			Node tulajdonosElement = tulajdonosElements.item(i);
			NodeList tulajdonosChildren = tulajdonosElement.getChildNodes();
			
			for (int j = 0; j < tulajdonosChildren.getLength(); j++) {
				Node tulajdonosChild = tulajdonosChildren.item(j);
				
				if (tulajdonosChild.getNodeType() != Node.ELEMENT_NODE) {
					continue;
				}
				
				String elementName = tulajdonosChild.getLocalName();
				
				switch (elementName) {
					case "fizetes":
						int salary = Integer.parseInt(tulajdonosChild.getTextContent());
						average += salary;
						break;
				}
			}
		}
		
		average /= count;
		System.out.printf("A tulajdonosok atlagfizetese: %.2f%n%n", average);
	}
	
}
