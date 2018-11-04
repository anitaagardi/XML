package jaxb.org.iit.xml.practice6;

import java.io.FileReader;

import javax.xml.bind.JAXBContext;

public class UnMarshalling {
	private static String XML_PATH = "src/jaxb/org/iit/xml/practice6/Book.xml";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
		JAXBContext context = JAXBContext.newInstance(Book.class);
	    Book book= (Book) context.createUnmarshaller()
	      .unmarshal(new FileReader(XML_PATH ));
	    System.out.println("Author: "+book.getAuthor());
	    System.out.println("Name: "+book.getName());
	    System.out.println("Date: "+book.getDate());
	    System.out.println("Id: "+book.getId());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
