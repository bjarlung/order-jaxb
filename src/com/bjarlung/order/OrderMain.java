package com.bjarlung.order;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class OrderMain {

	public static void main(String[] args) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);

		List<Shiporder> shiporderList = setUpShiporders();

		//Marshalling the Shiporder objects into XML
		for(Shiporder shiporder: shiporderList) {
			System.out.println("Shiporder for: " + shiporder.orderperson);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(shiporder, System.out);
			System.out.println(System.getProperty("line.separator"));
		}

		//Unmarshalling XML-file 
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Shiporder order= (Shiporder) unmarshaller.unmarshal(new File("orderInput.xml"));
		System.out.println(order.toString());
	}

	//TODO prompt user 
	/**
	 * instantiation and providing values for Item, Shipto and Shiporder
	 * @return order Shiporder
	 */
	private static List<Shiporder> setUpShiporders() {

		List<Shiporder> orderList = new ArrayList<>();

		//setting up order 1
		Shiporder order1 = new Shiporder();
		Shiporder.Item itemOrder1 = new Shiporder.Item();
		itemOrder1.setAll("Book", "Novel", 2, 349.5);	

		Shiporder.Shipto shipToOrder1 = new Shiporder.Shipto();
		shipToOrder1.setAll("Villa bergskog", "Grodstigen 5", "Stockholm", "Sweden");

		order1.setAll("Janne Johnsson", shipToOrder1, "12345");
		order1.getItem().add(itemOrder1);	

		//setting up order 2
		Shiporder order2 = new Shiporder();
		Shiporder.Item item1Order2 = new Shiporder.Item();
		item1Order2.setAll("Shoes", "Walk the walk", 1, 800.0);	
		Shiporder.Item item2Order2 = new Shiporder.Item();
		item2Order2.setAll("Shoelace", "Rainbow", 3, 51.0);

		Shiporder.Shipto shipToOrder2 = new Shiporder.Shipto();
		shipToOrder2.setAll("Villa kulla", "Grodstigen 5", "Stockholm", "Sweden");

		order2.setAll("Greta Berg", shipToOrder2, "12347");
		order2.getItem().add(item1Order2);
		order2.getItem().add(item2Order2);

		//adding orders to order list
		orderList.add(order1);
		orderList.add(order2);
		return orderList;
	}

}
