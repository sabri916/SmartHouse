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

	public synchronized boolean getFireStatus(){
		return isOnFire;
	}

	public synchronized void setFireStatus(boolean x){
		isOnFire=x;
		setChanged();
		notifyObservers();
	}


	public synchronized boolean getLeakStatus(){
		return isLeaking;
	}

	public synchronized void setLeakStatus(boolean x){
		isLeaking=x;
		setChanged();
		notifyObservers();
	}

	public synchronized boolean getLightStatus(){
		return isLightOn;
	}

	public synchronized void setLightStatus(boolean x){
		isLightOn=x;
		setChanged();
		notifyObservers();
	}

}