package om.edu.mec.smartHouseClient;

import java.io.*;
import java.net.*;

class ClientConnectionManager{

	private final static int REMOTE_PORT = 7242;

	private static Socket c1 =null;
	private static BufferedReader is = null;
	private static DataOutputStream os = null;
	private static BufferedReader stdin = new BufferedReader(new InputStreamReader (System.in));
	private static String userInput = null;
	private static String output = null;

	static void connectToServer(){
		try {
			c1 = new Socket("localhost",REMOTE_PORT);
			is = new BufferedReader (new InputStreamReader(c1.getInputStream()));
			os = new DataOutputStream(c1.getOutputStream());
		}
		catch(UnknownHostException e1){
			System.out.println("Unknown Host: "+e1);
		}
		catch(IOException e2){
			System.out.println("Error io: "+e2);
		}

		try {
			System.out.print("Please input a keyword:");
			userInput = stdin.readLine();
			os.writeBytes(userInput+"\n");
		}
		catch (IOException ex) {
			System.out.println("error writing to server."+ex);
		}

		try {
			output = is.readLine();
			System.out.println("Got from server: "+output);
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
		try {
			is.close();
			os.close();
			c1.close();
		}
		catch(IOException x) {
			System.out.println("Error writing...."+x);
		}
	}
}