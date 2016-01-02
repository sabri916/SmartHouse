package om.edu.mec.smartHouseClient;

import java.lang.Runnable;
import om.edu.mec.smartHouseCommon.SmartHouseModel;

public class AutoUpdate implements Runnable{
	//Class to update the model every five seconds
	//It is the same as clicking the refresh button every 5 seconds.
	//This is run on a separate thread as not to block program's progression
	//when in the infinite loop below.

	SmartHouseModel model;
	ClientConnectionManager myConnectionManager;

	AutoUpdate(SmartHouseModel m, ClientConnectionManager c){
		model = m;
		myConnectionManager = c;
	}

	public void run(){

		while(true){
			//the while loop will run forever until program terminates.
			System.out.println("autoupdate");

			//request model
			SmartHouseModel serverModel;
			try{
				serverModel = myConnectionManager.getServerModel();
			}catch(Exception e){
				System.out.println(e);
				//if no model is returned from network, use current local model
				//to avoid errors
				serverModel = model;
			}

			//updates local model
			model.setFireStatus(serverModel.getFireStatus());
			model.setLeakStatus(serverModel.getLeakStatus());
			model.setLightStatus(serverModel.getLightStatus());
			try{
				//waits for 5 seconds
				Thread.sleep(5000);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
	}
}