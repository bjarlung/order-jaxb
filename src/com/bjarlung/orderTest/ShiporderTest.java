package com.bjarlung.orderTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.bjarlung.order.Shiporder;


public class ShiporderTest {
	
	Shiporder shiporder;

	@Before
	public void setUp() throws Exception {
		shiporder = new Shiporder();
	}

	@Test
	public void testSetAll() {
		shiporder.setAll("Mary", new Shiporder.Shipto(), "123");
		assertEquals("123", shiporder.getOrderid());
	}


}
