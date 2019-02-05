package com.babu.design.parking_lot;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.babu.design.parking_lot.beans.Booking;
import com.babu.design.parking_lot.beans.Receipt;
import com.babu.design.parking_lot.beans.Slot;
import com.babu.design.parking_lot.beans.Vehicle;
import com.babu.design.parking_lot.enums.Size;

public class Parking {
	//Currently commenting this, can be used for extension purposes
	/*private int id;
	private String name;
	private String address;
	private int pinCode;*/
	
	private Map<Size, List<Slot>> freeSlots;
	private Map<Vehicle, Slot> bookings;
	
	/**
	 * following map are for temporary usage to avoid taking objects from console
	 */
	private Map<Integer, Booking> bookingsCache;
	
	public Parking(Slot[] slots) {
		freeSlots = new HashMap<>();
		bookings = new HashMap<>();
		
		for(Size size : Size.values()) {
			freeSlots.put(size, new ArrayList<Slot>());
		}
		
		for(Slot slot : slots) {
			freeSlots.get(slot.getSize()).add(slot);
		}
		
		bookingsCache = new HashMap<>();
	}
	
	public String park(Vehicle v) {
		if(v == null) {
			return "Invalid Vehicle passed as argument";
		}
		if(bookings.containsKey(v)) {
			//Already booked
			return "Vehicle is already parked";
		}
		Slot availSlot;
		//Check if same size slot is available
		if(freeSlots.get(v.getSize()).size() > 0) {
			availSlot = freeSlots.get(v.getSize()).remove(0);
		} else {
			//Find just next greate slot for efficiency
			Size curSize = v.getSize();
			for(Size size : freeSlots.keySet()) {
				if(!freeSlots.get(size).isEmpty() && size.compareTo(v.getSize()) > 0 && size.compareTo(curSize) < 0) {
					curSize = size;
				}
			}
			availSlot = freeSlots.get(curSize).remove(0);
		}
		
		if(availSlot == null) {
			//slot not available
			return "No slots available for the vehicle";
		}
		
		bookings.put(v, availSlot);
		Booking booking = new Booking(availSlot, v);
		bookingsCache.put(booking.getBookingId(), booking);
		return booking.toString();
	}
	
	private double getFare() {
		int min = 100, max = 1000;
		SecureRandom secRandom = new SecureRandom();
		return secRandom.nextInt(max-min+1) + min;
	}
	
	public String unpark(int bookingId) {
		return unpark(bookingsCache.get(bookingId));
	}
	
	public String unpark(Booking booking) {
		if(booking == null) {
			return "Invalid booking object is passed as argument";
		}
		if(!bookings.containsKey(booking.getVehicle())) {
			return "Vehicle: " + booking.getVehicle() + " not found";
		}
		
		Slot slot = bookings.remove(booking.getVehicle());
		bookingsCache.remove(booking.getBookingId());
		//Add to free slots
		freeSlots.get(slot.getSize()).add(slot);
		return new Receipt(booking, getFare()).toString();
	}

}
