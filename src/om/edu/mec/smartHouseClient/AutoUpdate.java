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
			SmartHouseModel serverModel;
			try{
				serverModel = myConnectionManager.getServerModel();
			}catch(Exception e){
				System.out.println(e);
				serverModel = model;
			}
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