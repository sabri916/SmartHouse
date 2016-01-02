package om.edu.mec.smartHouseClient;

import java.io.*;
import java.net.*;
import java.util.Observable;

import om.edu.mec.smartHouseCommon.SmartHouseModel;

class ClientConnectionManager extends Observable{
	//implements Observable because it holds the address to the server
	//which appears in the user interface. 
	//if the server address changed, the Observer, the view, needs to be notified

	private final int REMOTE_PORT = 7242;			//port number
	private String serverAddress = "127.0.0.1";		//server address, local machin by default

	private Socket clientSocket =null;
	private ObjectInputStream is = null;
	private DataOutputStream os = null;
	private SmartHouseModel receivedModel = null;

	SmartHouseModel getServerModel(){
		//requests model of the system from server
		return getServerModel("1");
	}

	SmartHouseModel lightsOn(){
		//requests switching on of lights then requests model of the system from server
		return getServerModel("2");		
	}

	SmartHouseModel lightsOff(){
		//requests switching off of lights then requests model of the system from server
		return getServerModel("3");
	}



	private SmartHouseModel getServerModel(String message){
		// the message string is passed on from the above methods
		try {
			//creation of connection socekt to server
			clientSocket = new Socket(serverAddress,REMOTE_PORT);
			//creation of inpout and output streams to enable sending and receiving of data
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
			//sending message to server
			System.out.println("we sent: " + message);
			os.writeBytes(message + "\n");
			os.flush();
		}
		catch (IOException e) {
			System.out.println("error writing to server."+e);
		}

		try {
			//receiving model from Server
			receivedModel = (SmartHouseModel)is.readObject();
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			System.out.println(e);
		}
		try {
			//close to reclaim resources
			is.close();
			os.close();
			clientSocket.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}
		//return the model that is received from server
		return receivedModel;
	}


	//setter and getter of the server address String
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