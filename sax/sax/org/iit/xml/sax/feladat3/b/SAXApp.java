package sax.org.iit.xml.sax.feladat3.b;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXApp {
	
	private static final String INPUT_FILE_PATH = "src/sax/data/autoDB.xml";
	
	// Ezek csak akkor kellenek, ha validalunk is
	private static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	private static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
	private static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
	private static final String XML_SCHEMA_PATH = "src/sax/data/autoDB.xsd";
	
	public static void main(String[] args) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		saxParserFactory.setNamespaceAware(true);
		
		// Ha nem akarunk validalni, ez a sor torolheto
		saxParserFactory.setValidating(true);
		
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			
			// Ez a ket sor torolheto, ha nem akarunk validalni.
			saxParser.setProperty(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
			saxParser.setProperty(JAXP_SCHEMA_SOURCE, new File(XML_SCHEMA_PATH));
			
			DefaultHandler handler = new AutoHandler();
			saxParser.parse(new File(INPUT_FILE_PATH), handler);
		}
		catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
}