import java.util.*;
public class startServer
   {
  	Buffer b;										//Creation of buffer object


        public startServer(int bufferSize, int num_users, int num_servers, int elements) throws InterruptedException {												//Creates execution scenario between user and webservers on buffer
       ArrayList<user> userList = new ArrayList<>();
       ArrayList<webserver> serverList = new ArrayList<>();
        
        long startTime = System.currentTimeMillis();		
																
												//Instantiate all objects (webserver, users, buffer)
	b = new Buffer(bufferSize);
	for(int i = 0; i< num_users; i++)
    {
        userList.add(new user(i, elements, b));
    }
	for(int j = 0; j<num_servers; j++)
    {
        serverList.add(new webserver(j, elements, b));
    }
            for (user user : userList) {
                user.start();
            }
            for (webserver webserver : serverList) {
                webserver.start();
            }
	
												//Equally subdivide user inputted elements across all user objects

	System.out.println("-----------------------");
	
												//Outputs the total number of elements added/removed from user and webserver		

	System.out.println("-----------------------");
	//System.out.println("Buffer has " + X + " elements remaining");			//Check to see buffer if all elements produced from users have been successfully removed by webservers
	System.out.println("-----------------------");
												//Checks if all users and web servers successfully finished
            for (user user : userList) {
                user.join();
            }
            for (webserver webserver : serverList) {
                webserver.join();
            }
				
	long endTime = System.currentTimeMillis();
	System.out.println("-----------------------");
     	System.out.println("Program took " + (endTime - startTime) + " milliseconds to complete");		
	
    }
  
public static void main(String[] args) throws InterruptedException {
      Scanner myObj = new Scanner(System.in);
      int bufferSize;
      int num_users;
      int num_servers;
      int elements;
    System.out.println("Enter buffer capacity");					//Insert user inputted values for program execution
      bufferSize = myObj.nextInt();
    System.out.println("Enter number of users");
    num_users = myObj.nextInt();
    System.out.println("Enter number of servers");
    num_servers = myObj.nextInt();
    System.out.println("Enter total number of elements");
    elements = myObj.nextInt();
    startServer start = new startServer(bufferSize, num_users, num_servers, elements);
  }
}
