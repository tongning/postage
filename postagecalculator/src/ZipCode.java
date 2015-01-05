

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



/**
 * ZipCode is a JPanel object that presents two JTextFields for 5-digit
 * ZIP code entry - one for the sender ZIP code, and one for the recipient
 * ZIP code. These ZIP codes are used to estimate the distance between the
 * sender and recipient.
 * @author Karina Chang, Anthony Li
 *
 */
public class ZipCode extends JPanel {
	private JLabel instructions;
	private JPanel textFields;
	private JPanel backForward;

	//entering in the zipcodes
	private JTextField senderZip;
	private JTextField recipientZip;
	private MainScreen linkApp;

	//belonging in backForward, which live in the south
	private JButton back;
	private JButton forward;
	private ArrayList<Integer> previousScreen=new ArrayList<Integer>();

	public int getPreviousScreen() {
		return previousScreen.get(previousScreen.size()-2);
	}

	public void setPreviousScreen(ArrayList<Integer> previousScreen) {
		this.previousScreen = previousScreen;

	}

	public ZipCode(MainScreen linkToApp,ArrayList<Integer> previous){

		super();

		setPreviousScreen(previous);
		linkApp=linkToApp; 
		setFocusable(true);
		setVisible(true);
		setLayout(new BorderLayout());

		instructions=new JLabel("Enter ZIP codes:  ");
		add(instructions,BorderLayout.NORTH);
		textFields = new JPanel();

		textFields.setLayout(new BoxLayout(textFields,BoxLayout.PAGE_AXIS));
		senderZip=new JTextField("Sender Zip Code");
		recipientZip=new JTextField("Recipient Zip Code");

		senderZip.setMaximumSize(new Dimension(200,50));
		recipientZip.setMaximumSize(new Dimension(200,50));
		textFields.add(senderZip);
		textFields.add(recipientZip);

		/**FOR THE BACK AND FORWARD BUTTONS**/
		//back and forward buttons inside the backFoward panel
		backForward=new JPanel();
		backForward.setLayout(new BoxLayout(backForward,BoxLayout.LINE_AXIS));
		back=new JButton("Back");
		forward=new JButton("Forward");
		backForward.add(back);
		backForward.add(forward);

		add(textFields,BorderLayout.CENTER);
		add(backForward,BorderLayout.SOUTH);

		senderZip.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(senderZip.getText().trim().equals("Sender Zip Code")){
					senderZip.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(senderZip.getText().trim().equals("")){
					senderZip.setText("Sender Zip Code");
				}
			}

		});

		recipientZip.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(recipientZip.getText().trim().equals("Recipient Zip Code")){
					recipientZip.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(recipientZip.getText().trim().equals("")){
					recipientZip.setText("Recipient Zip Code");
				}
			}

		});

		System.out.println(linkApp.getTypePackage());
		//action listener for forward screens 
		ActionListener clickForward=new ActionListener(){
			public void actionPerformed(ActionEvent e){

				boolean checksPassed=true;
				try{
					int sender=Integer.parseInt(senderZip.getText());
					int rec=Integer.parseInt(recipientZip.getText());
				}
				catch(Exception error){
					checksPassed=false;
				}
				if(!(senderZip.getText().trim().length()==5))checksPassed=false;
				if(!(recipientZip.getText().trim().length()==5))checksPassed=false;
				if(checksPassed){
					//System.out.println("You are "+e.getActionCommand());


					//set zip code variables on mainscreen when next is clicked
					linkApp.setZipStart(Integer.parseInt(senderZip.getText()));
					linkApp.setZipEnd(Integer.parseInt(recipientZip.getText()));
					linkApp.changeScreen(MainScreen.SCREEN_FINAL,MainScreen.SCREEN_ZIP);
				}
				else{
					instructions.setText("<html><font color='#FF0000'>Input error.<br>Please check your input<br>and try again.</font></html>");
				}
			}//
		};

		//action listener for backward screens
		ActionListener clickBackward=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//System.out.println("You are "+e.getActionCommand());


				//System.out.println(previousScreen);
				ArrayList<Integer> newHold=new ArrayList<Integer>();
				if (previousScreen.indexOf(4)!=previousScreen.lastIndexOf(4)){
					for (int x=0;x<previousScreen.indexOf(4);x++){
						newHold.add(previousScreen.get(x));
					}

				}
				//	previousScreen.remove(previousScreen.size()-1);
			//	System.out.println(newHold);
				linkApp.setTracking(newHold);

				linkApp.changeScreen(getPreviousScreen(),MainScreen.SCREEN_ZIP);
			}
		};
		back.addActionListener(clickBackward);
		forward.addActionListener(clickForward);


	}//
}
