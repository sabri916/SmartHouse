package om.edu.mec.smartHouse;

import java.awt.*;
import java.awt.event.*;

class LightsOffButtonListener implements ActionListener{

	SmartHouseModel myModel;
	SmartHouseView myView;

	LightsOffButtonListener(SmartHouseModel myModel,SmartHouseView myView){
		this.myModel = myModel;
		this.myView = myView;
	}
	
	public void actionPerformed(ActionEvent e){
		myModel.setLightStatus(false);
	}
}