package jaxb.org.iit.xml.practice7;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
//https://howtodoinjava.com/jaxb/jaxb-exmaple-marshalling-and-unmarshalling-list-or-set-of-objects/
public class MarshalListToXML {
	//Initialize the employees list
	private static String XML_PATH="src/jaxb/org/iit/xml/practice5/Employees.xml";
	 
	private static void marshalingExample() throws JAXBException
	{
		 Employees employees = new Employees();
		
		    employees.setEmployees(new ArrayList<Employee>());
		    //Create two employees
		    Employee emp1 = new Employee();
		    emp1.setId(1);
		    emp1.setFirstName("Lokesh");
		    emp1.setLastName("Gupta");
		    emp1.setIncome(100.0);
		     
		    Employee emp2 = new Employee();
		    emp2.setId(2);
		    emp2.setFirstName("John");
		    emp2.setLastName("Mclane");
		    emp2.setIncome(200.0);
		     
		    //Add the employees in list
		    employees.getEmployees().add(emp1);
		    employees.getEmployees().add(emp2);
		
	    JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	     
	    //Marshal the employees list in console
	    jaxbMarshaller.marshal(employees, System.out);
	     
	    //Marshal the employees list in file
		jaxbMarshaller.marshal(employees, new File(XML_PATH));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			marshalingExample();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
