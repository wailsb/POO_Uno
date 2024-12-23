package Logic;
import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;
    public Deck() {
        this.deck = new ArrayList<Card>();
        String[] colors = {"RED","YELLOW","BLUE","GREEN","BLACK"};
        String[] actions = {"SKIP","REVERSE","DRAW_TWO","WILD","DRAW_FOUR_WILD"};
        for (String color : colors) {
            if (!color.equals("BLACK")) {
                deck.add(new NonActionCard(0, color));
                for (int j = 1; j <= 9; j++) {
                    for (int k = 0; k < 2; k++) {
                        deck.add(new NonActionCard(j, color));
                    }
                }
                for (int j = 0; j <= 2; j++) {
                    for (int k = 0; k < 2; k++) {
                        deck.add(new ActionCard(actions[j], color));
                    }
                }
            } else {
                for (int j = 3; j < actions.length; j++) {
                    for (int k = 0; k < 4; k++) {
                        deck.add(new ActionCard(actions[j], color));
                    }
                }
            }
        }
        this.shuffleDeck();
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



