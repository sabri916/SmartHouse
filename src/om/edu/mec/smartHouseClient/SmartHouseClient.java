package om.edu.mec.smartHouseClient;

import java.io.*;
import java.net.*;

public class SmartHouseClient{
	

	public final static int REMOTE_PORT = 7242;
	public static void main(String argv[]){
		Socket c1 =null;
		BufferedReader is = null;
		DataOutputStream os = null;
		BufferedReader stdin = new BufferedReader(new InputStreamReader (System.in));
		String userInput = null;
		String output = null;

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