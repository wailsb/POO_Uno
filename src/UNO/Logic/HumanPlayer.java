package Logic;


import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Player {
    public HumanPlayer(int id) {
        super(id);
    }

    @Override
    public Card.Colors chooseColor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("PLAYER " + this.getId() + " Choose A Color Red Or Green Or Blue");
        return Card.Colors.valueOf(scanner.nextLine().toUpperCase());
    }
    @Override
    public Card chooseCard(Card lastPlayableCard) {
        ArrayList<Card> playableCards = this.getPlayableCards(lastPlayableCard);
        System.out.println("Your Playable Cards :");
        for (int i = 0; i < playableCards.size(); i++) {
            String color = playableCards.get(i).getColor().toString();
            String symbol = playableCards.get(i) instanceof ActionCard
                    ? ((ActionCard) (playableCards.get(i))).getAction().name()
                    : String.valueOf(((ValueCard) (playableCards.get(i))).getValue());
            System.out.println("+----" + i + "----+");
            System.out.printf("| %-10s |\n", playableCards.get(i).getColor());
            System.out.println("|          |");
            System.out.printf("|    %-5s  |\n", symbol);
            System.out.println("|          |");
            System.out.println("+----------+");
        }
        System.out.println("Choose A Card From Your Playable Cards :");
        int choice;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose A Card By Its Number (0 to " + (playableCards.size() - 1) + ")");
            choice = scanner.nextInt();
            if (choice >= 0 && choice <= playableCards.size() - 1) {
                break;
            }
            System.out.println("Invalid Choice. Try again.");
        }
        return playableCards.get(choice);
    }
}