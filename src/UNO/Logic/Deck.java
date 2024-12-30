package Logic;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck;
    public Deck() {
        this.deck = new ArrayList<Card>();
        this.initializeDeck();
        this.shuffleDeck();
    }
    public void initializeDeck() {
        Card.Colors[] colors = Card.Colors.values();
        ActionCard.Actions[] actions = ActionCard.Actions.values();
        for (int i = 0;i < colors.length;i++) {
            if (colors[i] != Card.Colors.BLACK) {
                deck.add(new ValueCard(0, colors[i]));
                for (int j = 1; j <= 9; j++) {
                    deck.add(new ValueCard(j, colors[i]));
                    deck.add(new ValueCard(j, colors[i]));

                }
                for (int j = 0; j <= 2; j++) {
                    deck.add(new ActionCard(actions[j], colors[i]));
                    deck.add(new ActionCard(actions[j], colors[i]));

                }
            } else {
                for (int j = 3; j < actions.length; j++) {
                    deck.add(new ActionCard(actions[j], colors[i]));
                    deck.add(new ActionCard(actions[j], colors[i]));
                    deck.add(new ActionCard(actions[j], colors[i]));
                    deck.add(new ActionCard(actions[j], colors[i]));
                }
            }
        }
    }
    public Card drawCard() {
        return deck.removeLast();
    }
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }
    public ArrayList<Card> getDeck() {
        return this.deck;
    }
    public void addToDeck(Card card) {
        this.deck.add(card);
    }
}



