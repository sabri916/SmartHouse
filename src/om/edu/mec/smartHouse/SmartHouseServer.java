package om.edu.mec.smartHouse;

import java.lang.Thread;
import java.lang.Runnable;
import om.edu.mec.smartHouseCommon.SmartHouseModel;

public class SmartHouseServer{

	public static void main(String args[]){
		
		SmartHouseModel myModel = new SmartHouseModel();
		ConnectionManager myConnectionManager = new ConnectionManager(myModel);
		SmartHouseView myView = new SmartHouseView(myModel);
		myModel.addObserver(myView);
		myView.setVisible(true);

		
		new Thread(myConnectionManager).start();
		new Thread(myConnectionManager).start();
		new Thread(myConnectionManager).start();
		new Thread(myConnectionManager).start();
		new Thread(myConnectionManager).start();

		System.out.println("Server Started...");
	}
}