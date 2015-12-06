package om.edu.mec.smartHouse;

import java.awt.*;
import java.awt.event.*;

class FireOffButtonListener implements ActionListener{

	SmartHouseModel myModel;
	SmartHouseView myView;

	FireOffButtonListener(SmartHouseModel myModel,SmartHouseView myView){
		this.myModel = myModel;
		this.myView = myView;
	}
	
	public void actionPerformed(ActionEvent e){
		myModel.setFireStatus(false);
		myView.reloadView();
	}
}