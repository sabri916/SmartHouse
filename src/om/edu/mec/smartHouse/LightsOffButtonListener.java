package om.edu.mec.smartHouse;

import java.awt.*;
import java.awt.event.*;

class LightsOffButtonListener implements ActionListener{

	SmartHouseModel myModel;

	LightsOffButtonListener(SmartHouseModel myModel){
		this.myModel = myModel;
	}
	
	public void actionPerformed(ActionEvent e){
		myModel.setLightStatus(false);
	}
}