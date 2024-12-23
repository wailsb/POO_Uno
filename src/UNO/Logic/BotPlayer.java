
package UNO.Logic;

public class BotPlayer extends Player {

    public BotPlayer(int id) {
        super(id);
    }

    @Override
    public Card playTurn(Card lastPlayedCard, Deck deck) {
       Card botCard=null;
        if (!this.canPlayCard(lastPlayedCard).isEmpty()) {
            botCard = chooseCardToPlay(lastPlayedCard);
            this.playCard(botCard);
            return botCard;
        } else {
            this.drawCard(deck);
            if (!this.canPlayCard(lastPlayedCard).isEmpty()) {
                botCard = canPlayCard(lastPlayedCard).get(0);
                this.playCard(botCard);
                return botCard;
            }
            return botCard;
        }
    }


    @Override
    public Card chooseCardToPlay(Card lastPlayedCard) {
        return super.canPlayCard(lastPlayedCard).get(0); // Select the first playable card.
    }
}
