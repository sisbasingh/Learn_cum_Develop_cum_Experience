package com.babu.practice.java.traps;

import java.util.Objects;

public class HashCodeAndEquals {
	
	private int id;
	private String name;
	
	public HashCodeAndEquals(int id, String n) {
		this.id = id;
		name = n;
	}
	
	@Override
	public int hashCode() {
		//return name.hashCode() + new Integer(id).hashCode();
		/**
		 * OR if Using JDK-7 or above use the following
		 */
		return Objects.hash(id, name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof HashCodeAndEquals))
			return false;
		if(this == obj)
			return true;
		HashCodeAndEquals temp = (HashCodeAndEquals)obj;
		return id == temp.id && name.equals(temp.name);
	}
	
	public static void main(String[] args) {
		HashCodeAndEquals obj1 = new HashCodeAndEquals(1, "abc");
		HashCodeAndEquals obj2 = new HashCodeAndEquals(2, "abc");
		
		HashCodeAndEquals obj3 = obj1;
		
		System.out.println("obj1 HashCode:" + obj1.hashCode());
		System.out.println("obj2 HashCode:" + obj2.hashCode());
		System.out.println("obj3 HashCode:" + obj3.hashCode());
		
		System.out.println(obj1==obj2);
		System.out.println(obj1 == obj3);
	}

}
