package om.edu.mec.smartHouse;

import java.awt.*;
import java.awt.event.*;

import om.edu.mec.smartHouseCommon.SmartHouseModel;

class FireOnButtonListener implements ActionListener{

	SmartHouseModel myModel;

	FireOnButtonListener(SmartHouseModel myModel){
		this.myModel = myModel;
	}
	
	public void actionPerformed(ActionEvent e){
		myModel.setFireStatus(true);
		System.out.println("fire on");
	}
}