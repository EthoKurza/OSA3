/*
Student 1: Heyzzen Flores
Student 2: Nathan Philip
Course: SYST44288
*/

import java.net.*;
import java.io.*;


public class EchoServer {

    public static void main(String args[]) {

	//server and client sockets
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {
            serverSocket = new ServerSocket(3000); //port 3000
        } 

	catch (IOException e) {
            e.printStackTrace();
        }//end of try-catch

        while (true) {
            try {
                clientSocket = serverSocket.accept();	//server will accept clients with accept method
            } 
	    
	    catch (IOException e) {
                System.out.println("Error, failed to connect");
            	System.out.println(e.getMessage());
            }//end of try-catch

            // Server makes new threads for additional clients
            new NewThread(clientSocket).start();
        }//end of while
    }//end of main
}//end of class

class NewThread extends Thread {

    protected Socket clientSocket;

    public NewThread(Socket clientSocket) {

        this.clientSocket = clientSocket;
    }

    public void run() {

        InputStream in = null;
        BufferedReader bufReaderIn = null;
        DataOutputStream out = null;
	String inputLine;

        try {
            in = clientSocket.getInputStream();
            bufReaderIn = new BufferedReader(new InputStreamReader(in));
            out = new DataOutputStream(clientSocket.getOutputStream());
        } 

	catch (IOException e) {
            return;
        }//end of try-catch

        while (true) {
            try {
                inputLine = bufReaderIn.readLine();
                if ((inputLine == null)) {
                    clientSocket.close();
                    return;
                } else {
                    out.writeBytes(inputLine + "\n\r");
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }//end of try-catch
        }//end of while
    }//end of NewThread
}//end of class
