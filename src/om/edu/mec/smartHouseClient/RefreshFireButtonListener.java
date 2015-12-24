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
		ClientConnectionManager.connectToServer();
	}
}