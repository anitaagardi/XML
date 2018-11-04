package jaxb.org.iit.xml.practice2;

import java.io.File;

import javax.xml.bind.JAXB;
//https://www.tutorialspoint.com/java/xml/javax_xml_bind_jaxb_unmarshal_file.htm
public class JAXBDemo {
	private static String XML_PATH="src/jaxb/org/iit/xml/practice1/Student.xml";
   public static void main(String[] args) {

      try {         
         // create file writer object
         File f = new File(XML_PATH);
         
         // marshal object to file writer
         Student st = JAXB.unmarshal(f, Student.class);
         
         System.out.println("Age : "+st.getAge());
         System.out.println("Name : "+st.getName());
         
      }catch(Exception ex) {
         ex.printStackTrace();
      }
   }
}
