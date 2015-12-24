package om.edu.mec.smartHouseClient;

import java.io.*;
import java.net.*;

import om.edu.mec.smartHouseCommon.SmartHouseModel;

class ClientConnectionManager{

	private final static int REMOTE_PORT = 7242;

	private static Socket c1 =null;
	private static ObjectInputStream is = null;
	private static DataOutputStream os = null;
	private static BufferedReader stdin;
	private static String userInput = null;
	private static SmartHouseModel receivedModel = null;

	static SmartHouseModel connectToServer(){
		try {
			c1 = new Socket("localhost",REMOTE_PORT);
			is = new ObjectInputStream (c1.getInputStream());
			os = new DataOutputStream(c1.getOutputStream());
			stdin = new BufferedReader(new InputStreamReader (System.in));
		}
		catch(UnknownHostException e1){
			System.out.println("Unknown Host: "+e1);
		}
		catch(IOException e2){
			System.out.println("Error io: "+e2);
		}

		try {
			os.writeBytes("fire\n");
		}
		catch (IOException ex) {
			System.out.println("error writing to server."+ex);
		}

		try {
			receivedModel = (SmartHouseModel)is.readObject();
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			System.out.println(e);
		}
		try {
			is.close();
			os.close();
			c1.close();
		}
		catch(IOException x) {
			System.out.println("Error writing...."+x);
		}
		return receivedModel;
	}
}