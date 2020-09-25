package bowling.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static bowling.ui.BowlingGameDisplay.display;

public final class BowlingGameInput {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int ONE = 1;

    public static int inputHowManyPeople(){
        display("How many people? ");
        return scanner.nextInt();
    }

    public static String inputPlayer(int playerIndex) {
        display(String.format("플레이어%d의 이름은(3 english letters)?: ", playerIndex + ONE));
        return scanner.next();
    }

    public static List<String> inputPlayers() {
        int playerCount = inputHowManyPeople();
        List<String> players = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            players.add(inputPlayer(i));
        }
        return players;
    }

    public static int inputPlayerFallenPins(String playerName) {
        display(String.format("%s's turn : ", playerName));
        return scanner.nextInt();
    }
}
