package com.bjarlung.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bjarlung.order.Shiporder.Item;
import com.bjarlung.order.Shiporder.Shipto;

/**
 * Prompter that interacts with user, asking to specify order
 * @author Beatrice
 *
 */
public class Prompter {
	Scanner scanner;
	ObjectFactory objectFactory;
	//defaults to provide a value for non-optionals
	static final double DEFAULT_PRICE  = 349.5;
	static final int DEFAULT_AMOUNT = 1;

	static int orderNumber = 12348;
	
	/**
	 * Constructor for Prompter-class
	 */
	public Prompter() {
		scanner = new Scanner(System.in);
		objectFactory = new ObjectFactory();
	}
	/**
	 * Prompt user for number of items.
	 * Calls method to set up order
	 * @return Shiporder
	 */
	public Shiporder promptToOrder() {
		boolean isValid = false;
		String input = "";
		
		while(!isValid) {
			System.out.println("Please state how many items would you like to order: ");
			input = scanner.nextLine();
			isValid = validateInput(input);
		}

		Shiporder order = setUpOrder(Integer.parseInt(input));
		System.out.println(System.getProperty("line.separator"));
		return order;
	}

	private Shiporder setUpOrder(int numberOfItems) {
		Shiporder order = objectFactory.createShiporder();

		//setting up item list in order
		List<Shiporder.Item> itemList = promptForItems(numberOfItems);
		for(Shiporder.Item item: itemList) {
			order.getItem().add(item);
		}

		//setting an order person
		System.out.println("Your name: ");
		String orderperson = scanner.nextLine();

		//setting up shipto for order
		Shiporder.Shipto shipto = promptForShipto();

		order.setAll(orderperson, shipto, Integer.toString(orderNumber));
		orderNumber++;
		return order;
	}

	private Shipto promptForShipto() {
		Shiporder.Shipto shipto = objectFactory.createShiporderShipto();
		System.out.println("Address name: ");
		String name = scanner.nextLine();
		System.out.println("Street name: ");
		String street = scanner.nextLine();
		System.out.println("City: ");
		String city = scanner.nextLine();
		System.out.println("Country: ");
		String coutry = scanner.nextLine();
		shipto.setAll(name, street, city, coutry);
		return shipto;
	}

	private List<Item> promptForItems(int numberOfItems) {
		List<Shiporder.Item> itemList = new ArrayList<>();
		for(int i= 0; i<numberOfItems; i++) {
			Shiporder.Item item = objectFactory.createShiporderItem();
			System.out.println("What would you like to order? ");
			String title = scanner.nextLine();
			System.out.println("Add a note: ");
			String note = scanner.nextLine();
			item.setAll(title, note, DEFAULT_AMOUNT, DEFAULT_PRICE);
			itemList.add(item);
		}
		return itemList;
	}

	private boolean validateInput(String input){
		boolean isInt = false;
		try {
			Integer.parseInt(input);
			isInt = true;
		}catch(Exception e){
			System.out.println("Not a number, try again.");
		}
		return isInt;
	}

}

