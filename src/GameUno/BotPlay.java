package GameUno;

import logicGui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BotPlay extends PanelGBLayout {
    private final GameGui game; // Store the GameGui instance
    private final JLabel currentCardLabel; // Label to display the middle card
    private final JPanel playerCardPanel; // Panel to display the human player's cards
    private final JButton drawButton; // Button to draw a card
    private JLabel InfoLab; // Label to display player info

    BotPlay(JFrame MainWindow) {
        super(MainWindow);

        // Initialize the game with 2 players (1 human, 1 bot)
        this.game = new GameGui(1, 2);
        setGBC(0, 0, 3, 1, 1, 1);

        // Display the current card in the middle
        this.currentCardLabel = new JLabel();
        this.currentCardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        updateCurrentCardDisplay();
        this.add(currentCardLabel, getGBC());

        // Player card panel (only human player's cards)
        setGBC(0, 1, 3, 1, 1, 1);
        this.playerCardPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        updatePlayerCardDisplay();

        JScrollPane scrollPane = new JScrollPane(playerCardPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(256, 256));
        this.add(scrollPane, getGBC());

        // Player info
        setGBC(0, 2, 3, 1, 1, 0);
        JPanel playerInfoPanel = new JPanel();
        InfoLab = new JLabel("Player:" + (game.getCurrentPlayer().getId() + 1) + " | Cards Remaining: " + game.getCurrentPlayer().getHand().size());
        playerInfoPanel.add(InfoLab);
        this.add(playerInfoPanel, getGBC());

        // Draw button
        setGBC(0, 3, 3, 1, 1, 0);
        this.drawButton = new JButton("Draw Card");
        this.drawButton.setFont(new Font("Arial", Font.BOLD, 14));
        this.drawButton.setBackground(new Color(34, 193, 195)); // Light blue color
        this.drawButton.setForeground(Color.WHITE);
        this.drawButton.addActionListener(e -> handleDrawCard());
        this.add(drawButton, getGBC());

        // Window resizing
        int childWidth = this.getMainWindow().getWidth() - 100;
        scrollPane.setPreferredSize(new Dimension(childWidth, 256));
        this.getMainWindow().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                OnResizeEv(scrollPane);
            }
        });

        // Start the bot's turn if the current player is a bot
        if (game.getCurrentPlayer() instanceof BotPlayer) {
            botTurn();
        }
    }

    private void updateCurrentCardDisplay() {
        Card lastPlayedCard = game.getLastPlayedCard();
        this.currentCardLabel.setIcon(this.resizeIcon(
                getDirPath() + "\\src\\resources\\Cards\\" + game.cardToString(lastPlayedCard) + ".jpg",
                178, 256
        ));
    }

    private void updatePlayerCardDisplay() {
        playerCardPanel.removeAll();
        ArrayList<Card> cards = game.getCurrentPlayer().getHand();
        ArrayList<Card> playableCards = game.getCurrentPlayer().getPlayableCards(game.getLastPlayedCard());

        // Only update the human player's cards
        if (game.getCurrentPlayer() instanceof HumanPlayer) {
            for (int i = 0; i < cards.size(); i++) {
                int cardIndex = i; // Index of the card in the full hand
                JLabel label = new JLabel(this.resizeIcon(
                        getDirPath() + "\\src\\resources\\Cards\\" + game.cardToString(cards.get(i)) + ".jpg",
                        152, 225
                ));

                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        InfoLab.setText("Player:" + (game.getCurrentPlayer().getId() + 1) + " | Cards Remaining: " + game.getCurrentPlayer().getHand().size());

                        if (playableCards.contains(cards.get(cardIndex))) {
                            int playableIndex = playableCards.indexOf(cards.get(cardIndex));
                            ClickPlay(playableIndex);
                        } else {
                            JOptionPane.showMessageDialog(
                                    BotPlay.this.getMainWindow(),
                                    "This card is not playable.",
                                    "Invalid Move",
                                    JOptionPane.WARNING_MESSAGE
                            );
                        }
                    }
                });

                playerCardPanel.add(label);
            }
        }

        this.getMainWindow().revalidate();
        this.getMainWindow().repaint();
    }

    private void botTurn() {
        BotPlayer bot = (BotPlayer) game.getCurrentPlayer();
        ArrayList<Card> playableCards = bot.getPlayableCards(game.getLastPlayedCard());

        if (playableCards.isEmpty()) {
            // Bot has no playable cards, so it draws a card
            bot.drawCard(game.getDeck());
            Card drawnCard = bot.getHand().get(bot.getHand().size() - 1);

            if (drawnCard.isPlayable(game.getLastPlayedCard())) {
                // If the drawn card is playable, the bot plays it
                Card.Colors chosenColor = null;
                if (drawnCard instanceof ActionCard actionCard) {
                    if (actionCard.getAction() == ActionCard.Actions.Wild || actionCard.getAction() == ActionCard.Actions.DRAW_4_Wild) {
                        chosenColor = bot.chooseColor(); // Bot chooses a color for Wild cards
                    }
                }
                game.executeTurn(bot.getHand().indexOf(drawnCard), chosenColor != null ? chosenColor.name() : null);
            } else {
                // If the drawn card is not playable, the bot skips its turn
                game.moveToNextPlayer();
            }
        } else {
            // Bot has playable cards, so it chooses one randomly
            Card chosenCard = bot.chooseCard(game.getLastPlayedCard());
            Card.Colors chosenColor = null;

            if (chosenCard instanceof ActionCard actionCard) {
                if (actionCard.getAction() == ActionCard.Actions.Wild || actionCard.getAction() == ActionCard.Actions.DRAW_4_Wild) {
                    chosenColor = bot.chooseColor(); // Bot chooses a color for Wild cards
                }
            }

            // Bot plays the chosen card
            game.executeTurn(playableCards.indexOf(chosenCard), chosenColor != null ? chosenColor.name() : null);

            updateCurrentCardDisplay();
            updatePlayerCardDisplay();
        }

        // Update the middle card display after the bot's turn
        updateCurrentCardDisplay();

        // Check if the game is over
        checkGameOver();

        // If the game is not over and the next player is a bot, continue the bot's turn
        if (!game.isGameOver()) {
            if (game.getCurrentPlayer() instanceof BotPlayer) {
                botTurn();
            }
        }
    }

    private void ClickPlay(int playableIndex) {
        try {
            Player currentPlayer = game.getCurrentPlayer();
            ArrayList<Card> playableCards = currentPlayer.getPlayableCards(game.getLastPlayedCard());

            // If no playable cards, the player must draw a card
            if (playableCards.isEmpty()) {
                handleDrawCard();
            } else {
                // Play the selected card
                Card cardToPlay = playableCards.get(playableIndex);
                Card.Colors chosenColor = null;

                if (cardToPlay instanceof ActionCard actionCard) {
                    if (actionCard.getAction() == ActionCard.Actions.Wild || actionCard.getAction() == ActionCard.Actions.DRAW_4_Wild) {
                        chosenColor = promptForColor();
                    }
                }

                // Execute the turn with the correct index and chosen color
                game.executeTurn(playableIndex, chosenColor != null ? chosenColor.name() : null);

                // Update displays
                updateCurrentCardDisplay();
                updatePlayerCardDisplay();
            }

            // Check if the game is over
            checkGameOver();

            // Move to the next player (bot) if the game is not over
            if (!game.isGameOver()) {
                currentPlayer=game.getNextPlayer();
                if (game.getCurrentPlayer() instanceof BotPlayer) {
                    botTurn();

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.getMainWindow(), "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleDrawCard() {
        Player currentPlayer = game.getCurrentPlayer();
        currentPlayer.drawCard(game.getDeck()); // Draw a card from the deck
        Card drawnCard = currentPlayer.getHand().get(currentPlayer.getHand().size() - 1);

        // Check if the drawn card is playable
        if (drawnCard.isPlayable(game.getLastPlayedCard())) {
            JOptionPane.showMessageDialog(
                    this.getMainWindow(),
                    "You drew " + game.cardToString(drawnCard),
                    "Draw Card",
                    JOptionPane.INFORMATION_MESSAGE
            );

            updateCurrentCardDisplay();
            updatePlayerCardDisplay();
        } else {
            JOptionPane.showMessageDialog(
                    this.getMainWindow(),
                    "You drew " + game.cardToString(drawnCard) + ". It is not playable.",
                    "Draw Card",
                    JOptionPane.INFORMATION_MESSAGE
            );
            game.moveToNextPlayer();
        }

        // Update displays
        updateCurrentCardDisplay();
        updatePlayerCardDisplay();

        // Check if the game is over
        checkGameOver();

        // Move to the next player (bot) if the game is not over
        if (!game.isGameOver()) {
            if (game.getCurrentPlayer() instanceof BotPlayer) {
                botTurn();
            }
        }
    }

    private void checkGameOver() {
        if (game.isGameOver()) {
            Player winner = game.getWinner();
            JOptionPane.showMessageDialog(
                    this.getMainWindow(),
                    "Game Over! The winner is Player " + (winner.getId() + 1) + "!",
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE
            );
            // Optionally, you can reset the game or close the window here
        }
    }

    private Card.Colors promptForColor() {
        Object[] options = {"Red", "Blue", "Green", "Yellow"};
        int choice = JOptionPane.showOptionDialog(
                this.getMainWindow(),
                "Choose a color for the Wild card:",
                "Color Selection",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice >= 0) {
            return Card.Colors.valueOf(options[choice].toString()); // Ensure the case matches the enum
        } else {
            throw new IllegalStateException("No color selected");
        }
    }

    public void OnResizeEv(JScrollPane scrollPane) {
        if (this.getMainWindow() != null) {
            boolean isPanelInFrame = this.getMainWindow().getContentPane().isAncestorOf(this);
            if (isPanelInFrame) {
                int childWidth = this.getMainWindow().getWidth() - 50;
                scrollPane.setPreferredSize(new Dimension(childWidth, 256));
                this.getMainWindow().revalidate();
                this.getMainWindow().repaint();
            }
        }
    }
}