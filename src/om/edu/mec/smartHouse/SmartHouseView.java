package om.edu.mec.smartHouse;

import java.awt.*;
import java.util.Observer;
import java.util.Observable;
import java.awt.event.*;

import om.edu.mec.smartHouseCommon.SmartHouseModel;

class SmartHouseView extends Frame implements Observer{
	//extends Frame to create a Window
	//implements and Observer to "Observe" changes in the Model (SmartHouseModel)
	//changes in the model will be reflected on this View

	SmartHouseModel myModel; //Model referenced to further pass it onto the Controller


	//Declaration of all Component/Layouts and Panels for access throughout the class
	private Label fireLabel;
	private Label leakLabel;
	private Label lightLabel;

	private Button fireOn;
	private Button fireOff;
	private Button leakOn;
	private Button leakOff;
	private Button lightOn;
	private Button ligthtOff;

	private Panel statusPanel;
	private Panel buttonPanel;
	private Panel fireButtonPanel;
	private Panel leakButtonPanel;
	private Panel lightButtonPanel;

	private BorderLayout mainLayout;
	private GridLayout statusLayout;
	private FlowLayout fireButtonLayout;
	private FlowLayout leakButtonLayout;
	private FlowLayout lightButtonLayout;

	SmartHouseView(SmartHouseModel myModel){
		//model passed from main class
		this.myModel = myModel;

		//setSize and location and title
		setTitle("Smart House Server");
		setSize(650,500);
		setLocation(200,900);
		addWindowListener(new WindowAdapter(){
			//Programming the cross button of the window to close the application
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});

		//instantiate all the declated objects above giving initial values
		fireLabel = new Label("No Fire");
		leakLabel = new Label("No Water Leak");
		lightLabel = new Label("Lights Off");

		fireOn = new Button("Fire On");
		fireOff = new Button("Fire Off");
		leakOn = new Button("Water Leak On");
		leakOff = new Button("Water Leak Off");
		lightOn = new Button("Lights On");
		ligthtOff = new Button("Lights Off");

		//Panels are given layouts 
		statusPanel = new Panel(new GridLayout(1,3,50,50));
		buttonPanel = new Panel(new GridLayout(1,3,50,50));
		fireButtonPanel = new Panel(new GridLayout(1,2));
		leakButtonPanel = new Panel(new GridLayout(1,2));
		lightButtonPanel = new Panel(new GridLayout(1,2));

		//set root layout manager
		setLayout(new BorderLayout(10,10));

		//add Panels to Main Layout
		add(statusPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		//add status to layout
		statusPanel.add(fireLabel);
		statusPanel.add(leakLabel);
		statusPanel.add(lightLabel);

		//set Alignment of Label
		fireLabel.setAlignment(Label.CENTER);
		leakLabel.setAlignment(Label.CENTER);
		lightLabel.setAlignment(Label.CENTER);

		//add Button Layouts
		buttonPanel.add(fireButtonPanel);
		buttonPanel.add(leakButtonPanel);
		buttonPanel.add(lightButtonPanel);

		//add On/off Buttons
		fireButtonPanel.add(fireOn);
		fireButtonPanel.add(fireOff);
		leakButtonPanel.add(leakOn);
		leakButtonPanel.add(leakOff);
		lightButtonPanel.add(lightOn);
		lightButtonPanel.add(ligthtOff);

		//adding ActionListeners - Each Button has its own ActionListeners in different Classes.
		//models are passed to actionlisteners because they will be modified
		fireOn.addActionListener(new FireOnButtonListener(myModel));
		fireOff.addActionListener(new FireOffButtonListener(myModel));
		leakOn.addActionListener(new LeakOnButtonListener(myModel));
		leakOff.addActionListener(new LeakOffButtonListener(myModel));
		lightOn.addActionListener(new LightsOnButtonListener(myModel));
		ligthtOff.addActionListener(new LightsOffButtonListener(myModel));

	}

	public void update(Observable o, Object arg){
		//the update button is inherited from the Observer interface
		//when changes in the Observable (SmartHouseModel) are notified, the view can be updated in here

		//this will cast the Observable into a SmartHouseModel to enable calling of its methods
		SmartHouseModel model = (SmartHouseModel) o;

		//depending on the Model, labels are Modified
		if(model.getFireStatus()){
			fireLabel.setText("On Fire!!! T.T");
		}else{
			fireLabel.setText("No Fire");
		}

		if(model.getLeakStatus()){
			leakLabel.setText("Water is leaking!!! T.T");
		}else{
			leakLabel.setText("No Water Leak");
		}

		if(model.getLightStatus()){
			lightLabel.setText("Lights are On ^.^");
		}else{
			lightLabel.setText("Lights Off");
		}
	}
}