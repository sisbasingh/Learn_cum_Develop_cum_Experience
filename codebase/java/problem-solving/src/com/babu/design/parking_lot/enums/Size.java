package com.babu.design.parking_lot.enums;

public enum Size {
	
	S("S"), M("M"), L("L"), XL("XL");
	
	private String sizeName;
	
	Size(String _sizeName) {
		this.sizeName = _sizeName;
	}
	
	public static String getAllSizes() {
		StringBuilder sbr = new StringBuilder("");
		int i = 1;
		for(Size size : Size.values()) {
			sbr.append(i++ + " " + size.sizeName + "\n");
		}
		return sbr.toString();
	}
	
	public static Size getSizeFromSizeName(String sizeName) {
		switch(sizeName) {
		case "S":
			return Size.S;
		case "M":
			return Size.M;
		case "L":
			return Size.L;
		case "XL":
			return Size.XL;
		default:
			throw new IllegalArgumentException("Invalid Size Name provided");
		}
	}

}
