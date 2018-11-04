package jaxb.org.iit.xml.practice1;

import java.io.*;

import javax.xml.bind.JAXB;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
//https://www.tutorialspoint.com/java/xml/javax_xml_bind_jaxb_marshal_file.htm
public class JAXBDemo {
	private static String XML_PATH="src/jaxb/org/iit/xml/practice1/Student.xml";
   public static void main(String[] args) {
      // create student object
      Student st = new Student();
      st.setAge(13);
      st.setName("Rehman");
      st.setId(12);
      try {
         // create new file object
         File f = new File(XML_PATH);
         
         // saves student object to the file
         JAXB.marshal(st, f);
         
         // create document object from the student.xml
         DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
         DocumentBuilder docBuilder = dbf.newDocumentBuilder();
         Document document = docBuilder.parse(XML_PATH);
         
         // print the marshalled object to the stdout
         TransformerFactory tf = TransformerFactory.newInstance();
         Transformer t = tf.newTransformer();
         t.transform(new DOMSource(document), new StreamResult(System.out));

      }catch(Exception ex) {
         ex.printStackTrace();
      }
   }
}