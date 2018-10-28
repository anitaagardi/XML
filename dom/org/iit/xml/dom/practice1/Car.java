package org.iit.xml.dom.practice1;

public class Car {
	
	private String manufacturer;
	private String color;
	private double price;
	
	public Car(String manufacturer, String color, double price) {
		this.manufacturer = manufacturer;
		this.color = color;
		this.price = price;
	}
	
	public Car() {
		this("", "", 0);
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return String.format("Car[manufacturer=%s, color=%s, price=%f]", manufacturer, color, price);
	}
	
}
