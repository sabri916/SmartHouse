package om.edu.mec.smartHouseClient;

import java.lang.Runnable;
import om.edu.mec.smartHouseCommon.SmartHouseModel;

public class AutoUpdate implements Runnable{

	SmartHouseModel model;
	ClientConnectionManager myConnectionManager;

	AutoUpdate(SmartHouseModel m, ClientConnectionManager c){
		model = m;
		myConnectionManager = c;
	}

	public void run(){

		while(true){
			System.out.println("autoupdate");

			SmartHouseModel serverModel = myConnectionManager.getServerModel();
			model.setFireStatus(serverModel.getFireStatus());
			model.setLeakStatus(serverModel.getLeakStatus());
			model.setLightStatus(serverModel.getLightStatus());
			try{
				Thread.sleep(5000);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
	}
}