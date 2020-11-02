import java.util.*;

//This is an example class for using a primitive synchronization (semaphore, lock). Please note that you
//can ONLY put the synchronization keyword within these type of classes, and nowhere else within the program.

/**
 * Standard semaphore implementation
 */
public class semaphore
  {
    private int count;

    public semaphore(int init_val) {
      count = init_val;
    }

    public synchronized void P() throws InterruptedException {
      count = count - 1;
      if(count < 0) wait();
    }

    public synchronized void V()
    {
      count = count + 1;
      if(count <= 0) notify();
    }
  }

