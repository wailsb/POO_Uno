package GameUno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MultiPlayer extends PanelGBLayout {
    private int PlayerNumber;
    private Deck MainDeck;
    private JLabel turnMessageLabel;
    private JButton readyButton;
    
    MultiPlayer(JFrame MainWindow, int PlayerNumber) {
        super(MainWindow);
        this.PlayerNumber = PlayerNumber;

        setGBC(0, 0, 3, 1, 1, 1);
        JLabel currentCardLabel = new JLabel();
        currentCardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentCardLabel.setIcon(this.resizeIcon(getDirPath() + "\\src\\resources\\Cards\\Wild.jpg", 178, 256));

        this.add(currentCardLabel, getGBC());

        setGBC(0, 1, 3, 1, 1, 1);
        JPanel playerCardPanel = new JPanel();
        playerCardPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        playerCardPanel.add(new JLabel());
        playerCardPanel.add(new JLabel(this.resizeIcon(getDirPath() + "\\src\\resources\\Cards\\Yellow_0.jpg", 152, 225)));
        playerCardPanel.add(new JLabel(this.resizeIcon(getDirPath() + "\\src\\resources\\Cards\\Blue_1.jpg", 152, 225)));
        playerCardPanel.add(new JLabel(this.resizeIcon(getDirPath() + "\\src\\resources\\Cards\\Red_2.jpg", 152, 225)));
        playerCardPanel.add(new JLabel(this.resizeIcon(getDirPath() + "\\src\\resources\\Cards\\Green_3.jpg", 152, 225)));

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

        // Message notification for Player 2's turn
        turnMessageLabel = new JLabel("It's Player " + PlayerNumber + "'s turn. Click 'Ready' to continue.", SwingConstants.CENTER);
        turnMessageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        turnMessageLabel.setForeground(Color.RED);

        setGBC(0, 3, 3, 1, 1, 1);
        this.add(turnMessageLabel, getGBC());

        // Ready button for player to click
        readyButton = new JButton("Ready");
        readyButton.setFont(new Font("Arial", Font.BOLD, 14));
        readyButton.setBackground(new Color(0, 255, 0)); // Green button
        readyButton.setForeground(Color.BLACK);

        readyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleReadyButtonClick();
            }
        });

        setGBC(0, 4, 3, 1, 1, 1);
        this.add(readyButton, getGBC());

        this.OnResizeEv(scrollPane);
        this.getMainWindow().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                OnResizeEv(scrollPane);
            }
        });
    }

    public void OnResizeEv(JScrollPane scrollPane) {
        if (this.getMainWindow() != null) {
            boolean isPanelInFrame = this.getMainWindow().getContentPane().isAncestorOf(this);
            if (isPanelInFrame) {
                int ChildW = this.getMainWindow().getWidth() - 50;
                scrollPane.setPreferredSize(new Dimension(ChildW, 256));
                this.getMainWindow().revalidate();
                this.getMainWindow().repaint();
            }
        }
    }

    private void handleReadyButtonClick() {
        // Handle the button click logic
        turnMessageLabel.setText("Player " + PlayerNumber + " has clicked 'Ready'. Proceeding to next action...");
        readyButton.setEnabled(false); // Disable the button after click
        
    }
}
