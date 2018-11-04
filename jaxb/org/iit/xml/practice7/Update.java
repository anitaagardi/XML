package jaxb.org.iit.xml.practice7;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Update {
	private static String XML_PATH = "src/jaxb/org/iit/xml/practice5/Employees.xml";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			// We had written this file in marshalling example
			Employees emps = (Employees) jaxbUnmarshaller.unmarshal(new File(XML_PATH));

			for (Employee emp : emps.getEmployees()) {
				System.out.println(emp.getId());
				System.out.println(emp.getFirstName());
				emp.setId(15);
			}
			Employee emp1 = new Employee();
			emp1.setId(3);
			emp1.setFirstName("XYZ");
			emp1.setLastName("ABC");
			emp1.setIncome(1000.0);
			emps.getEmployees().add(emp1);
			jaxbContext = JAXBContext.newInstance(Employees.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Marshal the employees list in console
			jaxbMarshaller.marshal(emps, System.out);

			// Marshal the employees list in file
			jaxbMarshaller.marshal(emps, new File(XML_PATH));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
