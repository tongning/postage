
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
/**
 * WeightScreen is a JPanel object that allows entry of the weight
 * of a package. It has one JTextField for user input, as well as JRadioButtons
 * for unit selection (grams, pounds, or page count).
 * @author Karina Chang, Anthony Li
 *
 */
public class WeightScreen extends JPanel{
	private ButtonGroup bg = new ButtonGroup();
	private JRadioButton grams = new JRadioButton("Grams");
	private JRadioButton pounds = new JRadioButton("Pounds");
	private JRadioButton estimate = new JRadioButton("Estimate by page count");
	private JTextField weightbox1=null;
	private JLabel units = new JLabel("lbs");
	private MainScreen linkApp;
	private JLabel instructions = new JLabel("Enter weight:");
	private ArrayList<Integer> previousScreen=new ArrayList<Integer>();

	public int getPreviousScreen() {
		return previousScreen.get(previousScreen.size()-2);
	}

	public void setPreviousScreen(ArrayList<Integer> previousScreen) {
		this.previousScreen = previousScreen;

	}

	public WeightScreen(MainScreen linkToApp, ArrayList<Integer> previous){
		setPreviousScreen(previous);
		linkApp=linkToApp;
		setVisible(true);
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gridbag);
		c.gridx=0;
		c.gridy=0;
		c.gridwidth=GridBagConstraints.REMAINDER;
		c.insets=new Insets(100,0,0,0);
		gridbag.setConstraints(instructions, c);
		add(instructions);


		c.insets=new Insets(0,100,0,0);
		bg.add(grams);
		bg.add(pounds);
		String packtype=linkApp.getTypePackage();
		System.out.println("br"+packtype);
		bg.add(estimate);


		grams.addItemListener(new ItemListener() {


			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Inches state changed");
				if(e.getStateChange()==1) {
					System.out.println("change detected");
					units.setText("grams");

				}

			}           
		});

		pounds.addItemListener(new ItemListener() {


			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Inches state changed");
				if(e.getStateChange()==1) {
					System.out.println("change detected");
					units.setText("lbs");

				}

			}           
		});

		estimate.addItemListener(new ItemListener() {


			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Inches state changed");
				if(e.getStateChange()==1) {
					System.out.println("change detected");
					units.setText("pages");

				}

			}           
		});
		pounds.setSelected(true);
		//action listener for forward screens
		ActionListener clickForward=new ActionListener(){
			public void actionPerformed(ActionEvent e){

				boolean checksPassed=true;
				Double weight=-1.0;
				try{
					weight=Double.parseDouble(weightbox1.getText());
				}
				catch(Exception error){
					checksPassed=false;
				}
				if(weight<=0){
					checksPassed=false;
				}
				
				if(checksPassed){
					//System.out.println("You are "+e.getActionCommand());

					//when forward button is clicked, read weight and unit
					linkApp.setWeightUnit(units.getText().trim());
					linkApp.setWeight(Double.parseDouble(weightbox1.getText()));

					//for envelopes, go straight to final price screen
					if(linkApp.getTypePackage().equals("Envelope") || linkApp.getTypePackage().equals("Large Envelope")){
						linkApp.changeScreen(MainScreen.SCREEN_FINAL,MainScreen.SCREEN_WEIGHT);
					}
					else
						linkApp.changeScreen(MainScreen.SCREEN_ZIP,MainScreen.SCREEN_WEIGHT); //go to zip code screen
				}
				else{
					instructions.setText("<html><font color='#FF0000'>Invalid input.<br>Please check your<br>input and try again.</font></html>");
				}
			}//
		};

		//action listener for backward screens
		ActionListener clickBackward=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ArrayList<Integer> newHold=new ArrayList<Integer>();
				if (previousScreen.indexOf(1)!=previousScreen.lastIndexOf(1)){
					for (int x=0;x<previousScreen.indexOf(1);x++){
						newHold.add(previousScreen.get(x));
					}

				}
				else{
					newHold=previousScreen;
				}
				//	previousScreen.remove(previousScreen.size()-1);
			System.out.println(newHold);
				linkApp.setTracking(newHold);

				linkApp.changeScreen(getPreviousScreen(),MainScreen.SCREEN_ZIP);

			}
		};


		c.anchor=GridBagConstraints.FIRST_LINE_START;
		c.gridx=0;
		c.gridy=1;
		c.gridwidth=GridBagConstraints.REMAINDER;
		gridbag.setConstraints(grams, c);
		add(grams);

		c.gridy=2;
		gridbag.setConstraints(pounds, c);
		add(pounds);

		c.gridy=3;
		gridbag.setConstraints(estimate, c);
		add(estimate);


		
		c.gridx=0;
		c.gridy=4;
		c.gridwidth=1;
		c.fill=GridBagConstraints.NONE;
		c.insets=new Insets(50,100,0,0);
		weightbox1 = new JTextField();
		weightbox1.setPreferredSize(new Dimension(200,30));
		weightbox1.setMaximumSize(new Dimension(100,30));
		gridbag.setConstraints(weightbox1, c);
		add(weightbox1);
		weightbox1.setText("0.00");
		weightbox1.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(weightbox1.getText().trim().equals("0.00")){
					weightbox1.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(weightbox1.getText().trim().equals("")){
					weightbox1.setText("0.00");
				}
			}
			
		});
		//c.gridx=1;
		//c.gridy=4;
		//c.gridwidth=1;
	//	c.anchor=GridBagConstraints.CENTER;

	//	gridbag.setConstraints(units, c);
	//	add(units);

		c.gridy=5;
		c.gridx=0;
		c.gridwidth=1;
		c.anchor=GridBagConstraints.LAST_LINE_START;
		c.fill=GridBagConstraints.NONE;
		JButton back = new JButton("Back");
		c.insets=new Insets(100,0,0,0);
		gridbag.setConstraints(back, c);
		add(back);

		c.gridy=5;
		c.gridx=0;
		c.gridwidth=1;
		c.anchor=GridBagConstraints.LAST_LINE_END;
		c.fill=GridBagConstraints.NONE;
		c.insets=new Insets(250,300,0,0);
		JButton forward = new JButton("Forward");
		gridbag.setConstraints(forward, c);
		add(forward);
		forward.addActionListener(clickForward);
		back.addActionListener(clickBackward);

	}
}
