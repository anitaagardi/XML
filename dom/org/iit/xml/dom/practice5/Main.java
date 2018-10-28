/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.iit.xml.dom.practice5;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Main {


    public static void main(String[] args) {
         try {
             DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
             DocumentBuilder builder = fact.newDocumentBuilder();
             File file = new File("src/org/iit/xml/dom/practice5/autok-auto.xml");
             Document doc = builder.parse(file);
             Element gyoker = doc.getDocumentElement();
             int db=0, atlag=0;
             NodeList autok = gyoker.getChildNodes();
             for (int i=0;i<autok.getLength(); i++) {
                 Node auto = autok.item(i);
                 if(auto.getNodeType() == Node.ELEMENT_NODE){
                     Element elem = (Element)auto;
                    String tipus = elem.getElementsByTagName("tipus").item(0).getFirstChild().getNodeValue();
                    String ev = elem.getElementsByTagName("ev").item(0).getFirstChild().getNodeValue();
                    String ar = elem.getElementsByTagName("ar").item(0).getFirstChild().getNodeValue();
                    int arInt = Integer.parseInt(ar);
                    if(tipus.toUpperCase().equals("FIAT") && ev.equals("2005")) {
                        atlag += arInt;
                        db++;
                    }
                    String rsz = auto.getAttributes().getNamedItem("rsz").getTextContent();
                    rsz = elem.getAttribute("rsz");
                    System.out.println("Rsz: " + rsz);
                 }
              }
             System.out.println("Talalt elemek szama:" + db + " Atlag ar:" + atlag/db);
             System.out.println(gyoker.getNodeName());
           } catch (Exception e) {
             System.out.println(e);
         }
    
    }
    

}
