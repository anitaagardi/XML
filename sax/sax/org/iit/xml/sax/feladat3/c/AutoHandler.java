package sax.org.iit.xml.sax.feladat3.c;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class AutoHandler extends DefaultHandler {
	
	private Stack<String> elemek = new Stack<>();
	private double atlagfizetes;
	private int darabszam;
	
	@Override
	public void startDocument() throws SAXException {
		elemek.clear();
		atlagfizetes = 0;
		darabszam = 0;
	}
	
	@Override
	public void endDocument() throws SAXException {
		System.out.printf("A tulajdonosok atlagfizetese: %.2f", atlagfizetes);
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		elemek.push(localName);
		
		if ("tulajdonos".equals(localName)) {
			darabszam++;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		elemek.pop();
		
		if ("tulajdonosok".equals(localName)) {
			atlagfizetes /= darabszam;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		switch (elemek.peek()) {
			case "fizetes":
				String fizetesString = new String(ch, start, length);
				int fizetes = Integer.parseInt(fizetesString);
				atlagfizetes += fizetes;
				break;
		}
	}
	
	@Override
	public void error(SAXParseException e) throws SAXException {
		e.printStackTrace();
	}
	
}