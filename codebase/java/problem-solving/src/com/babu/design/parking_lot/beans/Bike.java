package com.babu.design.parking_lot.beans;

import com.babu.design.parking_lot.enums.Color;
import com.babu.design.parking_lot.enums.Size;

public class Bike extends Vehicle {

	public Bike(String regId, Color color, Size size) {
		super(regId, color, size);
	}
	
	public Bike(String regId, Color color) {
		super(regId, color, Size.S);
	}

}
