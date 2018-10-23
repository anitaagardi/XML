package org.iit.xml.feladat1;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

public class MySAXApp {

	public static final String INPUT_FILE_PATH = "src/data/cars.xml";
	
	public static void main(String[] args) {
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		
		try{
			SAXParser parser = factory.newSAXParser();
			DefaultHandler handler = new MyCarHandler();
			
			parser.parse(INPUT_FILE_PATH, handler);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
