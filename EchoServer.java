/*
Student 1: Heyzzen Flores
Student 2: Nathan Philip
Course: SYST44288
*/

import java.net.*;
import java.io.*;


public class EchoServer2 {
public static void main(String args[]){

    ServerSocket serverSocket =null;
    Socket clientSocket =null;
    
    try{

        serverSocket = new ServerSocket(3000); 

    } catch(IOException e){

   	 System.out.println("Error, failed to connect");
         System.out.println(e.getMessage());

    }//end of try-catch

    while (true){

        try{
            clientSocket = serverSocket.accept();
		}catch(Exception e){

			System.out.println("Error, failed to connect");
			System.out.println(e.getMessage());

		}//end of try-catch

		new EchoThread (clientSocket).start();

	}//end of while

    
    }//end of main
}//end of class



public class EchoThread extends Thread {

    protected Socket socket;

    public EchoThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
        InputStream inp = null;
        BufferedReader brinp = null;
        DataOutputStream out = null;
        try {
            inp = socket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }//end of try catch
        String line;
        while (true) {
            try {
                line = brinp.readLine();
                if ((line == null) || line.equalsIgnoreCase("QUIT")) {
                    socket.close();
                    return;
                } else {
                    out.writeBytes(line + "\n\r");
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }//end of try-catch
        }//end of while
    }//end of run
}//end of class

