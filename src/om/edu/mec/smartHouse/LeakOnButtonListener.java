package om.edu.mec.smartHouse;

import java.awt.*;
import java.awt.event.*;

class LeakOnButtonListener implements ActionListener{

	SmartHouseModel myModel;
	SmartHouseView myView;

	LeakOnButtonListener(SmartHouseModel myModel,SmartHouseView myView){
		this.myModel = myModel;
		this.myView = myView;
	}
	
	public void actionPerformed(ActionEvent e){
		myModel.setLeakStatus(true);
		myView.reloadView();
	}
}