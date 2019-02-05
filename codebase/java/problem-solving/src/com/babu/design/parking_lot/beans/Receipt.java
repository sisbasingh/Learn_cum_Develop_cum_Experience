package com.babu.design.parking_lot.beans;

import java.util.Calendar;
import java.util.Date;

public class Receipt {
	
	private Booking booking;
	private Date endDate;
	private double amount;
	
	public Receipt(Booking booking, double amount) {
		super();
		this.booking = booking;
		this.endDate = Calendar.getInstance().getTime();
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return booking + ", End Date-Time: " + endDate + ", amount: " + amount + " INR";
	}

}
