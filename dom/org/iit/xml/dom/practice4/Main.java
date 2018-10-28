package org.iit.xml.dom.practice4;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {

    public static void main(String[] args) {
        
        DocumentBuilderFactory fact= DocumentBuilderFactory.newInstance();
        
        try{
            //fact.setValidating(true);
            DocumentBuilder builder = fact.newDocumentBuilder();
            File file = new File("src/org/iit/xml/dom/practice4/autok_teszt.xml");
            Document doc = builder.parse(file);
            Element gyoker = doc.getDocumentElement();
            System.out.println(gyoker.getNodeName());
            
            NodeList gyerekek = gyoker.getChildNodes();
            
            int atlagAr = 0;
            int darab = 0;
            int legdragabb = 0;
            
            for(int i = 0; i < gyerekek.getLength(); i++)
            {
                Node elem = gyerekek.item(i);
                if (elem.getNodeType() == Node.ELEMENT_NODE)  
                    //elem instanceof Element
                {
                    Element auto = (Element)elem;
                    String tipus = auto.getElementsByTagName("tipus").item(0).getFirstChild().getTextContent();
                    String arString = auto.getElementsByTagName("ar").item(0).getFirstChild().getTextContent();
                    String evjarat = auto.getElementsByTagName("evjarat").item(0).getFirstChild().getTextContent();
                    int ar = Integer.parseInt(arString);
                    
                    
                    if(tipus.equalsIgnoreCase("Audi") && Integer.parseInt(evjarat) <= 2005){
                        darab++;
                        atlagAr += ar;    
                    }
                    
                }
            }
            atlagAr /= darab;
            System.out.println("2005-nel regebbi Audi-k keresese... ");
            System.out.println("Talált autók száma: " + darab + " \t Átlagáruk: " + atlagAr );
            kiir("", doc.getDocumentElement());
            legdragabb(doc);
            
            Node node = doc.getElementsByTagName("auto").item(0);
            Node uj = doc.createElement("proba");
            uj.setTextContent("ertek");
            node.appendChild(uj);
            kiir("", doc.getDocumentElement());
            
            Node torolt = doc.getDocumentElement().removeChild(node);
            kiir("",doc.getDocumentElement());
            
            doc.getDocumentElement().replaceChild(torolt, doc.getDocumentElement().getLastChild());
            kiir("",doc.getDocumentElement());
                    
                    
        }catch(Exception e){
            System.out.println(e);
        }
           
    }
    
    public static void legdragabb(Document doc){
        int max = 0, index=0;
        
        Node elem;
        NodeList nl = doc.getElementsByTagName("ar");
     
        for(int i = 0; i < nl.getLength(); i++){
            elem = nl.item(i);
            int ar = Integer.parseInt(elem.getTextContent());
            if(ar > max){
                max = ar;
                index = i;
            }
        }
        
        System.out.println("A legdragabb auto adatai:");elem = nl.item(index);
        kiir("",elem.getParentNode());
    }
    
    public static void kiir(String tab, Node node){
        short tipus = node.getNodeType();
        
        switch (tipus){
            case Node.ELEMENT_NODE:
                System.out.print(tab + "<" + node.getNodeName());
                if (node.getAttributes().getLength() > 0)
                {
                    for(int i = 0; i < node.getAttributes().getLength(); i++)
                    {
                        kiir(" ", node.getAttributes().item(i));
                    }
                }
                System.out.println(">");
                for(int i = 0; i < node.getChildNodes().getLength(); i++){
                    kiir(tab + "\t", node.getChildNodes().item(i));
                }
                System.out.println(tab + "</" + node.getNodeName() + ">");
                break;
                
            case Node.ATTRIBUTE_NODE:
                System.out.print(tab + node.getNodeName() + "=" + node.getTextContent().trim());
                break;
                
            case Node.TEXT_NODE:
                String str = node.getTextContent().trim();
                if(str.length() > 0)
                System.out.println(tab + str);
                break;
               
        }
    }

}
