package om.edu.mec.smartHouseClient;

import java.awt.*;
import java.awt.event.*;

import om.edu.mec.smartHouseCommon.SmartHouseModel;

class RefreshFireButtonListener implements ActionListener{
	//ActionListener listens to clicks for when the Refresh button is clicked

	private SmartHouseModel myModel;
	private ClientConnectionManager myConnectionManager;

	RefreshFireButtonListener(SmartHouseModel myModel,ClientConnectionManager myConnectionManager){
		//connection manager is needed to establish a connection with the server when refresh is clicked
		//model is needed to change the local model according to data from the server
		this.myModel = myModel;
		this.myConnectionManager = myConnectionManager;
	}
	
	public void actionPerformed(ActionEvent e){
		//request latest model froms server
		final SmartHouseModel serverModel = myConnectionManager.getServerModel();

		//change local model to match server model
		myModel.setFireStatus(serverModel.getFireStatus());
		myModel.setLeakStatus(serverModel.getLeakStatus());
		myModel.setLightStatus(serverModel.getLightStatus());
	}
}