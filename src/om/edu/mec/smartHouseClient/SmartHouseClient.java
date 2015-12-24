package om.edu.mec.smartHouseClient;

import om.edu.mec.smartHouseCommon.SmartHouseModel;
public class SmartHouseClient{


	public static void main(String argv[]){

		new SmartHouseClientView(new SmartHouseModel()).setVisible(true);
		
	}
}