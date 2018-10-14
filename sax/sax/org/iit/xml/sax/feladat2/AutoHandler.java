package sax.org.iit.xml.sax.feladat2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class AutoHandler extends DefaultHandler {
	
	private static final String NAMESPACE_URI = "http://iit.uni-miskolc.hu/xml/autok";
	
	private Stack<String> elemek = new Stack<>();
	private String aktualisRendszam;
	private List<String> rendszamok = new ArrayList<>();
	private double aktualisFizetes;
	private boolean tulajdonosFeldolgozva;
	private double atlagFizetes;
	private int darabszam;
	
	@Override
	public void startDocument() throws SAXException {
		elemek.clear();
		rendszamok.clear();
		atlagFizetes = 0;
		darabszam = 0;
	}
	
	@Override
	public void endDocument() throws SAXException {
		System.out.printf("A kek autok tulajdonosainak atlagfizetese: %.2f", atlagFizetes);
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		elemek.push(localName);
		
		switch (localName) {
			case "auto":
				//aktualisRendszam = attributes.getValue("a:rsz");
				aktualisRendszam = attributes.getValue(NAMESPACE_URI, "rsz");
				break;
			
			case "tulajdonos":
				tulajdonosFeldolgozva = false;
				break;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		elemek.pop();
		
		switch (localName) {
			case "tulajdonosok":
				atlagFizetes /= darabszam;
				break;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		switch (elemek.peek()) {
			case "szin":
				String szin = new String(ch, start, length);
				if (szin.equals("kek")) {
					rendszamok.add(aktualisRendszam);
				}
				break;
			
			case "fizetes":
				aktualisFizetes = Double.parseDouble(new String(ch, start, length));
				break;
			
			case "auto":
				String rsz = new String(ch, start, length);
				if (!rsz.trim().equals("") && rendszamok.contains(rsz) && !tulajdonosFeldolgozva) {
					atlagFizetes += aktualisFizetes;
					darabszam++;
					tulajdonosFeldolgozva = true;
				}
				break;
		}
	}
	
	@Override
	public void error(SAXParseException e) throws SAXException {
		e.printStackTrace();
	}
	
}