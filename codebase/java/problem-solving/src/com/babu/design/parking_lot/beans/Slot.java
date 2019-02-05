package com.babu.design.parking_lot.beans;

import com.babu.design.parking_lot.enums.Size;

public class Slot {
	
	private int slotId;
	private int floorId;
	private Size size;
	
	public Slot(int slotId, int floorId, Size size) {
		super();
		this.slotId = slotId;
		this.floorId = floorId;
		this.size = size;
	}

	public int getSlotId() {
		return slotId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}

	public int getFloorId() {
		return floorId;
	}

	public void setFloorId(int floorId) {
		this.floorId = floorId;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}
	
	@Override
	public String toString() {
		return "Slot Id: " + slotId + ", Floor Id: " + floorId + ", size: " + size.toString();
	}
	
}
