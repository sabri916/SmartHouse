package om.edu.mec.smartHouse;

import java.io.*;
import java.net.*;

import om.edu.mec.smartHouseCommon.SmartHouseModel;

class ConnectionManager{
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

	void startServer(){

		try{
			myServerSocket = new ServerSocket(SERVER_PORT);
			System.out.println("Simple Server Started...");

		}
		catch(IOException e){
			System.out.println(e);
		}

		while(true){
			System.out.println("new loop");
			try{
				System.out.println("yellow");
				clientSocket = myServerSocket.accept();
				System.out.println("blue");
				is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				os = new ObjectOutputStream(clientSocket.getOutputStream());
			}catch(Exception ei){
				ei.printStackTrace();
			}

			try{
				System.out.println("readingline");
				stringReceived = is.readLine();
				System.out.println("readinglinefinished");

				if(stringReceived.equals("fire")){
					os.writeObject(myModel);
				}
			
				System.out.println("We received: "+stringReceived);
			}
			catch(Exception ic){
				ic.printStackTrace();
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