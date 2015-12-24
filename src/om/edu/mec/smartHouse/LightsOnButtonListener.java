package om.edu.mec.smartHouse;

import java.awt.*;
import java.awt.event.*;

class LightsOnButtonListener implements ActionListener{

	SmartHouseModel myModel;

	LightsOnButtonListener(SmartHouseModel myModel){
		this.myModel = myModel;
	}
	
	public void actionPerformed(ActionEvent e){
		myModel.setLightStatus(true);
	}
}