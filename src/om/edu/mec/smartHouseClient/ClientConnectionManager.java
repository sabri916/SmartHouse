package om.edu.mec.smartHouseClient;

import java.io.*;
import java.net.*;
import java.util.Observable;

import om.edu.mec.smartHouseCommon.SmartHouseModel;

class ClientConnectionManager extends Observable{

	private final int REMOTE_PORT = 7242;
	private String serverAddress = "127.0.0.1";

	private Socket clientSocket =null;
	private ObjectInputStream is = null;
	private DataOutputStream os = null;
	private SmartHouseModel receivedModel = null;

	SmartHouseModel getServerModel(){
		return getServerModel("1");
	}

	SmartHouseModel lightsOn(){
		return getServerModel("2");		
	}

	SmartHouseModel lightsOff(){
		return getServerModel("3");
	}



	private SmartHouseModel getServerModel(String message){
		try {
			clientSocket = new Socket(serverAddress,REMOTE_PORT);
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
			System.out.println("we sent: " + message);
			os.writeBytes(message + "\n");
			os.flush();
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

	void setServerAddress(String s){
		serverAddress = s;
		System.out.println(serverAddress);
		setChanged();
		notifyObservers();
	}

	String getServerAddress(){
		return serverAddress;
	}
}