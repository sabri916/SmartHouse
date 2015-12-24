package om.edu.mec.smartHouse;

import java.awt.*;
import java.awt.event.*;

class LeakOnButtonListener implements ActionListener{

	SmartHouseModel myModel;
	LeakOnButtonListener(SmartHouseModel myModel){
		this.myModel = myModel;
	}
	
	public void actionPerformed(ActionEvent e){
		myModel.setLeakStatus(true);
	}
}