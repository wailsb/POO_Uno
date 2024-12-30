package Logic;


import java.util.ArrayList;

public class BotPlayer extends Player {
    public BotPlayer(int id) {
        super(id);
    }
    @Override
    public Card.Colors chooseColor() {
        return Card.Colors.values()[(int) (Math.random() * Card.Colors.values().length)];
    }
    public Card chooseCard(Card lastPlayedCard) {
        ArrayList<Card> cards = this.getPlayableCards(lastPlayedCard);
        return cards.get((int) (Math.random() * cards.size()));
    }
}