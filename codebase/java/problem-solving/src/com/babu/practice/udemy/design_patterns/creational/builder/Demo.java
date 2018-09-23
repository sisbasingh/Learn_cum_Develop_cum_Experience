package com.babu.practice.udemy.design_patterns.creational.builder;

class Point
{
  public int x, y;
  
  public Point(int x, int y) {
	  this.x = x;
	  this.y = y;
  }
  
  public Point(Point other) {
	  this(other.x, other.y);
  }

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
  
}

class Line
{
  public Point start, end;
  
  public Line(Point start, Point end) {
	  this.start = start;
	  this.end = end;
  }
  
  public Line(Line other) {
	  this(new Point(other.start), new Point(other.end));
  }

  public Line deepCopy()
  {
    return new Line(this);
  }

	@Override
	public String toString() {
		return "Line [start=" + start + ", end=" + end + "]";
	}
  
}


public class Demo {
	public static void main(String args[]) {
		Line line1 = new Line(new Point(1, 2), new Point(3, 4));
		Line line2 = line1.deepCopy();
		line2.end = new Point(5, 6);
		System.out.println(line1);
		System.out.println(line2);
	}
}
