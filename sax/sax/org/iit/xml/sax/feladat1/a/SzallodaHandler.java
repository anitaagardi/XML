package sax.org.iit.xml.sax.feladat1.a;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class SzallodaHandler extends DefaultHandler {
	
	private Stack<String> elemek = new Stack<>();
	private String currentSzisz;
	private String szisz;
	private int count;
	
	@Override
	public void startDocument() throws SAXException {
		this.elemek.clear();
		this.szisz = "";
		this.count = 0;
	}
	
	@Override
	public void endDocument() throws SAXException {
		System.out.printf("John Doe foglalasainak szama: %d\n", this.count);
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		this.elemek.push(localName);
		
		switch (localName) {
			case "vendeg":
				this.currentSzisz = attributes.getValue("szisz");
				break;
			
			case "foglalas":
				String vendeg = attributes.getValue("vendeg");
				if (vendeg.equals(this.szisz)) {
					this.count++;
				}
				break;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		this.elemek.pop();
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String text = new String(ch, start, length);
		
		if (this.elemek.peek().equals("nev") && text.equals("John Doe")) {
			this.szisz = this.currentSzisz;
		}
	}
	
	@Override
	public void error(SAXParseException e) throws SAXException {
		e.printStackTrace();
	}
	
}