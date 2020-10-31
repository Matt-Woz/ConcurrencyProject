import java.util.*;

class user extends Thread
   {
       private int id;
       private int num_elements;
       public static Buffer buf;
 
     public user(int i, int el, Buffer b)							//Created user will add a certain number of elements to the buffer.
     {
         id = i;
         num_elements = el;
         buf = b;
     }

     public void run()
     {
         int n = 1;
         while (num_elements > 0)
         {
             try {
                 buf.add(n);
             } catch (InterruptedException e) { e.printStackTrace();}
             num_elements--;
             }
         }

       @Override
       public long getId() {
           return id;
       }


     }