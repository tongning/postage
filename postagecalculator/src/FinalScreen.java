
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
//git test
public class FinalScreen extends JPanel{
	private ButtonGroup bg = new ButtonGroup();
	private JRadioButton grams = new JRadioButton("Grams");
	private JRadioButton pounds = new JRadioButton("Pounds");
	private JRadioButton estimate = new JRadioButton("Use SmartEstimate");
	private JLabel units = new JLabel("lbs  ");
	private JLabel finalprice= new JLabel("<html><font size='60'>3.56</font></html>");
	private double pricevar=3.56;
	private MainScreen linkApp;
	private ArrayList<Integer> previousS=new ArrayList<Integer>();
	
	public FinalScreen(MainScreen linkToApp, ArrayList<Integer> previous){
		previousS=previous;
		linkApp=linkToApp;
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gridbag);
		c.gridx=0;
		c.gridy=0;
		c.anchor=GridBagConstraints.CENTER;
		c.gridwidth=GridBagConstraints.REMAINDER;
		c.insets=new Insets(50,0,0,0);
		JLabel instructions = new JLabel("Your price is:");
		gridbag.setConstraints(instructions, c);
		add(instructions);
		
		c.insets=new Insets(0,0,0,0);
		
		
		
		c.anchor=GridBagConstraints.CENTER;
		c.gridx=0;
		c.gridy=1;
		c.weighty=30;
		c.insets=new Insets(100,140,50,50);
		c.gridwidth=GridBagConstraints.REMAINDER;
		c.fill=GridBagConstraints.BOTH;
		finalprice= new JLabel("<html><font size='40'>"+pricevar+"</font></html>");
		gridbag.setConstraints(finalprice, c);
		add(finalprice);
		
	
		
		
		
		c.insets=new Insets(0,0,0,0);
		c.weighty=0;
		c.gridy=2;
		
		c.gridx=0;
		c.gridwidth=1;
		c.anchor=GridBagConstraints.LAST_LINE_START;
		c.fill=GridBagConstraints.NONE;
		c.insets=new Insets(0,0,0,200);
		JButton back = new JButton("Back");
		gridbag.setConstraints(back, c);
		add(back);
		
		
		c.insets=new Insets(0,0,0,0);
		c.gridy=2;
		c.gridx=1;
		c.gridwidth=1;
		c.anchor=GridBagConstraints.LAST_LINE_END;
		c.fill=GridBagConstraints.NONE;
		JButton forward = new JButton("Forward");
		gridbag.setConstraints(forward, c);
		add(forward);

		
		//action listener for backward screens
		ActionListener clickBackward=new ActionListener(){
			public void actionPerformed(ActionEvent e){


				ArrayList<Integer> newHold=new ArrayList<Integer>();

					for (int x=0;x<previousS.indexOf(6);x++){
						newHold.add(previousS.get(x));
					}
	
				
			//	previousScreen.remove(previousScreen.size()-1);
				System.out.println(newHold);
				linkApp.setTracking(newHold);
				
				linkApp.changeScreen(previousS.get(previousS.size()-2),MainScreen.SCREEN_FINAL);
			}
		};
		back.addActionListener(clickBackward);
		
		
	}
}
