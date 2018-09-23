package com.babu.practice.udemy.design_patterns.behavioral.null_object;

import java.lang.reflect.Proxy;


interface DLog
{
  void info(String msg);
  void warn(String msg);
}

class ConsoleLog1 implements DLog
{

  @Override
  public void info(String msg)
  {
    System.out.println(msg);
  }

  @Override
  public void warn(String msg)
  {
    System.out.println("WARNING: " + msg);
  }
}

class BankAccount1
{
  private DLog log;
  private int balance;

  public BankAccount1(DLog log)
  {
    this.log = log;
  }

  public void deposit(int amount)
  {
    balance += amount;

    // check for null everywhere?
    if (log != null)
    {
      log.info("Deposited " + amount
        + ", balance is now " + balance);
    }
  }

  public void withdraw(int amount)
  {
    if (balance >= amount)
    {
      balance -= amount;
      if (log != null)
      {
        log.info("Withdrew " + amount
          + ", we have " + balance + " left");
      }
    }
    else {
      if (log != null)
      {
        log.warn("Could not withdraw "
          + amount + " because balance is only " + balance);
      }
    }
  }
}

final class NullLog1 implements DLog
{
  @Override
  public void info(String msg)
  {

  }

  @Override
  public void warn(String msg)
  {

  }
}

class DynamicNullObjectDemo
{
  @SuppressWarnings("unchecked")
  public static <T> T noOp(Class<T> itf)
  {
    return (T) Proxy.newProxyInstance(
      itf.getClassLoader(),
      new Class<?>[]{itf},
      (proxy, method, args) ->
      {
        if (method.getReturnType().equals(Void.TYPE))
          return null;
        else
          return method.getReturnType().getConstructor().newInstance();
      });
  }

  public static void main(String[] args)
  {
    //ConsoleLog log = new ConsoleLog();
    //NullLog log = new NullLog();

    DLog log = noOp(DLog.class);

    BankAccount1 ba = new BankAccount1(log);
    ba.deposit(100);
    ba.withdraw(200);
  }
}