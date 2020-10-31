import java.util.*;

public class Buffer							//Provides data and operations onto the fixed-length buffer
  {     									
	private LinkedList<Object> buf_list;
	private int elements;
	private int buf_size;
	private int i;
	semaphore mutex = new semaphore(1);
	semaphore mutex2 = new semaphore(1);

	
     public Buffer(int n)						//Buffer creation, with n indicating the maximum capacity
	{
	   buf_list = new LinkedList<Object>();
	   elements = 0;
	   buf_size = n;
        }

	  public void add(int n) throws InterruptedException
	  {
	  	mutex.P();
	  	Thread t = Thread.currentThread();
	  	System.out.println("User "+t.getId()+" adds an element "+i+"/"+buf_size);
	  	i++;
	  	mutex.V();
	  }
	  public void remove(int n) throws InterruptedException
	  {
	  	mutex2.P();
	  	Thread t = Thread.currentThread();
	  	System.out.println("User "+t.getId()+" removed an element "+i+"/"+buf_size);
	  	i--;
	  	mutex2.V();

	  }
  }
