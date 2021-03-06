class Reader extends Thread
{
  Calculator c;
  public Reader(Calculator calc)
  {
    c=calc;
  }
  public void run()
  {
    synchronized(c)
    {
      try{System.out.println("Waiting for calculation: ");
        c.wait();
      }
      catch(InterruptedException e)
      {
        System.out.println(e);
      }
      
    }
    System.out.println("Total is: "+c.total);
  }
  public static void main(String args[])
  {
    Calculator cal=new Calculator();
    new Reader(cal).start();
    new Reader(cal).start();
  }
}

class Calculator extends Thread
{
  int total;
  public void run()
  {
    synchronized(this)
    {
      for(int i=0;i<100;i++)
        total+=i;
      notifyAll();
    }
  }
}