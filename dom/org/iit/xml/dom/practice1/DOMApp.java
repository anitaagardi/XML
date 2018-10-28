package org.iit.xml.dom.practice1;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class DOMApp {
	
	private static final String INPUT_FILE_PATH = "src/org/iit/xml/dom/xml/cars.xml";
	private static final String NAMESPACE_URI = "http://iit.uni-miskolc.hu/xml/cars";
	private static final String MANUFACTURER_HONDA = "Honda";
	private static final String MANUFACTURER_MERCEDES = "Mercedes";
	
	public static void main(String[] args) {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(true);
		
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(new File(INPUT_FILE_PATH));
			
			printAveragePriceOfManufacturer(document, MANUFACTURER_HONDA);
			removeManufacturer(document, MANUFACTURER_MERCEDES);
			addCar(document, new Car("Ferrari", "red", 120));

		}
		catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void printAveragePriceOfManufacturer(Document document, String manufacturer) {
		double averagePrice = 0;
		int count = 0;
		
		NodeList carElements = document.getElementsByTagNameNS(NAMESPACE_URI, "car");
		
		for (int i = 0; i < carElements.getLength(); i++) {
			Node carElement = carElements.item(i);
			
			String currentManufacturer = null;
			double currentPrice = 0;
			NodeList carChildren = carElement.getChildNodes();
			for (int j = 0; j < carChildren.getLength(); j++) {
				Node carChild = carChildren.item(j);
				
				if (carChild.getNodeType() != Node.ELEMENT_NODE) {
					continue;
				}
				
				String elementName = carChild.getNodeName();
				
				
				switch (elementName) {
					case "manufacturer":
						currentManufacturer = carChild.getTextContent();
						break;
					
					case "price":
						currentPrice = Double.parseDouble(carChild.getTextContent());
						break;
				}
			}
			
			if (currentManufacturer.equals(manufacturer)) {
				averagePrice += currentPrice;
				count++;
			}
		}
		
		averagePrice /= count;
		System.out.printf("The average price of %s cars is %.2f%n%n", manufacturer, averagePrice);
	}
	
	public static void removeManufacturer(Document document, String manufacturer) {
		NodeList manufacturerElements = document.getElementsByTagNameNS(NAMESPACE_URI, "manufacturer");
		
		for (int i = 0; i < manufacturerElements.getLength(); i++) {
			Node manufacturerElement = manufacturerElements.item(i);
			String currentManufacturer = manufacturerElement.getTextContent();
			
			if (currentManufacturer.equals(manufacturer)) {
				Node carElement = manufacturerElement.getParentNode();
				Node carsElement = carElement.getParentNode();
				
				carsElement.removeChild(carElement);
			}
		}
		
		printDocument(document);
		System.out.printf("%n%n");
	}
	
	public static void addCar(Document document, Car car) {
		Element carsElement = document.getDocumentElement();
		
		// <car/>
		Element carElement = document.createElementNS(NAMESPACE_URI, "car");
		carsElement.appendChild(carElement);
		
		// <car>
		//   <manufacturer/>
		// </cars>
		Element manufacturerElement = document.createElementNS(NAMESPACE_URI, "manufacturer");
		carElement.appendChild(manufacturerElement);
		
		// <car>
		//   <manufacturer>${car.manufacturer}</manufacturer>
		// </cars>
		Text manufacturerText = document.createTextNode(car.getManufacturer());
		manufacturerElement.appendChild(manufacturerText);
		
		// <car>
		//   <manufacturer>${car.manufacturer}</manufacturer>
		//   <color/>
		// </cars>
		Element colorElement = document.createElementNS(NAMESPACE_URI, "color");
		carElement.appendChild(colorElement);
		
		// <car>
		//   <manufacturer>${car.manufacturer}</manufacturer>
		//   <color>${car.color}</color>
		// </cars>
		Text colorText = document.createTextNode(car.getColor());
		colorElement.appendChild(colorText);
		
		// <car>
		//   <manufacturer>${car.manufacturer}</manufacturer>
		//   <color>${car.color}</color>
		//   <price/>
		// </cars>
		Element priceElement = document.createElementNS(NAMESPACE_URI, "price");
		carElement.appendChild(priceElement);
		
		// <car>
		//   <manufacturer>${car.manufacturer}</manufacturer>
		//   <color>${car.color}</color>
		//   <price>${car.price}</price>
		// </cars>
		Text priceText = document.createTextNode(String.valueOf(car.getPrice()));
		priceElement.appendChild(priceText);
		
		printDocument(document);
	}
	//DOM-ot írja ki a console-ra
	private static void printDocument(Document document) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(System.out);
			
			transformer.transform(source, result);
		}
		catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	
}
