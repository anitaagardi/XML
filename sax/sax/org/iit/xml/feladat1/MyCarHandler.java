package org.iit.xml.feladat1;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyCarHandler extends DefaultHandler {

	private Stack<String> elements = new Stack<String>();
	private int count;
	private String currentCar;
	private double currentPrice;

	
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		this.elements.clear();
		this.count=0;

	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		elements.push(localName);
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		
		String currentElement = this.elements.peek();
		if(currentElement.equals("manufacturer")){
			this.currentCar = new String(ch, start, length);
		}
		if(currentElement.equals("price")){
			this.currentPrice = Double.parseDouble(new String(ch, start,length));
			
			if("Honda".equals(this.currentCar) && this.currentPrice < 6){
				this.count++;
			}
		}
	
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		this.elements.pop();
		
		
	}
	
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	
		System.out.println(this.count);
	}
	
}
