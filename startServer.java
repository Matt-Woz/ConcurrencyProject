import java.util.*;
public class startServer
   {
  	Buffer b; //Creates buffer

       /**
        * Initiates buffer, users and servers
        * @param bufferSize Size of the buffer specified by user
        * @param num_users Amount of users adding to buffer
        * @param num_servers Amount of servers removing from buffer
        * @param elements Amount of elements to be processed
        * @throws InterruptedException
        */
       public startServer(int bufferSize, int num_users, int num_servers, int elements) throws InterruptedException {
       ArrayList<user> userList = new ArrayList<>();
       ArrayList<webserver> serverList = new ArrayList<>();
       //3 bounded buffer semaphores
       semaphore mutex = new semaphore(1);
       semaphore space_avail = new semaphore(bufferSize);
       semaphore item_avail = new semaphore(0);
       //Calls method to calculate how many elements each user and server will process
       int[] elementsPerUser = shareObjects(elements, num_users);
       int[]  elementsPerServer = shareObjects(elements, num_servers);
       long startTime = System.currentTimeMillis();
       b = new Buffer(bufferSize); //Creates buffer object
       for(int i = 0; i< num_users; i++)
       {
          userList.add(new user(i, elementsPerUser[i], b, mutex, space_avail, item_avail)); //Creates users and puts them in a list
       }
	   for(int j = 0; j<num_servers; j++)
	   {
	      serverList.add(new webserver(j, elementsPerServer[j], b, mutex, space_avail, item_avail)); //Creates servers and puts them in a list
	   }
       for (user user : userList)
       {
          user.start(); //Run user threads
       }
       for (webserver webserver : serverList)
       {
          webserver.start(); //Run server threads
       }
	   //Wait for users and servers to finish
	   for (user user : userList)
	   {
	       user.join();
	   }
	   for (webserver webserver : serverList)
	   {
	       webserver.join();
	   }
	   long endTime = System.currentTimeMillis();
           System.out.println("-----------------------");
           System.out.println("-----------------------");
           System.out.println("-----------------------");
	   for(int i = 0; i< num_users; i++)
	   {
	       System.out.println("User " + userList.get(i).getId() + " created a total of " + elementsPerUser[i] + " elements\n");
	   }
	   for(int i = 0; i < num_servers; i++)
	   {
	       System.out.println("Consumer "+serverList.get(i).getId() + " consumed a total of "+elementsPerServer[i] + " elements\n");
	   }
	   System.out.println("-----------------------");
	   System.out.println("Buffer has "+b.checkBuffer() +" elements remaining");
	   System.out.println("-----------------------");
	   System.out.println("Program took " + (endTime - startTime) + " milliseconds to complete");
    }

       /**
        * This method calculates how many elements each user or server should take to spread them evenly
        * @param num_threads Number of threads i.e. users or servers
        * @param elements Number of elements to be processed
        * @return Share of elements for each thread
        */
    public static int[] shareObjects(int elements, int num_threads)
    {
        int remainder = elements % num_threads;
        int[] elementsEach = new int[num_threads]; //Creates array with number of users/servers
            while(elements > remainder)
            {
                for (int i = 0; i < num_threads; i++) //For all the users/servers
                {
                    elementsEach[i]++; //Increment element each thread processes
                }
                elements = elements - num_threads;
            }
        for(int i = 0; i < remainder; i++) //For odd numbers of elements/threads
        {
            elementsEach[i]++;
        }
        return elementsEach;
    }
}
