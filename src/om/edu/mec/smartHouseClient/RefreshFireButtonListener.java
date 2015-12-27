package om.edu.mec.smartHouseClient;

import java.awt.*;
import java.awt.event.*;

import om.edu.mec.smartHouseCommon.SmartHouseModel;

class RefreshFireButtonListener implements ActionListener{

	private SmartHouseModel myModel;
	private ClientConnectionManager myConnectionManager;

	RefreshFireButtonListener(SmartHouseModel myModel,ClientConnectionManager myConnectionManager){
		this.myModel = myModel;
		this.myConnectionManager = myConnectionManager;
	}
	
	public void actionPerformed(ActionEvent e){
		//request update froms server
		final SmartHouseModel serverModel = myConnectionManager.getServerModel();
		myModel.setFireStatus(serverModel.getFireStatus());
		myModel.setLeakStatus(serverModel.getLeakStatus());
		myModel.setLightStatus(serverModel.getLightStatus());
	}
}