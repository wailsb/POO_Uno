package UNO.Logic;
import java.util.ArrayList;

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
                deck.add(new NonActionCard(0, colors[i]));
                for (int j = 1; j <= 9; j++) {
                    deck.add(new NonActionCard(j, colors[i]));
                    deck.add(new NonActionCard(j, colors[i]));

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
    public Card drawDeck() {
        return deck.removeLast();
    }
    public int getDeckSize() {
        return deck.size();
    }
    public void shuffleDeck() {
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        ArrayList<Integer> ArrayIndex = new ArrayList<Integer>();
        while (!(tempDeck.size() == deck.size())) {
            int idx = (int) Math.floor(Math.random() * deck.size());
            if (!ArrayIndex.contains(idx)) {
                ArrayIndex.add(idx);
                tempDeck.add(deck.get(idx));
            }
        }
        deck = tempDeck;
    }
    public ArrayList<Card> getDeck() {
        return this.deck;
    }

    public void addToDeck(Card card) {
        this.deck.add(card);
    }
}



