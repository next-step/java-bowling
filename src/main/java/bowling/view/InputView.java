package bowling.view;

import bowling.domain.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final int ONE = 1;
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputPlayer(int playerIndex) {
        System.out.println(String.format("플레이어%d의 이름은(3 english letters)?: ", playerIndex + ONE));
        return scanner.next();
    }

    public static int relaseBowling(Player player) {
        System.out.println(String.format("%s's turn : ", player.getName()));
        return scanner.nextInt();
    }

    public static int playerCount() {
        System.out.println("How many people?");
        return scanner.nextInt();
    }

    public static List<String> inputPlayers() {
        int playerCount = playerCount();

        List<String> players = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            players.add(inputPlayer(i));
        }
        return players;
    }
}
