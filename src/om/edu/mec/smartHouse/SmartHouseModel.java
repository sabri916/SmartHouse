package om.edu.mec.smartHouse;

public class SmartHouseModel{

	private boolean isOnFire;
	private boolean isLeaking;
	private boolean isLightOn;

	public SmartHouseModel(){
		 isOnFire = false;
		 isLeaking = false;
		 isLightOn = false;
	}

	boolean getFireStatus(){
		return isOnFire;
	}

	void setFireStatus(boolean x){
		isOnFire=x;
	}


	boolean getLeakStatus(){
		return isLeaking;
	}

	void setLeakStatus(boolean x){
		isLeaking=x;
	}

	boolean getLightStatus(){
		return isLightOn;
	}

	void setLightStatus(boolean x){
		isLightOn=x;
	}

}