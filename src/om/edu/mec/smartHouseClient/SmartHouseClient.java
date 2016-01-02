package om.edu.mec.smartHouseClient;

import om.edu.mec.smartHouseCommon.SmartHouseModel;
public class SmartHouseClient{
	//Main Class of Client


	public static void main(String argv[]){
		//Main method tying the different components of MVC together

		//A model is created for the Client
		//This model is identical to the one used in the Server
		SmartHouseModel myModel = new SmartHouseModel();

		//ClientConnectionManager connects to the server and fetches latest Model of the system
		//it is also able to send lights On/Off commands
		ClientConnectionManager myConnectionManager = new ClientConnectionManager();

		//View of the system/the User Interface
		SmartHouseClientView myView = new SmartHouseClientView(myModel,myConnectionManager);

		//the Model is observed by the View
		myModel.addObserver(myView);

		//the connectionManager is also observed by the view because it holds information about
		//the IP address which is showen on the View (the user interface)
		myConnectionManager.addObserver(myView);

		//Auto Update Class fetches infromation from the server every five seconds and it does it
		//on a different thread
		AutoUpdate au = new AutoUpdate(myModel,myConnectionManager);
		try{
			new Thread(au).start();
		}catch(Exception e){
			System.out.println("Cannot connect!");
		}

		//makes the window visible
		myView.setVisible(true);
		
	}
}