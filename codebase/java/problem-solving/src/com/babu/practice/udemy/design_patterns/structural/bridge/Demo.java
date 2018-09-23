package com.babu.practice.udemy.design_patterns.structural.bridge;

interface Renderer
{
  public String whatToRenderAs();
}

class VectorRenderer implements Renderer {

	@Override
	public String whatToRenderAs() {
		return "lines";
	}
}

class RasterRenderer implements Renderer {

	@Override
	public String whatToRenderAs() {
		return "pixels";
	}
}

abstract class Shape
{
  public abstract String getName();
}

class Triangle extends Shape
{
	Renderer renderer;
	
	public Triangle(Renderer renderer) {
		this.renderer = renderer;
	}
	
  @Override
  public String getName()
  {
    return "Triangle";
  }
  
  @Override
	public String toString() {
		return String.format("Drawing %s as %s", getName(), renderer.whatToRenderAs());
	}
}

class Square extends Shape
{
	Renderer renderer;
	
	public Square(Renderer renderer) {
		this.renderer = renderer;
	}
  @Override
  public String getName()
  {
    return "Square";
  }
  
  @Override
	public String toString() {
	  return String.format("Drawing %s as %s", getName(), renderer.whatToRenderAs());
	}
}

/*class VectorSquare extends Square
{
  @Override
  public String toString()
  {
    return String.format("Drawing %s as lines", getName());
  }
}

class RasterSquare extends Square
{
  @Override
  public String toString()
  {
    return String.format("Drawing %s as pixels", getName());
  }
}*/

// imagine VectorTriangle and RasterTriangle are here too


public class Demo {
	public static void main(String args[]) {
		System.out.println(new Triangle(new RasterRenderer()));
		System.out.println(new Triangle(new VectorRenderer()));
		System.out.println(new Square(new RasterRenderer()));
		System.out.println(new Square(new VectorRenderer()));
	}
}
