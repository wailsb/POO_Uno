package GameUno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MultiPlayer extends PanelGBLayout {
    private int PlayerNumber;

    MultiPlayer(JFrame MainWindow, int PlayerNumber) {
        super(MainWindow);

        setGBC(0, 0, 3, 1, 1, 1);
        JLabel currentCardLabel = new JLabel();
        currentCardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentCardLabel.setIcon(this.resizeIcon(getDirPath() + "\\src\\resources\\Cards\\Wild.jpg",178,256));

        this.add(currentCardLabel, getGBC());

        setGBC(0, 1, 3, 1, 1, 1);
        JPanel playerCardPanel = new JPanel();
        playerCardPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        String[] Cards={
                "Yellow_0.jpg" ,
                "Blue_1.jpg" ,
                "Red_2.jpg" ,
                "Green_3.jpg"
        };
        for(int i=0;i<4;i++){
            JLabel label =new JLabel(this.resizeIcon(getDirPath() + "\\src\\resources\\Cards\\"+Cards[i],152,225));
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle the mouse click event
                    ClickPlay();
                }
            });
            playerCardPanel.add(label);

        }

        JScrollPane scrollPane = new JScrollPane(playerCardPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(256, 256));

        setGBC(0, 1, 3, 1, 1, 1);
        this.add(scrollPane, getGBC());
        setGBC(0, 2, 3, 1, 1, 0);
        JPanel playerInfoPanel = new JPanel();
        playerInfoPanel.add(new JLabel("Player: wail sari bey | Cards Remaining: 4"));
        this.add(playerInfoPanel, getGBC());
        int ChildW=this.getMainWindow().getWidth()-100;
        scrollPane.setPreferredSize(new Dimension(ChildW, 256));
        this.getMainWindow().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                OnResizeEv(scrollPane);
            }
        });
    }
    public void OnResizeEv(JScrollPane scrollPane){
        if(this.getMainWindow() != null){
            boolean isPanelInFrame = this.getMainWindow().getContentPane().isAncestorOf(this);
            if(isPanelInFrame){
                int ChildW=this.getMainWindow().getWidth()-50;
                scrollPane.setPreferredSize(new Dimension(ChildW, 256));
                this.getMainWindow().revalidate();
                this.getMainWindow().repaint();
            }
        }
    }
    public void ClickPlay(){
        if(this.getMainWindow() != null){
            this.getMainWindow().remove(this);
            this.getMainWindow().add(new PlayerTurnWindow());
            this.getMainWindow().revalidate();
            this.getMainWindow().repaint();
        }
    }

}
