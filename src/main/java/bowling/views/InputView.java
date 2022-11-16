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
        int numberOfPlayers = Integer.parseInt(SCANNER.nextLine());
        if (numberOfPlayers <= 0) {
            throw new IllegalArgumentException("0 보다 큰 숫자를 입력해야 합니다.");
        }
        return numberOfPlayers;
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
