package om.edu.mec.smartHouseClient;

import java.awt.*;
import java.util.Observer;
import java.util.Observable;
import java.awt.event.*;

import om.edu.mec.smartHouseCommon.SmartHouseModel;

class SmartHouseClientView extends Frame implements Observer{

	SmartHouseModel myModel;

	private Label fireLabel;
	private Label leakLabel;
	private Label lightLabel;

	private Button refreshFireButton;
	private Button refreshLeakButton;
	private Button refreshLightButton;

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

	SmartHouseClientView(SmartHouseModel myModel){
		this.myModel = myModel;
		//setSize and location and title
		setTitle("Smart House Client");
		setSize(650,500);
		setLocation(1200,900);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});

		//instantiate 
		fireLabel = new Label("No Fire");
		leakLabel = new Label("No Water Leak");
		lightLabel = new Label("Lights Off");

		refreshFireButton = new Button("Refresh");
		refreshLeakButton = new Button("Refresh");
		refreshLightButton = new Button("Refresh");

		statusPanel = new Panel(new GridLayout(1,3,50,50));
		buttonPanel = new Panel(new GridLayout(1,3,50,50));
		fireButtonPanel = new Panel(new GridLayout(1,2));
		leakButtonPanel = new Panel(new GridLayout(1,2));
		lightButtonPanel = new Panel(new GridLayout(1,2));

		//set layout manager
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
		fireButtonPanel.add(refreshFireButton);
		leakButtonPanel.add(refreshLeakButton);
		lightButtonPanel.add(refreshLightButton);

		//adding ActionListeners
		refreshFireButton.addActionListener(new RefreshFireButtonListener(myModel));
	

	}

	public void update(Observable o, Object arg){

		SmartHouseModel model = (SmartHouseModel) o;

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