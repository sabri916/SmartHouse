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
		return isOnFire;
	}

	void setLeakStatus(boolean x){
		isLeaking=x;
	}

	boolean getLightStatus(){
		return isOnFire;
	}

	void setLightStatus(boolean x){
		isLightOn=x;
	}

}