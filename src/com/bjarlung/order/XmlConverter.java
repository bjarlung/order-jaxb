package com.bjarlung.order;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * XmlConverter-class with methods to marshal and unmarshal
 * @author Beatrice
 *
 */
public class XmlConverter {
	private JAXBContext jaxbContext;
	private Unmarshaller unmarshaller;
	private Marshaller marshaller;

	/**
	 * Constructor for XmlConverter-class.
	 * Calls method to initiate instance variables
	 */
	public XmlConverter() {
		init();			
	}

	private void init() {
		try {
			jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			unmarshaller = jaxbContext.createUnmarshaller();
			marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		} catch (JAXBException e) {
			e.printStackTrace();
		}	
	}

	/**
	 * Instantiates unmarshaller.
	 * Unmarshalling referenced file to a Shiporder object containing Item and Shipto objects.
	 * @param fileAsString file path
	 * @throws JAXBException
	 */
	public void unmarshal(String fileAsString) throws JAXBException {	
		Shiporder order = (Shiporder) unmarshaller.unmarshal(new File(fileAsString));
		System.out.println(order.toString());
		System.out.println(System.getProperty("line.separator"));
	}

	/**
	 * Instantiates marshaller.
	 * Marshals the referenced list of Shiporders to XML and prints them to the console.
	 * @param shiporderList
	 * @throws JAXBException
	 */
	public void marshal(List<Shiporder> shiporderList) throws JAXBException {
		for(Shiporder shiporder: shiporderList) {
			System.out.println("Shiporder for: " + shiporder.orderperson);				
			marshaller.marshal(shiporder, System.out);
			System.out.println(System.getProperty("line.separator"));
		}
	}

}

