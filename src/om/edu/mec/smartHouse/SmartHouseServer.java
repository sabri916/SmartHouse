package om.edu.mec.smartHouse;

public class SmartHouseServer{

	public static void main(String args[]){
		SmartHouseModel myModel = new SmartHouseModel();
		SmartHouseView myView = new SmartHouseView(myModel);
		myView.setVisible(true);

		System.out.println("Yellow");
	}
}