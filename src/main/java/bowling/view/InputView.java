package bowling.view;

import bowling.domain.BowlingGames;
import bowling.domain.Pins;
import bowling.domain.Player;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class InputView {

    private static final String INPUT_PLAYER_NAME_MESSAGE_FORMAT = "플레이어 %d 이름은(3 english letters): ";

    private static final String INPUT_PLAYERS_COUNT_MESSAGE = "How many people? ";

    private static final String INPUT_CURRENT_PLAYER_PIN_COUNT_FORMAT = "%s's turn: ";

    private final Scanner scanner = new Scanner(System.in);

    public Player inputPlayerName(int count) {
        System.out.printf(INPUT_PLAYER_NAME_MESSAGE_FORMAT, count);
        return Player.create(readLine());
    }

    private String readLine() {
        return scanner.nextLine();
    }

    public Pins inputPins(BowlingGames bowlingGames) {
        System.out.printf(INPUT_CURRENT_PLAYER_PIN_COUNT_FORMAT, bowlingGames.currentPlayer());
        return Pins.create(readNumber());
    }

    private int readNumber() {
        return Integer.parseInt(readLine());
    }

    public int inputPlayerCount() {
        System.out.print(INPUT_PLAYERS_COUNT_MESSAGE);
        return Integer.parseInt(readLine());
    }

    public List<Player> inputPlayers(int playersCount) {
        int initPlayerCount = 1;
        return IntStream.rangeClosed(initPlayerCount, playersCount)
                .mapToObj(this::inputPlayerName)
                .collect(Collectors.toList());
    }

}
