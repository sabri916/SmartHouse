package om.edu.mec.smartHouse;

import java.awt.*;
import java.awt.event.*;

class FireOnButtonListener implements ActionListener{

	SmartHouseModel myModel;
	SmartHouseView myView;

	FireOnButtonListener(SmartHouseModel myModel,SmartHouseView myView){
		this.myModel = myModel;
		this.myView = myView;
	}
	
	public void actionPerformed(ActionEvent e){
		myModel.setFireStatus(true);
		myView.reloadView();
	}
}