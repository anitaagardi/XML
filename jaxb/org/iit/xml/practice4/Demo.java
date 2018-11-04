package jaxb.org.iit.xml.practice4;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
 //http://blog.bdoughan.com/2011/06/jaxb-and-complex-types-with-simple.html
public class Demo {
 
    public static void main(String[] args) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(PhoneNumber.class);
 
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setType("work");
        phoneNumber.setNumber("555-1234");
 
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(phoneNumber, System.out);
    }
 
}
