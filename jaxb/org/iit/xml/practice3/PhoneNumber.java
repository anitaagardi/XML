package jaxb.org.iit.xml.practice3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
 //http://blog.bdoughan.com/2011/06/jaxb-and-complex-types-with-simple.html
@XmlRootElement(name="phone-number")
public class PhoneNumber {
 
    private String type;
    private String number;
 
    @XmlAttribute
    public String getType() {
        return type;
    }
 
    public void setType(String type) {
        this.type = type;
    }
 
    @XmlValue
    public String getNumber() {
        return number;
    }
 
    public void setNumber(String number) {
        this.number = number;
    }
 
}
