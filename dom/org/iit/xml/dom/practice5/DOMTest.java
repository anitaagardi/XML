/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.iit.xml.dom.practice5;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;


public class DOMTest {

    private String fileNev;

   
    private DOMTest(String fileNev) {
        this.fileNev = fileNev;;
    }

    
    public Document parse() throws Exception {
        DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        
        return parser.parse(new File(this.fileNev));
        
    }
    
    public void kiir(Node node, String tab){
        short tipus= node.getNodeType();
        switch (tipus) {
            case Node.DOCUMENT_NODE:
                System.out.println("<?xml version='1.0'?>");
                kiir(node.getFirstChild(), "");
                break;
                
            case Node.ELEMENT_NODE:
                System.out.print(tab + "<" + node.getNodeName());
                if (node.hasAttributes()) {
                    for(int i=0; i< node.getAttributes().getLength(); i++) {
                        kiir(node.getAttributes().item(i), " ");
                    }
                }
                System.out.println(">");
                for(int i=0; i<node.getChildNodes().getLength();i++){
                    kiir(node.getChildNodes().item(i), "\t");
                }
                System.out.println(tab + "</" + node.getNodeName() + ">");
                break;
                
            case Node.ATTRIBUTE_NODE:
                System.out.print(tab + node.getNodeName() + "="+node.getTextContent());
                break;
                
            case Node.TEXT_NODE:
                String s = node.getTextContent().trim();
                if (!s.isEmpty())
                System.out.println(tab + s);
                break;
        }
    }
    
    
    public static void main(String[] args) {
        String fileNev = "src/org/iit/xml/dom/practice5/autok-auto.xml";
        DOMTest test = new DOMTest(fileNev);
        try {
            Document doc = test.parse();
            test.kiir(doc,"");
            Node node = doc.getElementsByTagName("auto").item(0);
            Node uj = doc.createElement("proba");
            uj.setTextContent("ertek");
            node.appendChild(uj);
            test.kiir(doc,"");
            
            Node torolt = doc.removeChild(node);
            test.kiir(doc,"");
            
            doc.getDocumentElement().replaceChild(torolt,doc.getDocumentElement().getLastChild() );
            test.kiir(doc,"");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}