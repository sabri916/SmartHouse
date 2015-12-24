package om.edu.mec.smartHouseClient;

import java.awt.*;
import java.awt.event.*;

import om.edu.mec.smartHouseCommon.SmartHouseModel;

class RefreshFireButtonListener implements ActionListener{

	SmartHouseModel myModel;

	RefreshFireButtonListener(SmartHouseModel myModel){
		this.myModel = myModel;
	}
	
	public void actionPerformed(ActionEvent e){
		//request update froms server
		final SmartHouseModel serverModel = ClientConnectionManager.getServerModel();
		myModel.setFireStatus(serverModel.getFireStatus());
		myModel.setLeakStatus(serverModel.getLeakStatus());
		myModel.setLightStatus(serverModel.getLightStatus());
	}
}