package om.edu.mec.smartHouse;

import java.awt.*;
import java.awt.event.*;

class LeakOffButtonListener implements ActionListener{

	SmartHouseModel myModel;
	SmartHouseView myView;

	LeakOffButtonListener(SmartHouseModel myModel,SmartHouseView myView){
		this.myModel = myModel;
		this.myView = myView;
	}
	
	public void actionPerformed(ActionEvent e){
		myModel.setLeakStatus(false);
	}
}