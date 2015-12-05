package om.edu.mec.smartHouse;

import java.awt.*;
import java.awt.event.*;

class FireOnButtonListener implements ActionListener{

	SmartHouseModel myModel;
	Label fireLabel;

	FireOnButtonListener(SmartHouseModel myModel,Label fireLabel){
		this.myModel = myModel;
		this.fireLabel = fireLabel;
	}
	
	public void actionPerformed(ActionEvent e){
		myModel.setFireStatus(true);
		fireLabel.setText("On Fire!!! T.T");
	}
}