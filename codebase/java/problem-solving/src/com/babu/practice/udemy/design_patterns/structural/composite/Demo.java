package com.babu.practice.udemy.design_patterns.structural.composite;

import java.util.*;
import java.util.function.Consumer;

/**
 * Treating the single and aggregate as same
 * @author sisba01
 *
 */

interface ValueContainer extends Iterable<Integer> {
	default int sum() {
		int sum = 0;
		for(Integer value : this)
			sum += value;
		return sum;
	}
}

class SingleValue implements ValueContainer
{
  public int value;

// please leave this constructor as-is
  public SingleValue(int value)
  {
    this.value = value;
  }

	@Override
	public Iterator<Integer> iterator() {
		return Collections.singleton(value).iterator();
	}
	
	@Override
	public Spliterator<Integer> spliterator() {
		return Collections.singleton(value).spliterator();
	}
	
	@Override
	public void forEach(Consumer<? super Integer> action) {
		action.accept(value);
	}
}

class ManyValues extends ArrayList<Integer> implements ValueContainer
{
}


class MyList extends ArrayList<ValueContainer>
{
    // please leave this constructor as-is
  public MyList(Collection<? extends ValueContainer> c)
  {
    super(c);
  }
  
  public MyList() {
  }

  public int sum()
  {
    int sum = 0;
    for (ValueContainer vc : this) {
		sum += vc.sum();
	}
    return sum;
  }
}

public class Demo {
	public static void main(String args[]) {
		ValueContainer vc1 = new SingleValue(5);
		ManyValues mv = new ManyValues();
		mv.add(5);
		mv.add(5);
		mv.add(5);
		MyList myList = new MyList();
		myList.add(vc1);
		myList.add(mv);
		ManyValues mv2 = new ManyValues();
		mv2.add(10);
		mv2.add(10);
		SingleValue sv = new SingleValue(10);
		myList.add(mv2);
		myList.add(sv);
		System.out.println(myList.sum());
	}
}
