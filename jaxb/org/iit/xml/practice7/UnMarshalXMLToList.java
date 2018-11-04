package jaxb.org.iit.xml.practice7;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
//https://howtodoinjava.com/jaxb/jaxb-exmaple-marshalling-and-unmarshalling-list-or-set-of-objects/
public class UnMarshalXMLToList {
	private static String XML_PATH="src/jaxb/org/iit/xml/practice5/Employees.xml";
	private static void main() throws JAXBException
	{
	    JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	     
	    //We had written this file in marshalling example
	    Employees emps = (Employees) jaxbUnmarshaller.unmarshal( new File(XML_PATH) );
	     
	    for(Employee emp : emps.getEmployees())
	    {
	        System.out.println(emp.getId());
	        System.out.println(emp.getFirstName());
	    }
	}
}
