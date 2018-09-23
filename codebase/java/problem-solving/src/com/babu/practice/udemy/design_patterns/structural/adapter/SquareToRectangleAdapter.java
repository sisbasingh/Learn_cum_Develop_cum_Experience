package com.babu.practice.udemy.design_patterns.structural.adapter;

class Square
{
  public int side;

  public Square(int side)
  {
    this.side = side;
  }
}

interface Rectangle
{
  int getWidth();
  int getHeight();

  default int getArea()
  {
    return getWidth() * getHeight();
  }
}

class SquareToRectangleAdapter implements Rectangle
{
  int width, height;
  
  public SquareToRectangleAdapter(Square square)
  {
    width = height = square.side;
  }
  
	@Override
	public int getWidth() {
		return width;
	}
	@Override
	public int getHeight() {
		return height;
	}
	
	public static void main(String args[]) {
		Square sqre = new Square(5);
		SquareToRectangleAdapter sqtorectadptr = new SquareToRectangleAdapter(sqre);
		System.out.println(sqtorectadptr.getWidth() + " " + sqtorectadptr.getHeight() + " " + sqtorectadptr.getArea());
	}
}

class Demo {
	public static void main(String args[]) {
		Square sqre = new Square(5);
		SquareToRectangleAdapter sqtorectadptr = new SquareToRectangleAdapter(sqre);
		System.out.println(sqtorectadptr.getWidth() + " " + sqtorectadptr.getHeight() + " " + sqtorectadptr.getArea());
	}
}