package com.babu.design.parking_lot.beans;

import com.babu.design.parking_lot.enums.Color;
import com.babu.design.parking_lot.enums.Size;

public class Car extends Vehicle {
	

	public Car(String regId, Color color, Size size) {
		super(regId, color, size);
	}
	
	public Car(String regId, Color color) {
		super(regId, color, Size.M);
	}

}
