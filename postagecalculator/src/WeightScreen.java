
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
public class WeightScreen extends JPanel{
	private ButtonGroup bg = new ButtonGroup();
	private JRadioButton grams = new JRadioButton("Grams");
	private JRadioButton pounds = new JRadioButton("Pounds");
	private JRadioButton estimate = new JRadioButton("Use SmartEstimate");
	private JTextField weightbox1=null;
	private JLabel units = new JLabel("lbs  ");
	private MainScreen linkApp;
//	public static void main (String[] args){
//		JFrame f = new JFrame("Postage Calc");
//		f.setContentPane(new WeightScreen());
//		f.setDefaultCloseOperation(3);
//		f.setDefaultLookAndFeelDecorated(true);
//		f.pack();
//		f.setSize(new Dimension(400,600));
//		
//		f.setVisible(true);
//	}
	
	public WeightScreen(MainScreen linkToApp){
		linkApp=linkToApp;
		setVisible(true);
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gridbag);
		c.gridx=0;
		c.gridy=0;
		c.gridwidth=GridBagConstraints.REMAINDER;
		JLabel instructions = new JLabel("Enter weight:");
		gridbag.setConstraints(instructions, c);
		add(instructions);
		
		
		
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
					units.setText("grams  ");
					
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
					units.setText("lbs   ");
					
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
					units.setText("pages  ");
					
				}
				
			}           
	      });
		
		//action listener for forward screens
		ActionListener clickForward=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//System.out.println("You are "+e.getActionCommand());
				
				//when forward button is clicked, read weight and unit
				linkApp.setWeightUnit(units.getText().trim());
				linkApp.setWeight(Double.parseDouble(weightbox1.getText()));
				
				linkApp.changeScreen(MainScreen.SCREEN_ZIP,MainScreen.SCREEN_WEIGHT); //go to zip code screen

			}//
		};
		
		//action listener for backward screens
		ActionListener clickBackward=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//System.out.println("You are "+e.getActionCommand());
				linkApp.changeScreen(MainScreen.SCREEN_HOME,MainScreen.SCREEN_WEIGHT);

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
		c.fill=GridBagConstraints.HORIZONTAL;
		c.insets=new Insets(50,0,0,0);
		weightbox1 = new JTextField();
		weightbox1.setPreferredSize(new Dimension(200,30));
		gridbag.setConstraints(weightbox1, c);
		add(weightbox1);
		
		c.gridx=1;
		c.gridy=4;
		c.gridwidth=1;
		c.anchor=GridBagConstraints.CENTER;
		
		gridbag.setConstraints(units, c);
		add(units);
		
		c.gridy=5;
		c.gridx=0;
		c.gridwidth=1;
		c.anchor=GridBagConstraints.LAST_LINE_START;
		c.fill=GridBagConstraints.NONE;
		JButton back = new JButton("Back");
		gridbag.setConstraints(back, c);
		add(back);
		
		c.gridy=5;
		c.gridx=0;
		c.gridwidth=1;
		c.anchor=GridBagConstraints.LAST_LINE_END;
		c.fill=GridBagConstraints.NONE;
		JButton forward = new JButton("Forward");
		gridbag.setConstraints(forward, c);
		add(forward);
		forward.addActionListener(clickForward);
		back.addActionListener(clickBackward);
		
	}
}
