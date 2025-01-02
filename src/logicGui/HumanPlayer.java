package logicGui;

import java.util.ArrayList;

public class HumanPlayer extends Player {

    public HumanPlayer(int id) {
        super(id);
    }

    @Override
    public Card.Colors chooseColor() {
        // Default to Red if no color is chosen
        return Card.Colors.Red;
    }

    public Card.Colors chooseColor(String color) {
        try {
            return Card.Colors.valueOf(color); // Ensure the case matches the enum
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid color. Please provide Red, Green, Blue, or Yellow.");
        }
    }

    public Card chooseCard(Card lastPlayableCard) {
        // Get playable cards based on the last played card.
        ArrayList<Card> playableCards = this.getPlayableCards(lastPlayableCard);

        // Ensure the choice is valid.
        if (this.getChoice() < 0 || this.getChoice() >= playableCards.size()) {
            throw new IllegalArgumentException("Invalid choice index. Please provide a valid index.");
        }
        return playableCards.get(this.getChoice());
    }
}
