package om.edu.mec.smartHouse;

import java.awt.*;
import java.awt.event.*;

class FireOffButtonListener implements ActionListener{

	SmartHouseModel myModel;

	FireOffButtonListener(SmartHouseModel myModel){
		this.myModel = myModel;
	}
	
	public void actionPerformed(ActionEvent e){
		myModel.setFireStatus(false);
		System.out.println("fire off");
	}
}