package jaxb.org.iit.xml.practice6;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "book")
@XmlType(propOrder = { "id", "name", "date" })
public class Book {
    private Long id;
    private String name;
    private String author;
    private Date date;
 
    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }
 
    @XmlElement(name = "title")
    public void setName(String name) {
        this.name = name;
    }
 
    @XmlTransient
    public void setAuthor(String author) {
        this.author = author;
    }

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}
     
}