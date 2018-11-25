package xslt.org.iit.xml.feladat4;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XSLTUtil {
	private static final String INPUT_XSL_FILE_PATH = "src/xslt/org/iit/xml/feladat1/feladat.xsl";
	private static final String INPUT_XML_FILE_PATH = "src/xslt/org/iit/xml/feladat1/konyvek";
	public static void main(String args[]){
		TransformerFactory transformerFactory=TransformerFactory.newInstance();
		StreamSource xslStream=new StreamSource(INPUT_XSL_FILE_PATH);
		
		StreamSource in=new StreamSource(INPUT_XML_FILE_PATH );
		//StreamResult out=new StreamResult("OrderOut.xml");
		StreamResult out=new StreamResult(System.out);
		try{
			Transformer transformer=transformerFactory.newTransformer(xslStream);
			transformer.transform(in, out);
		}catch(TransformerException e){
			e.printStackTrace();
		}
	}

}
