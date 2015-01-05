
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
/**
 * BoxSize is a JPanel object that allows box size selection for flat rate shipping
 * options. For priority mail, the USPS has two flat rate box options. BoxSize has a 
 * button for each option. Each button is labeled with the box dimensions. 
 * @author Karina Chang, Anthony Li
 *
 */
public class BoxSize extends JPanel{
	private JLabel instruction = new JLabel("Choose box size:");
	private JButton largeboxbutton=new JButton("Large\n23-11/16\" x 11-3/4\" x 3\" OR");
	private JButton medboxbutton=new JButton("Medium");
	private JButton continuebtn=new JButton("Continue");
	private JButton homebtn=new JButton("Home");
	private JLabel dimension1=new JLabel("23-11/16\" x 11-3/4\" x 3\" OR");
	private JLabel dimension2=new JLabel("12\" x 12\" x 5-1/2\"");
	private JButton back=new JButton("Back");
	
	private MainScreen linkApp;
//	public static void main (String[] args){
//		JFrame f = new JFrame("Postage Calc");
//		f.setContentPane(new BoxSize());
//		f.setDefaultCloseOperation(3);
//		f.setDefaultLookAndFeelDecorated(true);
//		f.pack();
//		f.setSize(new Dimension(400,600));
//		
//		f.setVisible(true);
//	}
	//
	


	public BoxSize(MainScreen linkToApp) {
		setVisible(true);
		linkApp=linkToApp;
		largeboxbutton.setText("<html><font size=\"6\"><center>Large</center></font><br>23-11/16\" x 11-3/4\" x 3\" <br><center>OR</center><center>12\" x 12\" x 5-1/2\"</center></html>");
		medboxbutton.setText("<html><font size=\"6\"><center>Medium</center></font><br>13-5/8\" x 11-7/8\" x 3-3/8\" <br><center>OR</center><center>11\" x 8-1/2\" x 5-1/2\"</center></html>");
		GridBagLayout gridbag=new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gridbag);
		
		
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(50,30,0,0);
		c.anchor=(GridBagConstraints.LINE_START);
		gridbag.setConstraints(instruction, c);
		add(instruction);
		c.insets=new Insets(30,30,0,30);
		
		c.gridx=0;
		c.gridy=1;
		c.weightx=1;
		c.weighty=1;
		
		//
		
		c.fill=GridBagConstraints.BOTH;
		gridbag.setConstraints(largeboxbutton, c);
		add(largeboxbutton);
		
		
		
		c.gridx=0;
		c.gridy=2;
		c.weightx=1;
		c.weighty=1;
		c.insets=new Insets(0,30,80,30);
		c.fill=GridBagConstraints.BOTH;
		gridbag.setConstraints(medboxbutton, c);
		
		add(medboxbutton);
		
		
		c.gridx=0;
		c.gridy=3;
		c.weightx=0;
		c.weighty=0;
		c.insets=new Insets(0,30,30,0);
		c.fill=GridBagConstraints.NONE;
		gridbag.setConstraints(back, c);
		add(back);
		//add(continuebtn);
		
		/**ONLY NEED A BACK BUTTON**/
		//action listener for backward screens
		ActionListener clickBackward=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//System.out.println("You are "+e.getActionCommand());
				linkApp.changeScreen(MainScreen.SCREEN_RATE,MainScreen.SCREEN_BOXSIZE);

			}
		};
		back.addActionListener(clickBackward);
		
		medboxbutton.setActionCommand("medium");
		largeboxbutton.setActionCommand("large");
		//action listener for the sizes, both go to the final screen with different calculations
		ActionListener largeMedium=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("You are "+e.getActionCommand());
				if(e.getActionCommand().equals("large")){
					linkApp.setBoxType(2);
				}
				else if(e.getActionCommand().equals("medium")){
					linkApp.setBoxType(1);
				}
				linkApp.changeScreen(MainScreen.SCREEN_FINAL, MainScreen.SCREEN_BOXSIZE);

			}
		};
		medboxbutton.addActionListener(largeMedium);
		largeboxbutton.addActionListener(largeMedium);
	}


}
