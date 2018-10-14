package sax.org.iit.xml.sax.feladat3.a;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class AutoHandler extends DefaultHandler {
	
	private Stack<String> elemek = new Stack<>();
	private int osszfizetes;
	
	@Override
	public void startDocument() throws SAXException {
		elemek.clear();
		osszfizetes = 0;
	}
	
	@Override
	public void endDocument() throws SAXException {
		System.out.printf("A tulajdonosok osszfizetese: %d", osszfizetes);
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
			case "fizetes":
				String fizetesString = new String(ch, start, length);
				int fizetes = Integer.parseInt(fizetesString);
				osszfizetes += fizetes;
				break;
		}
	}
	
	@Override
	public void error(SAXParseException e) throws SAXException {
		e.printStackTrace();
	}
	
}