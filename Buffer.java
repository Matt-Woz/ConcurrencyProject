import java.util.*;

public class Buffer							//Provides data and operations onto the fixed-length buffer
  {     									
	private LinkedList<Object> buf_list;
	private int elements;
	private int buf_size;

	  /**
	   * Creates buffer
	   * @param n Size of buffer
	   */
     public Buffer(int n)						//Buffer creation, with n indicating the maximum capacity
	{
	   buf_list = new LinkedList<Object>();
	   elements = 0;
	   buf_size = n;
        }

	  /**
	   * Adds element into buffer
	   * @param n Element
	   * @throws InterruptedException
	   */
	  public void add(int n) throws InterruptedException {
     //	Thread.sleep(1000);
	  	Thread t = Thread.currentThread();
	  	elements++;
	  	System.out.println("User "+t.getId()+" adds an element "+elements+"/"+buf_size);
	  	checkFull();
	  }

	  /**
	   * Removes element from buffer
	   * @param n Element
	   * @throws InterruptedException
	   */
	  public void remove(int n) throws InterruptedException {
	  	Thread t = Thread.currentThread();
	  	elements--;
	  	System.out.println("Serv "+t.getId()+" removed an element "+elements+"/"+buf_size);
	  	checkEmpty();
	  }
	  /**
	   * Checks if buffer is empty
	   */
	  public void checkEmpty()
	  {
		  if(elements == 0) {
			  System.out.println("Buffer empty");
		  }
	  }

	  /**
	   * Checks if buffer is full
	   */
	  public void checkFull()
	  {
	  	if(elements == buf_size){
			System.out.println("Buffer full");
		}
	  }
	  /**
	   * Element accessor method
	   * @return Amount of elements in buffer at current moment
	   */
	  public int checkBuffer()
	  {
	  	return elements;
	  }
  }
