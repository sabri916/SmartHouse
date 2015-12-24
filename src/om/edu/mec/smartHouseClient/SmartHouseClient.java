package om.edu.mec.smartHouseClient;

import om.edu.mec.smartHouseCommon.SmartHouseModel;
public class SmartHouseClient{


	public static void main(String argv[]){
		SmartHouseModel myModel = new SmartHouseModel();
		SmartHouseClientView myView = new SmartHouseClientView(myModel);
		myModel.addObserver(myView);
		myView.setVisible(true);
		
	}
}