package sax.org.iit.xml.sax.feladat1.b;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class SzallodaHandler extends DefaultHandler {
	
	private Stack<String> elemek = new Stack<>();
	private String currentSzisz;
	private String szisz;
	private String currentLakosztalyId;
	private List<String> lakosztalyIds = new ArrayList<>();
	private int count;
	
	@Override
	public void startDocument() throws SAXException {
		this.elemek.clear();
		this.szisz = "";
		this.lakosztalyIds.clear();
		this.count = 0;
	}
	
	@Override
	public void endDocument() throws SAXException {
		System.out.printf("John Doe lakosztaly foglalasainak szama: %d\n", this.count);
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		this.elemek.push(localName);
		
		switch (localName) {
			case "szoba":
				this.currentLakosztalyId = attributes.getValue("id");
				break;
			
			case "leiras":
				boolean isLakosztaly = Boolean.parseBoolean(attributes.getValue("lakosztaly"));
				if (isLakosztaly) {
					this.lakosztalyIds.add(this.currentLakosztalyId);
				}
				break;
			
			case "vendeg":
				this.currentSzisz = attributes.getValue("szisz");
				break;
			
			case "foglalas":
				String vendeg = attributes.getValue("vendeg");
				String szoba = attributes.getValue("szoba");
				if (vendeg.equals(this.szisz) && this.lakosztalyIds.contains(szoba)) {
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