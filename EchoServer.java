/*
Student 1: Heyzzen Flores
Student 2: Nathan Philip
Course: SYST44288
*/

import java.net.*;
import java.io.*;


public class EchoServer2 {

    public static void main(String args[]) {

        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {
            serverSocket = new ServerSocket(3000);
        } 

	catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                clientSocket = serverSocket.accept();
            } 
	    
	    catch (IOException e) {
                System.out.println("Error, failed to connect");
            	System.out.println(e.getMessage());
            }

            // Server makes new thread for additional clients
            new NewThread(clientSocket).start();
        }
    }
}
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
        }

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
            }
        }
    }
}
