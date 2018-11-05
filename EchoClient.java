/*
Student 1: Heyzzen Flores
Student 2: Nathan Philip
Course: SYST44288
*/

import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) throws IOException {
    	//Declare Variables
        String hostName = args[0];
		
		//connect to server
        try (
            Socket echoSocket = new Socket(hostName,3000);
            PrintWriter out =
                new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in))
        ) {
        	//get user input and echo
            String userInput;
            while (((userInput = stdIn.readLine()) != null) || ((userInput = stdIn.readLine()).equals("."))) {
                out.println(userInput);
                if(userInput.equals(".")){
                	break;
                }
                else{
                	System.out.println("Server: " + in.readLine());
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Unknown Host: " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't connect to " + hostName);
            System.exit(1);
        } //end of try-catch
    }//end of main
}//end of class