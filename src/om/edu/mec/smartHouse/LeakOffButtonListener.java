package om.edu.mec.smartHouse;

import java.awt.*;
import java.awt.event.*;

class LeakOffButtonListener implements ActionListener{

	SmartHouseModel myModel;

	LeakOffButtonListener(SmartHouseModel myModel){
		this.myModel = myModel;
	}
	
	public void actionPerformed(ActionEvent e){
		myModel.setLeakStatus(false);
	}
}