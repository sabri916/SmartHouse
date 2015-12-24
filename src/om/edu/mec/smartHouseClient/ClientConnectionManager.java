package om.edu.mec.smartHouseClient;

import java.io.*;
import java.net.*;

import om.edu.mec.smartHouseCommon.SmartHouseModel;

class ClientConnectionManager{

	private final static int REMOTE_PORT = 7242;

	private static Socket clientSocket =null;
	private static ObjectInputStream is = null;
	private static DataOutputStream os = null;
	private static SmartHouseModel receivedModel = null;

	static SmartHouseModel getServerModel(){
		try {
			clientSocket = new Socket("localhost",REMOTE_PORT);
			is = new ObjectInputStream (clientSocket.getInputStream());
			os = new DataOutputStream(clientSocket.getOutputStream());
		}
		catch(UnknownHostException e1){
			System.out.println("Unknown Host: "+e1);
		}
		catch(IOException e2){
			System.out.println("Error io: "+e2);
		}

		try {
			os.writeBytes("1");
		}
		catch (IOException e) {
			System.out.println("error writing to server."+e);
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
			clientSocket.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}
		return receivedModel;
	}
}