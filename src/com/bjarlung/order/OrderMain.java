package com.bjarlung.order;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Main class with main method.
 * 
 * @author Beatrice
 *
 */
public class OrderMain {

	private static JAXBContext jaxbContext;

	/**
	 * main method for order.
	 * Instantiates JAXBContext variable and calls methods marshal and unmarshal.
	 * @param args
	 * @throws JAXBException
	 */
	public static void main(String[] args) throws JAXBException{

		jaxbContext = JAXBContext.newInstance(ObjectFactory.class);

		List<Shiporder> shiporderList = setUpShiporders();

		//Marshalling the Shiporder objects into XML
		marshal(shiporderList);

		//Unmarshalling XML-file 
		unmarshal("orderInput.xml");

	}
	/**
	 * Instantiates unmarshaller.
	 * Unmarshalling referenced file to a Shiporder object containing Item and Shipto objects.
	 * @param fileAsString file path
	 * @throws JAXBException
	 */
	private static void unmarshal(String fileAsString) throws JAXBException {	
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Shiporder order = (Shiporder) unmarshaller.unmarshal(new File(fileAsString));
		System.out.println(order.toString());

	}
	/**
	 * Instantiates marshaller.
	 * Marshals the referenced list of Shiporders to XML and prints them to the console.
	 * @param shiporderList
	 * @throws JAXBException
	 */
	private static void marshal(List<Shiporder> shiporderList) throws JAXBException {
		for(Shiporder shiporder: shiporderList) {
			System.out.println("Shiporder for: " + shiporder.orderperson);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(shiporder, System.out);
			System.out.println(System.getProperty("line.separator"));
		}
	}

	/**
	 * Instantiation and providing values for Item, Shipto and Shiporder.
	 * Temporary hard coded objects. To be replaced by prompter or user interface.
	 * @return order List<Shiporder>
	 */
	private static List<Shiporder> setUpShiporders() {

		List<Shiporder> orderList = new ArrayList<>();
		ObjectFactory objectFactory = new ObjectFactory();

		//setting up order 1
		Shiporder order1 = objectFactory.createShiporder();
		Shiporder.Item itemOrder1 = objectFactory.createShiporderItem();
		itemOrder1.setAll("Book", "Novel", 2, 349.5);	

		Shiporder.Shipto shiptoOrder1 = objectFactory.createShiporderShipto();
		shiptoOrder1.setAll("Villa bergskog", "Grodstigen 5", "Stockholm", "Sweden");

		order1.setAll("Janne Johnsson", shiptoOrder1, "12345");
		order1.getItem().add(itemOrder1);	

		//setting up order 2
		Shiporder order2 = objectFactory.createShiporder();
		Shiporder.Item item1Order2 = objectFactory.createShiporderItem();
		item1Order2.setAll("Shoes", "Walk the walk", 1, 800.0);	
		Shiporder.Item item2Order2 = objectFactory.createShiporderItem();
		item2Order2.setAll("Shoelace", "Rainbow", 3, 51.0);

		Shiporder.Shipto shipToOrder2 = objectFactory.createShiporderShipto();
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
