import java.util.*;

/**
 * Gets user input for different values of buffer size, number of users/servers and number of elements, and starts the server
 */
public class Main {
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
