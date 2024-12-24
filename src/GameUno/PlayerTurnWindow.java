package GameUno;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerTurnWindow extends JFrame {
    public PlayerTurnWindow() {
        // Set up the window
        setTitle("Player 2 Turn");
        setSize(300, 200);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Add a label with a message
        JLabel messageLabel = new JLabel("It's Player 2's turn!", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(messageLabel, BorderLayout.CENTER);

        // Add "Ready" button
        JButton readyButton = new JButton("Ready");
        readyButton.setFont(new Font("Arial", Font.PLAIN, 14));
        add(readyButton, BorderLayout.SOUTH);

        // Add action listener to the button
        readyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the window when the button is clicked
                dispose();
            }
        });
    }

    // Method to show the window
    public void showWindow() {
        setVisible(true);
    }
}
