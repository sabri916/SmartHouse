package om.edu.mec.smartHouse;

import java.util.Observable;

public class SmartHouseModel extends Observable{

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
		System.out.println("setFire");
		setChanged();
		notifyObservers();
	}


	boolean getLeakStatus(){
		return isLeaking;
	}

	void setLeakStatus(boolean x){
		isLeaking=x;
		setChanged();
		notifyObservers();
	}

	boolean getLightStatus(){
		return isLightOn;
	}

	void setLightStatus(boolean x){
		isLightOn=x;
		setChanged();
		notifyObservers();
	}

}