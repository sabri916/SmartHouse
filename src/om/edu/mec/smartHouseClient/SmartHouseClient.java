package om.edu.mec.smartHouseClient;

import om.edu.mec.smartHouseCommon.SmartHouseModel;
public class SmartHouseClient{


	public static void main(String argv[]){
		SmartHouseModel myModel = new SmartHouseModel();
		ClientConnectionManager myConnectionManager = new ClientConnectionManager();
		SmartHouseClientView myView = new SmartHouseClientView(myModel,myConnectionManager);
		myModel.addObserver(myView);
		myConnectionManager.addObserver(myView);
		AutoUpdate au = new AutoUpdate(myModel,myConnectionManager);
		try{
			new Thread(au).start();
		}catch(Exception e){
			System.out.println("Cannot connect!");
		}
		myView.setVisible(true);
		
	}
}