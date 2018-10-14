package sax.org.iit.xml.sax.feladat3.b;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class AutoHandler extends DefaultHandler {
	
	private Stack<String> elemek = new Stack<>();
	private int darabszam;
	
	@Override
	public void startDocument() throws SAXException {
		elemek.clear();
		darabszam = 0;
	}
	
	@Override
	public void endDocument() throws SAXException {
		System.out.printf("Az autok darabszama: %d", darabszam);
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		elemek.push(localName);
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		elemek.pop();
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		switch (elemek.peek()) {
			case "auto":
				darabszam++;
				break;
		}
	}
	
	@Override
	public void error(SAXParseException e) throws SAXException {
		e.printStackTrace();
	}
	
}