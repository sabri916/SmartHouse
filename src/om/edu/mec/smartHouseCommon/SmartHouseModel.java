package om.edu.mec.smartHouseCommon;

import java.util.Observable;
import java.io.Serializable;

public class SmartHouseModel extends Observable implements Serializable{

	private boolean isOnFire;
	private boolean isLeaking;
	private boolean isLightOn;

	public SmartHouseModel(){
		 isOnFire = false;
		 isLeaking = false;
		 isLightOn = false;
	}

	public boolean getFireStatus(){
		return isOnFire;
	}

	public void setFireStatus(boolean x){
		isOnFire=x;
		setChanged();
		notifyObservers();
	}


	public boolean getLeakStatus(){
		return isLeaking;
	}

	public void setLeakStatus(boolean x){
		isLeaking=x;
		setChanged();
		notifyObservers();
	}

	public boolean getLightStatus(){
		return isLightOn;
	}

	public void setLightStatus(boolean x){
		isLightOn=x;
		setChanged();
		notifyObservers();
	}

}