
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
	private double pricevar=0.00;
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

		System.out.println("Package Type is "+linkApp.getTypePackage());

		if(linkApp.getTypePackage().equals("PackageFlatRate")){
			if(linkApp.getBoxType()==2){
				if(linkApp.getShipSpeed()==2)
					pricevar=50.00;
				else if(linkApp.getShipSpeed()==1)
					pricevar=40.00;
			}
			else if(linkApp.getBoxType()==1){
				if(linkApp.getShipSpeed()==2)
					pricevar=25.00;
				else if(linkApp.getShipSpeed()==1)
					pricevar=20.00;
			}
		}
		else if(linkApp.getTypePackage().equals("Package")){   //non-flatrate package
			//get zip code, weight, dimensions, and units
			int startzip=linkApp.getZipStart();
			int endzip=linkApp.getZipEnd();
			double weight=linkApp.getWeight();
			String weightunit=linkApp.getWeightUnit();
			
			double length=linkApp.getPackLength();
			double width=linkApp.getPackWidth();
			double height=linkApp.getPackHeight();
			String packUnit=linkApp.getPackUnit();
			
			//convert all lengths to inches
			if(packUnit.equals("Centimeters")){
				System.out.println("Converting lengths to inches...");
				length=length/2.54;
				width=width/2.54;
				height=height/2.54;
			}
			//estimate the distance between zip codes
			double distance=(double)(Math.abs(endzip-startzip));
			distance=(distance/99999.0)*2500; //formula to estimate distance based on difference in zip codes
			//check if package is considered "large" - any dimension over 12 inches
			boolean isLarge=false;
			if(length>12 || width >12 || height>12){
				isLarge=true;
			}
			
		}

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
