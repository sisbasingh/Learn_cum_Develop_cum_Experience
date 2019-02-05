package com.babu.design.parking_lot.beans;

import java.util.Objects;

import com.babu.design.parking_lot.enums.Color;
import com.babu.design.parking_lot.enums.Size;

public abstract class Vehicle {
	private String regId;
	private Color color;
	private Size size;
	
	public Vehicle(String regId, Color color, Size size) {
		super();
		this.regId = regId;
		this.color = color;
		this.size = size;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(regId, color, size);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Vehicle)) {
			return false;
		}
		Vehicle v = (Vehicle) obj;
		if(this == v) {
			return true;
		}
		return regId == v.getRegId() && color == v.getColor() && size == v.getSize();
	}
	
	@Override
	public String toString() {
		return "Reg Id: " + regId + ", Color: " + color.toString() + ", Size: " + size.toString();
	}
	
}
