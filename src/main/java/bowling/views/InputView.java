package bowling.views;

import bowling.domain.Player;
import bowling.domain.Players;

import java.util.Scanner;
import java.util.stream.IntStream;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static Players inputPlayers() {
        int numberOfPlayers = InputView.inputNumberOfPlayers();
        Players players = new Players();
        IntStream.range(0, numberOfPlayers)
                .forEach(i -> players.add(new Player(InputView.inputPlayerName())));
        return players;
    }

    private static int inputNumberOfPlayers() {
        System.out.print("How many people? ");
        return Integer.parseInt(SCANNER.nextLine());
    }

    private static String inputPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return SCANNER.nextLine();
}

    public static int inputNumberOfPinsHit(String playerName) {
        System.out.printf("%s's turn : ", playerName);
        return Integer.parseInt(SCANNER.nextLine());
    }

}
