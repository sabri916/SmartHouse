package om.edu.mec.smartHouse;

import java.awt.*;
import java.awt.event.*;

class LightsOnButtonListener implements ActionListener{

	SmartHouseModel myModel;
	SmartHouseView myView;

	LightsOnButtonListener(SmartHouseModel myModel,SmartHouseView myView){
		this.myModel = myModel;
		this.myView = myView;
	}
	
	public void actionPerformed(ActionEvent e){
		myModel.setLightStatus(true);
	}
}