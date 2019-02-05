package com.babu.design.parking_lot.beans;

import java.util.Calendar;
import java.util.Date;

public class Booking {
	private static int counter = 1;
	
	private int bookingId = 0;
	private Date startDate;
	private Slot slot;
	private Vehicle vehicle;
	
	public Booking(Slot slot, Vehicle vehicle) {
		super();
		bookingId = counter++;
		this.startDate = Calendar.getInstance().getTime();
		this.slot = slot;
		this.vehicle = vehicle;
	}
	
	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		Booking.counter = counter;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public String toString() {
		return "Booking Id: " + bookingId +  ", Date-Time: " + startDate + ", Slot: " + slot + ", Vehicle: " + vehicle;
	}

}
