package UNO.Logic;

import java.util.ArrayList;

public abstract class Player {
    private final int id;
    private final ArrayList<Card> cards;

    public Player(int id) {
        this.id = id;
        this.cards = new ArrayList<>();
    }

    public void drawCard(Deck deck) {
        this.cards.add(deck.drawDeck());
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void playCard(Card card) {
        this.cards.remove(card);
    }

    public ArrayList<Card> canPlayCard(Card lastPlayedCard) {
        ArrayList<Card> playableCards = new ArrayList<>();
        for (Card card : cards) {
            if (card.isPlayable(lastPlayedCard)) {
                playableCards.add(card);
            }
        }
        return playableCards;
    }

    public int getId() {
        return id;
    }

  
    public abstract Card playTurn(Card lastPlayedCard, Deck deck);

    public abstract Card chooseCardToPlay(Card lastPlayedCard);
}