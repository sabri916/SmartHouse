package om.edu.mec.smartHouse;

import java.io.*;
import java.net.*;
import java.lang.Runnable;
import java.util.concurrent.*;

import om.edu.mec.smartHouseCommon.SmartHouseModel;

class ConnectionManager implements Runnable{
	final ExecutorService clientPool = Executors.newFixedThreadPool(5);
	private final static int SERVER_PORT = 7242;
	private ServerSocket myServerSocket = null;
	private String stringReceived;
	private BufferedReader is = null;
	private ObjectOutputStream os= null;
	private Socket clientSocket =null;
	private SmartHouseModel myModel;

	ConnectionManager(SmartHouseModel myModel){
		this.myModel = myModel;
	}

	public void run(){
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
				clientSocket = myServerSocket.accept();
				is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				os = new ObjectOutputStream(clientSocket.getOutputStream());
				clientPool.submit(new ClientProcessing());
			}catch(Exception ei){
				ei.printStackTrace();
			}
		}
	}

	private class ClientProcessing implements Runnable{

		public void run(){

			try{
				stringReceived = is.readLine();
				System.out.println("string Received: " +  stringReceived);
			}
			catch(Exception ic){
				ic.printStackTrace();
			}

			try{

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
				is.close();
				os.close();

			}catch(Exception e){
				System.out.println(e);
			}


		}
	}
}