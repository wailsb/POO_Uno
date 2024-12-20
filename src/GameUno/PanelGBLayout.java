package GameUno;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelGBLayout extends JPanel {

	private GridBagConstraints gbc;
	private String DirPath;
	private JFrame MainWindow;
	PanelGBLayout(JFrame Main){
		this.setLayout(new GridBagLayout());
		this.gbc=new GridBagConstraints();
		this.DirPath=System.getProperty("user.dir");
		this.MainWindow=Main;
	}
	GridBagConstraints getGBC() {
		return this.gbc;
	}
	String getDirPath() {
		return DirPath;
	}
	JFrame getMainWindow() {
		return this.MainWindow;
	}
	
	// GBC Setter Supercharge
		public void setGBC(int gx,int gy) {
			this.gbc.gridx=gx;
			this.gbc.gridy=gy;
		}
		
		public void setGBC(
				int gx,
				int gy,
				int width,
				int height
				){
			this.gbc.gridx = gx;       
			this.gbc.gridy = gy;
			this.gbc.gridwidth = width;
			this.gbc.gridheight = height;
		}
		
		public void setGBC(
				int gx,
				int gy,
				int width,
				int height,
				int weightX,
				int weightY
				){
			this.gbc.gridx = gx;        
			this.gbc.gridy = gy;        
			this.gbc.gridwidth = width; 
			this.gbc.gridheight = height;
			this.gbc.weightx = weightX; 
			this.gbc.weighty = weightY;
		}
		
		public void setGBC(
				int gx,
				int gy,
				int width,
				int height,
				int weightX,
				int weightY,
				int fill,
				int anchor
				) {
			this.gbc.gridx = gx;        // Column (x-coordinate) of the grid
			this.gbc.gridy = gy;        // Row (y-coordinate) of the grid
			this.gbc.gridwidth = width; // How many columns the component spans
			this.gbc.gridheight = height; // How many rows the component spans
			this.gbc.weightx = weightX; // Horizontal resizing weight
			this.gbc.weighty = weightY; // Vertical resizing weight
			this.gbc.fill = fill;      // How the component should resize
			this.gbc.anchor = anchor;
		}
}
