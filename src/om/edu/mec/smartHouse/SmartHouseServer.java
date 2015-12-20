package om.edu.mec.smartHouse;

public class SmartHouseServer{

	public static void main(String args[]){
		
		SmartHouseModel myModel = new SmartHouseModel();
		ConnectionManager myConnectionManager = new ConnectionManager(myModel);
		SmartHouseView myView = new SmartHouseView(myModel);
		myModel.addObserver(myView);
		myView.setVisible(true);
		myConnectionManager.startServer();

		
		//socket class

		System.out.println("Yellow");
	}
}