package bowling.ui;

import bowling.domain.Player;
import bowling.domain.Players;
import bowling.domain.pin.Pin;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_NAME_FORMAT = "플레이어 %d의 이름은?(3 english letters): ";
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static Players promptPlayers() {
        System.out.print("How many people? ");
        Players players = new Players();
        int playerCount = Integer.parseInt(SCANNER.nextLine());

        for (int number = 1; number <= playerCount; number++) {
            players.addPlayer(promptPlayerName(number));
        }

        return players;
    }

    private static Player promptPlayerName(int number) {
        System.out.printf(INPUT_NAME_FORMAT, number);
        return new Player(SCANNER.nextLine());
    }

    public static Pin promptPinFor(String name) {
        System.out.print(name + "'s turn: ");
        return Pin.of(Integer.parseInt(SCANNER.nextLine()));
    }
}
