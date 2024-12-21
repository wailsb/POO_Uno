package GameUno;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class newGamePanel extends PanelGBLayout {
	private String Choices[]= {
			"2 players",
			"3 players",
			"4 players",
			"5 players",
			"6 players",
			"7 players",
			"8 players",
			"9 players"
			};
	newGamePanel(JFrame MainWindow){
		super(MainWindow);
		
		
		// Initialisation of components
		Button backMainBtn=new Button("Back To Main Menu");
		Button soloGameBtn=new Button("Solo");
		Button multyPlayer=new Button("MultiPlayer");
		JComboBox numberOfPlayers= new JComboBox(Choices);

		// Setting Buttons Background Colors
		backMainBtn.setBackground(Color.YELLOW);
		soloGameBtn.setBackground(Color.LIGHT_GRAY);
		multyPlayer.setBackground(Color.lightGray);
		// Setting Events for Btns
		backMainBtn.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e){
						BackEv();
					}
				});

		// Adding Spacers
		this.setGBC(0,0,3,1,1,1,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
		this.add(new JPanel(),this.getGBC());
		
		this.setGBC(0,1,1,6);
		this.add(new JPanel(),this.getGBC());

		this.setGBC(1,5,2,1);
		this.add(new JPanel(),this.getGBC());

		this.setGBC(2,1,1,4);
		this.add(new JPanel(),this.getGBC());

		// Adding Components
		this.setGBC(1,1,1,1,1,1,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER);
		this.add(backMainBtn,this.getGBC());

		this.setGBC(1,2,1,1,1,1,GridBagConstraints.BOTH,GridBagConstraints.CENTER);
		this.add(soloGameBtn,this.getGBC());
		
		this.setGBC(1,4,1,1);
		this.add(multyPlayer,this.getGBC());

		this.setGBC(1,3,1,1,1,1,GridBagConstraints.HORIZONTAL,GridBagConstraints.SOUTH);
		this.add(numberOfPlayers,this.getGBC());
		
	}
	void BackEv() {
		if(this.getMainWindow() != null) 
		{
			this.getMainWindow().remove(this);
			this.getMainWindow().add(new WelcomePanel(getMainWindow()));
			this.getMainWindow().revalidate();
			this.getMainWindow().repaint();
		}
	}

}
