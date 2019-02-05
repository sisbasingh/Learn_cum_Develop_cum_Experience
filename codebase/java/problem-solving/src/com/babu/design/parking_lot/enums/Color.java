package com.babu.design.parking_lot.enums;

public enum Color {
	
	RED("RED"), BLUE("BLUE"), GREEN("GREEN");
	
	private String colorName;
	
	Color(String _colorName) {
		this.colorName = _colorName;
	}
	
	public static String getAllColors() {
		int i = 1;
		StringBuilder sbr = new StringBuilder("");
		for(Color color : Color.values()) {
			sbr.append(i++ + " " + color.colorName + "\n");
		}
		return sbr.toString();
	}
	
	public static Color getColorFromName(String colorName) {
		switch(colorName) {
		case "RED":
			return Color.RED;
		case "BLUE":
			return Color.BLUE;
		case "GREEN":
			return Color.GREEN;
		default: throw new IllegalArgumentException("Invalid color name provided");
		}
	}

}
