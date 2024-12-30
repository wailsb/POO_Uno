package Logic;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Pls Enter How Many HumanPlayer : ");
        Scanner sc = new Scanner(System.in);
        int numberOfHumanPlayers = sc.nextInt();
        System.out.print("Pls Enter How Many BotPlayers : ");
        int numberOfBotPlayers = sc.nextInt();
        Game game = new Game(numberOfHumanPlayers + numberOfBotPlayers ,numberOfHumanPlayers);
        while (!game.isGameOver()) {
            game.executeTurn();
        }

    }
}
