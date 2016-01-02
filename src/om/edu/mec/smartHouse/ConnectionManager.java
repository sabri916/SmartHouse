package om.edu.mec.smartHouse;

import java.io.*;
import java.net.*;
import java.lang.Runnable;
import java.util.concurrent.*;

import om.edu.mec.smartHouseCommon.SmartHouseModel;

class ConnectionManager implements Runnable{
	//This class implements Runnable to enable it to run on a different Thread
	final ExecutorService clientPool = Executors.newFixedThreadPool(5);
	//ExecutorService handles the Scheduling of Threads
	//newFixedThreadPool(5) indicates that five threads can be run concurrently

	private final static int SERVER_PORT = 7242; // Port number of Server
	private ServerSocket myServerSocket = null; //Socket to listen to incoming connections
	private String stringReceived;
	private BufferedReader is = null;			//input Stream
	private ObjectOutputStream os= null;		//output Stream
	private Socket clientSocket =null;			//Socket to send back to clients
	private SmartHouseModel myModel;			//model is passed to this class to send to client

	ConnectionManager(SmartHouseModel myModel){
		//model is passed from main class
		this.myModel = myModel;
	}

	public void run(){
		// run() is required in all Runnables
		// this method is the entry point to the thread.
		startServer();
	}

	private void startServer(){

		try{
			myServerSocket = new ServerSocket(SERVER_PORT);
			System.out.println("Simple Server Started...");

		}
		catch(IOException e){
			System.out.println(e);
		}

		while(true){
			try{
				//accepts connections from client returning a socket which can be used for a response
				clientSocket = myServerSocket.accept();				

				//and instance of ClientProcessing, an inner class is created and submitted into the Thread Pool
				//This will run ClientProcessing on a different thread allowing the current thread to go back to 
				//accepting more connections.
				clientPool.submit(new ClientProcessing(clientSocket));
			}catch(Exception ei){
				ei.printStackTrace();
			}
		}
	}

	private class ClientProcessing implements Runnable{
		//Client Processing is responsible of receiving and replying back to the clients 
		//as opposed to creating more connections with more clients
		private Socket s;
		public ClientProcessing(Socket s){
			this.s = s;
		}

		public void run(){
			try{
				//input and output streams created from client socekt just created.
				is = new BufferedReader(new InputStreamReader(s.getInputStream()));
				os = new ObjectOutputStream(s.getOutputStream());

			}catch(Exception e){
				System.out.println(e);
			}

			try{
				//Listens to the message from Client and stores the received string to stringReceived
				stringReceived = is.readLine();
				System.out.println("string Received: " +  stringReceived);
			}
			catch(Exception ic){
				ic.printStackTrace();
			}

			try{
				//if "1" is received, send Model Object through output stream
				//if "2" is received, Switch the lights ON then  send Model Object through output stream
				//if "2" is received, Switch the lights OFF then  send Model Object through output stream

				if(stringReceived.equals("1")){
					os.writeObject(myModel);
				}
				else if(stringReceived.equals("2")){
					myModel.setLightStatus(true);
					os.writeObject(myModel);
				}else if(stringReceived.equals("3")){
					myModel.setLightStatus(false);
					System.out.println("lights off");
					os.writeObject(myModel);
				}
			}
			catch(IOException e){
				System.out.println(e);
			}

			try{
				//close streams and socket to save resources
				is.close();
				os.close();
				s.close();

			}catch(Exception e){
				System.out.println(e);
			}


		}
	}
}