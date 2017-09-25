package com.bjarlung.order;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class OrderMain {

	public static void main(String[] args) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			
		Shiporder shiporder = setUpShiporder();
		
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(shiporder, System.out);
		
		
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Shiporder order1= (Shiporder) unmarshaller.unmarshal(new File("orderInput.xml"));
		//TODO toString 
		System.out.println("___________________________________________________");
		System.out.println(order1.getOrderperson());
	}

	//TODO optimize
	private static Shiporder setUpShiporder() {	

		Shiporder.Item item = new Shiporder.Item();
		item.setNote("Book");
		item.setPrice(new BigDecimal("349.5"));
		item.setQuantity(new BigInteger("2"));
		item.setTitle("Your item");
		
		Shiporder.Shipto shipTo = new Shiporder.Shipto();
		shipTo.setAddress("Gårdsvägen 3");
		shipTo.setCity("Stockholm");
		shipTo.setCountry("Stockholms län");
		shipTo.setName("Villa björnskär");		
		
		Shiporder order = new Shiporder();
		order.setOrderid("12345");
		order.setOrderperson("Janne Andersson");
		order.setShipto(shipTo);
		order.getItem().add(item);	
		
		return order;
	}

}
