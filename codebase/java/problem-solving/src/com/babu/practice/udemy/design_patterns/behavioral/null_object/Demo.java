package com.babu.practice.udemy.design_patterns.behavioral.null_object;

interface Log1
{
  // max # of elements in the log
  int getRecordLimit();

  // number of elements already in the log
  int getRecordCount();

  // expected to increment record count
  void logInfo(String message);
}

class Account
{
  private Log1 log;

  public Account(Log1 log)
  {
    this.log = log;
  }

  public void someOperation() throws Exception
  {
    int c = log.getRecordCount();
    log.logInfo("Performing an operation");
    if (c+1 != log.getRecordCount())
      throw new Exception();
    if (log.getRecordCount() >= log.getRecordLimit())
      throw new Exception();
  }
}

class NullLog2 implements Log1
{
	int count;
	
	@Override
	public int getRecordLimit() {
		return count+1;
	}

	@Override
	public int getRecordCount() {
		return count;
	}

	@Override
	public void logInfo(String message) {
		count++;
	}
}

public class Demo {

}
