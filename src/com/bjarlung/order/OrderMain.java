package com.bjarlung.order;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;

/**
 * Main class with main method
 * 
 * @author Beatrice
 *
 */
public class OrderMain {

	private static XmlConverter xmlConverter;

	/**
	 * main method for order.
	 * Instantiates XmlConverter variable and calls methods marshal and unmarshal.
	 * 
	 * @param args
	 * @throws JAXBException
	 */
	public static void main(String[] args) throws JAXBException{

		xmlConverter = new XmlConverter();

		//Marshalling the Shiporder objects into XML
		List<Shiporder> shiporderList = setUpShiporderExample();
		xmlConverter.marshal(shiporderList);

		//Unmarshalling XML-file 
		xmlConverter.unmarshal("orderInput.xml");

		//Getting user input and marshalling to XML
		xmlConverter.marshal(getPromptList());		
	}

	/**
	 * Creates Prompter-object. Calls to prompt user for input.
	 * @return List<Shiporder>
	 */
	private static List<Shiporder> getPromptList() {
		Prompter prompter = new Prompter();
		List<Shiporder> promptedList = new ArrayList<>();
		promptedList.add(prompter.promptToOrder());	
		return promptedList;
	}


	/**
	 * Instantiation and providing values for Item, Shipto and Shiporder.
	 * Temporary hard coded objects, to be replaced by user interface.
	 * @return order List<Shiporder>
	 */
	private static List<Shiporder> setUpShiporderExample() {

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
