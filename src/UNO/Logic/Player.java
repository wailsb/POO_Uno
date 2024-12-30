package Logic;
import java.util.ArrayList;

public abstract class Player {
    private final int id;
    private final ArrayList<Card> hand;
    private Card.Colors forcedColor;
    public Player(int id) {
        this.id = id;
        this.hand = new ArrayList<>();
        this.forcedColor = null;
    }
    public int getId() {
        return this.id;
    }
    public ArrayList<Card> getHand() {
        return this.hand;
    }
    public Card.Colors getForcedColor() {
        return this.forcedColor;
    }
    public void setForcedColor(Card.Colors forcedColor) {
        this.forcedColor = forcedColor;
    }

    public void drawCard(Deck deck) {
        this.hand.add(deck.drawCard());
    }

    public ArrayList<Card> getPlayableCards(Card lastPlayedCard) {
        ArrayList<Card> playableCards = new ArrayList<>();
        for (int i = 0; i < this.hand.size(); i++) {
            if (forcedColor == null) {
                if (this.hand.get(i).isPlayable(lastPlayedCard)) {
                    playableCards.add(this.hand.get(i));
                }
            } else {
                if (this.hand.get(i).getColor() == forcedColor) {
                    playableCards.add(this.hand.get(i));
                }
            }
        }
        return playableCards;
    }
    public boolean hasPlayableCard(Card lastPlayedCard) {
        return !this.getPlayableCards(lastPlayedCard).isEmpty();
    }
    public abstract Card.Colors chooseColor();
    public abstract Card chooseCard(Card lastPlayedCard);
    public Card play(Card lastPlayedCard) {
        Card choosedCard = chooseCard(lastPlayedCard);
        this.hand.remove(choosedCard);
        return choosedCard;
    }
}