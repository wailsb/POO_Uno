
package GameUno;

public class BotPlayer extends Player {

    public BotPlayer(int id) {
        super(id);
    }

    @Override
    public Card playTurn(Card lastPlayedCard, Deck deck) {
       Card Card =new Card();
        if (!super.canPlayCard(lastPlayedCard).isEmpty()) {
             Card = chooseCardToPlay(lastPlayedCard);
            super.playCard(Card);
            return Card;
        } else {
            super.drawCard(deck);
            if (!super.canPlayCard(lastPlayedCard).isEmpty()) {
                 Card = canPlayCard(lastPlayedCard).get(0);
                super.playCard(Card);
                return Card;
            }
            return  Card;
        }
    }


    @Override
    public Card chooseCardToPlay(Card lastPlayedCard) {
        return super.canPlayCard(lastPlayedCard).get(0); // Select the first playable card.
    }
}
