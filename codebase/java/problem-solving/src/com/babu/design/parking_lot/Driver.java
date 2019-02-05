package com.babu.design.parking_lot;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.babu.design.parking_lot.beans.Car;
import com.babu.design.parking_lot.beans.Slot;
import com.babu.design.parking_lot.beans.Vehicle;
import com.babu.design.parking_lot.enums.Color;
import com.babu.design.parking_lot.enums.Size;

public class Driver {
	
	private static Size getSize() {
		int min = 0, max = 3;
		SecureRandom secRand = new SecureRandom();
		return Size.values()[secRand.nextInt(max-min+1) + min];
	}
	
	private static void printCommands() {
		System.out.println("Please select your choice from below:");
		System.out.println("1. Park a vehicle");
		System.out.println("2. Unpark a vehicle");
		System.out.println("3. Exit");
	}

	public static void main(String[] args) {
		int[] spots = new int[] {3, 7, 2, 6, 9};
		List<Slot> slots = new ArrayList<>();
		
		for(int i=0;i<spots.length;i++) {
			for(int j=1;j<=spots[i];j++) {
				slots.add(new Slot(j+1, i, getSize()));
			}
		}
		
		//Create a Parking with given slots
		Parking parking = new Parking(slots.toArray(new Slot[slots.size()]));
		Scanner in = new Scanner(System.in);
		System.out.println("================== Welcome to Bangalore Parking facility ====================");
		while(true) {
			printCommands();
			int command = Integer.parseInt(in.nextLine());
			if(command == 3) {
				break;
			} else if(command == 1) {
				System.out.println("Enter vehicle registration Id: \n");
				String regId = in.nextLine().trim();
				System.out.println("Enter vehicle color available colors are: \n" + Color.getAllColors());
				String colorName = in.nextLine().trim();
				Color color = Color.getColorFromName(colorName);
				System.out.println("Enter vehicle size, avilable sizes are: \n" + Size.getAllSizes());
				String sizeName = in.nextLine().trim();
				Size size = Size.getSizeFromSizeName(sizeName);
				Vehicle vehicle = new Car(regId, color, size);
				System.out.println(parking.park(vehicle));
			} else if(command == 2) {
				System.out.println("Please enter your booking id: \n");
				int bookingId = Integer.parseInt(in.nextLine());
				System.out.println(parking.unpark(bookingId));
			}
		}
		System.out.println("============= ThankYou for using parking facility, visit again :) ===================");
	}

}
