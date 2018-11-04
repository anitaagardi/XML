package jaxb.org.iit.xml.practice6;

import java.io.File;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Marshalling {
	private static String XML_PATH = "src/jaxb/org/iit/xml/practice6/Book.xml";

	public static void main(String[] args) {
		Book book = new Book();
		book.setId(1L);
		book.setName("Book1");
		book.setAuthor("Author1");
		book.setDate(new Date());
		try {
			JAXBContext context = JAXBContext.newInstance(Book.class);
			Marshaller mar = context.createMarshaller();
			mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			mar.marshal(book, new File(XML_PATH));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
