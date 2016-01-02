package om.edu.mec.smartHouseCommon;
//class is packaged in a "Common" package because it is used in bothe server and client

import java.util.Observable;
import java.io.Serializable;

public class SmartHouseModel extends Observable implements Serializable{
	//Class needs to be Observable by the View so that changes in the model (this class)
	//is reflected on the View Immedietely
	//Class is Serializable to enable transmission through network

	//variables holding information about the state of the system
	//if isOnFire is true, the smart house is on fire and vice versa.
	private boolean isOnFire;
	private boolean isLeaking;
	private boolean isLightOn;

	public SmartHouseModel(){
		 isOnFire = false;
		 isLeaking = false;
		 isLightOn = false;
	}

	//setters and getters for the above variables for good practice
	//setters and getters are declared "synchronized" to ensure that modification
	//to the above variables are atomic

	public synchronized boolean getFireStatus(){
		return isOnFire;
	}

	public synchronized void setFireStatus(boolean x){
		isOnFire=x;
		setChanged(); //flags the object that it has been changed
		notifyObservers(); //notifies the Observers, in this case the View (SmartHouseView.java) of the change
							//so that the change can be reflected on the observers.
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