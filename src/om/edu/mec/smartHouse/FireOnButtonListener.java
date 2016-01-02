package om.edu.mec.smartHouse;

import java.awt.*;
import java.awt.event.*;

import om.edu.mec.smartHouseCommon.SmartHouseModel;

class FireOnButtonListener implements ActionListener{
	//Implements Action Listener to enable the class to listen to events 
	//such as buttons being pressed

	SmartHouseModel myModel;

	FireOnButtonListener(SmartHouseModel myModel){
		//model is passed. This is needed to enable modification of Model
		this.myModel = myModel;
	}
	
	public void actionPerformed(ActionEvent e){
		//actionPerformed is called whenever Fire On Button is pressed
		
		myModel.setFireStatus(true);//Clicking on the Button changes the model.
		System.out.println("fire on");
	}
}