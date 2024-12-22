package GameUno;
import javax.swing.*;

import java.awt.*;

public class MultiPlayer extends PanelGBLayout {
    private int PlayerNumber;

    MultiPlayer(JFrame MainWindow, int PlayerNumber) {
        super(MainWindow);
        setGBC(0, 0, 3, 1, 1, 1);
        JLabel currentCardLabel = new JLabel("Current Card");
        currentCardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentCardLabel.setIcon(new ImageIcon(getDirPath() + "\\src\\resources\\Icon.png"));
        this.add(currentCardLabel, getGBC());

        setGBC(0, 1, 3, 1, 1, 1);
        JPanel playerCardPanel = new JPanel();
        playerCardPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        playerCardPanel.add(new JLabel("Player's Cards: "));
        playerCardPanel.add(new JLabel(new ImageIcon(getDirPath() + "\\src\\resources\\Icon.png")));
        playerCardPanel.add(new JLabel(new ImageIcon(getDirPath() + "\\src\\resources\\Icon.png")));
        playerCardPanel.add(new JLabel(new ImageIcon(getDirPath() + "\\src\\resources\\Icon.png")));
        playerCardPanel.add(new JLabel(new ImageIcon(getDirPath() + "\\src\\resources\\Icon.png")));

        JScrollPane scrollPane = new JScrollPane(playerCardPanel);
        scrollPane.setPreferredSize(new Dimension(500, 256));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        setGBC(0, 1, 3, 1, 1, 1);
        this.add(scrollPane, getGBC());

        setGBC(0, 2, 3, 1, 1, 0);
        JPanel playerInfoPanel = new JPanel();
        playerInfoPanel.add(new JLabel("Player: wail sari bey | Cards Remaining: 4"));
        this.add(playerInfoPanel, getGBC());
    }
}
