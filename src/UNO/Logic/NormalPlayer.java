
package UNO.Logic;

import java.util.ArrayList;
import java.util.Scanner;

public class NormalPlayer extends Player {

    public NormalPlayer(int id) {
        super(id);
    }

    @Override
    public Card playTurn(Card lastPlayedCard, Deck deck) {
       Card normalPlayerCard =null;
        if (!this.canPlayCard(lastPlayedCard).isEmpty()) {
             normalPlayerCard = chooseCardToPlay(lastPlayedCard);
            this.playCard(normalPlayerCard);
            return normalPlayerCard;
        } else {
            this.drawCard(deck);
            if (!this.canPlayCard(lastPlayedCard).isEmpty()) {
                 normalPlayerCard = canPlayCard(lastPlayedCard).get(0);
                this.playCard(normalPlayerCard);
                return normalPlayerCard;
            }
            return normalPlayerCard;
        }
    }

    @Override
    public Card chooseCardToPlay(Card lastPlayedCard) {
         Scanner Scanner = new Scanner(System.in);

        ArrayList<Card> playableCard = new ArrayList<Card>();
        playableCard = this.canPlayCard(lastPlayedCard);

        
        System.out.println("Available cards :");
        for (int i = 0; i < playableCard.size(); i++) {
            if (playableCard.get(i) instanceof NonActionCard) {
                System.out.println("cart number " + i + " :" + "  this card is a Non action card : ("
                        + playableCard.get(i).getColor() + " , " + ((NonActionCard) playableCard.get(i)).getValue() + ")");
            } else if (playableCard.get(i).getColor().equals("BLACK")) {
                System.out.println("cart number " + i + " :" + " this card is a action card : ("
                        + ((ActionCard) playableCard.get(i)).getAction() + ")");
            } else {
                System.out.println("cart number " + i + " :" + "  this card is a action card : ("
                        + playableCard.get(i).getColor() + " , " + ((ActionCard) playableCard.get(i)).getAction() + ")");

            }
        }
        System.out.printf("Select the card number you want to play.");
        int j = Scanner.nextInt();

        return playableCard.get(j);
    }
}