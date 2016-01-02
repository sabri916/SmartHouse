//to Arrange files into packages
package om.edu.mec.smartHouse;


import java.lang.Thread; //needed to run Server on a different Thread

import om.edu.mec.smartHouseCommon.SmartHouseModel; //needed for common file between Server and Client

public class SmartHouseServer{

	public static void main(String args[]){
		//This application employes the Model-View-Controller (MVC) Design pattern
		//The main method creates the model, View and Controller and sets the relationship between them
		
		SmartHouseModel myModel = new SmartHouseModel();
		ConnectionManager myConnectionManager = new ConnectionManager(myModel);
		SmartHouseView myView = new SmartHouseView(myModel);
		myModel.addObserver(myView);
		myView.setVisible(true); //Required for Frame(View) to appear.

		//a new thread will create a server on a different thread improving server availability
		new Thread(myConnectionManager).start();

		System.out.println("Server Started...");
	}
}