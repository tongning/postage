

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



/**
 * RateOptions is a JPanel object that displays buttons for the four package
 * shipping options: Flat rate express, flat rate regular, priority mail regular, and
 * priority mail express.
 * @author Karina Chang, Anthony Li
 *
 */
public class RateOptions extends JPanel{
	private JLabel instructions;
	private JPanel rGroup;
	private MainScreen linkApp;
	
	//for back and forward buttons
	private JPanel backForward;
	private JButton back;
	private JButton forward;
public RateOptions(MainScreen linkToApp) {
	super();
	linkApp=linkToApp;
	setFocusable(true);
	setVisible(true);
	setLayout(new BorderLayout());
	
	instructions=new JLabel("Select a rate:  ");
	rGroup=new RateGroup(); //calls rategroup for the buttons and stuff
	
	/**FOR THE BACK AND FORWARD BUTTONS**/
	backForward=new JPanel();
	backForward.setLayout(new BoxLayout(backForward,BoxLayout.LINE_AXIS));
	back=new JButton("Back");
	//forward=new JButton("Forward");
	backForward.add(back);
	//backForward.add(forward);
	
	/**ONLY NEED A BACK BUTTON**/
	//action listener for backward screens
	ActionListener clickBackward=new ActionListener(){
		public void actionPerformed(ActionEvent e){
			//System.out.println("You are "+e.getActionCommand());
			linkApp.changeScreen(MainScreen.SCREEN_HOME,MainScreen.SCREEN_RATE);

		}
	};
	back.addActionListener(clickBackward);
	//forward.addActionListener(clickForward);
	
	
	add(instructions,BorderLayout.NORTH);
	add(rGroup,BorderLayout.CENTER);
	add(backForward,BorderLayout.SOUTH);
}


public class RateGroup extends JPanel{
	private JButton flatRateR; //regular
	private JButton flatRateE; //express
	private JButton priority;
	private JButton priorityE; //express
	public RateGroup(){
		super();
		setFocusable(true);
		setVisible(true);
		
		setLayout(new GridLayout(4,1,20,10));
		flatRateR=new JButton("Flat Rate Service (Regular)");
		flatRateE=new JButton("Flat Rate Service (Express)");
		priority=new JButton("Priority Mail");
		priorityE=new JButton("Priority Mail Express");
		

		
		
		//adding the jbuttons
		add(flatRateR);
		add(flatRateE);
		add(priority);
		add(priorityE);
		
		
		//action listener for flat rates regular, as it goes to the box size
		ActionListener clickFlatR=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//System.out.println("You are "+e.getActionCommand());
				linkApp.setShipSpeed(1);
				linkApp.setTypePackage("PackageFlatRate");
				linkApp.changeScreen(MainScreen.SCREEN_BOXSIZE, MainScreen.SCREEN_RATE);

			}
		};
		
		//action listener for the flat rate express, as it goes to the final screen
		ActionListener clickFlatE=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//System.out.println("You are "+e.getActionCommand());
				linkApp.setShipSpeed(2);
				linkApp.setBoxType(1);
				linkApp.setTypePackage("PackageFlatRate");
				linkApp.changeScreen(MainScreen.SCREEN_FINAL, MainScreen.SCREEN_RATE); //there is only one box size for flat rate express, so go straight to final screen

			}
		};
		
		//action listener for prioirty rates, both go to DIMENSION SCREEN
		ActionListener clickPriority=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("You are "+e.getActionCommand());
				if(e.getActionCommand().equals("Priority Mail")){
					linkApp.setShipSpeed(1); //set to regular shipping speed
				}
				else if(e.getActionCommand().equals("Priority Mail Express")){
					linkApp.setShipSpeed(2); //set to express shipping speed
				}
				linkApp.changeScreen(MainScreen.SCREEN_DIM, MainScreen.SCREEN_RATE);

			}
		};//
		

		
		flatRateR.addActionListener(clickFlatR);
		flatRateE.addActionListener(clickFlatE);
		
		priority.addActionListener(clickPriority);
		priorityE.addActionListener(clickPriority);
		
	}
}
}


