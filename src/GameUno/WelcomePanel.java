package GameUno;

import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class WelcomePanel extends JPanel {

	private String IconPath=System.getProperty("user.dir")+"\\src\\resources\\Icon.png";
	
	WelcomePanel(){
		
		this.setLayout(new GridBagLayout());
		JPanel Spacer=new JPanel();
		GridBagConstraints gbc=new GridBagConstraints();
		Button newGameBtn=new Button("Start a new Game");
		Button loadOldGameBtn=new Button("Load old Game results");
		JLabel imgLabel=new JLabel(new ImageIcon(IconPath));
		gbc.fill=GridBagConstraints.BOTH;
		gbc.weightx=1.0;
		gbc.weighty=1.0;
		gbc.gridx=0;
		gbc.gridy=0;
		this.add(imgLabel,gbc);
		gbc.gridy=1;
		this.add(newGameBtn,gbc);
		gbc.gridy=2;
		this.add(loadOldGameBtn,gbc);
		gbc.weighty=0.5;
		gbc.gridy=3;
		this.add(Spacer,gbc);
		
	}
	

}
