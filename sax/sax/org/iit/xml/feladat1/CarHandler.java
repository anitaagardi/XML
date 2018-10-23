package org.iit.xml.feladat1;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CarHandler extends DefaultHandler {
	
	private Stack<String> elements = new Stack<>();
	private String requiredManufacturer;
	private String currentManufacturer;
	private int count;
	private double averagePrice;
	
	public CarHandler(String requiredManufacturer) {
		this.requiredManufacturer = requiredManufacturer;
	}
	
	@Override
	public void startDocument() throws SAXException {
		elements.clear();
		count = 0;
		averagePrice = 0;
	}
	
	@Override
	public void endDocument() throws SAXException {
		System.out.printf("The average price of %s cars is %.2f", requiredManufacturer, averagePrice);
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		elements.add(localName);
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String currentElement = elements.peek();
		
		switch (currentElement) {
			case "manufacturer":
				currentManufacturer = new String(ch, start, length);
				break;
			
			case "price":
				if (requiredManufacturer.equals(currentManufacturer)) {
					count++;
					String priceString = new String(ch, start, length);
					double price = Double.parseDouble(priceString);
					averagePrice += price;
				}
				break;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		String element = elements.pop();
		
		if (element.equals("cars")) {
			averagePrice /= count;
		}
	}
	
}
