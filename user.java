import java.util.*;

class user extends Thread
   {
       private int id;
       private int num_elements;
       public static Buffer buf;
       private int n = 1;
       semaphore mutex;
       semaphore space_avail;
       semaphore item_avail;

       /**
        *User thread which adds elements into buffer
        * @param i Id of user thread
        * @param el Number of elements the thread has to add
        * @param b Instance of the buffer class
        * @param mutex Semaphore for mutual exclusion inside critical section
        * @param space_avail Semaphore maintains number of empty spaces in buffer
        * @param item_avail Semaphore maintains number of items in buffer
        */
 
     public user(int i, int el, Buffer b, semaphore mutex, semaphore space_avail, semaphore item_avail)							//Created user will add a certain number of elements to the buffer.
     {
         id = i;
         num_elements = el;
         buf = b;
         this.mutex = mutex;
         this.space_avail = space_avail;
         this.item_avail = item_avail;
     }

     public void run() {
         while (num_elements > 0) {
             try {
                 space_avail.P(); //emptySpace--
                 mutex.P();
                 buf.add(n); //Add element to buffer
                 num_elements--;
                 mutex.V();
                 item_avail.V(); //itemsInBuffer++
             } catch (InterruptedException interruptedException) {
                 interruptedException.printStackTrace();
             }
         }
     }

       @Override
       public long getId() {
           return id;
       }

     }