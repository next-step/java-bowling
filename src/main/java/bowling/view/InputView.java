package bowling.view;

import bowling.domain.Pins;
import bowling.domain.Player;
import bowling.domain.Players;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class InputView {
    private static final String INPUT_PLAYERS_SIZE_MESSAGE = "How many people? ";
    private static final String INPUT_PLAYER_NAME_MESSAGE = "플레이어 %d의 이름은(3 english letters)?: ";
    private static final String INPUT_FALLEN_PINS_MESSAGE = "%s's turn : : ";

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static Players inputPlayersInformation() {
        System.out.print(INPUT_PLAYERS_SIZE_MESSAGE);
        int size = SCANNER.nextInt();
        SCANNER.nextLine();
        return Players.from(players(size));
    }

    private static List<Player> players(int size) {
        return IntStream.rangeClosed(1, size)
                .mapToObj(InputView::player)
                .collect(Collectors.toList());
    }

    private static Player player(int index) {
        System.out.printf(INPUT_PLAYER_NAME_MESSAGE, index);
        return Player.create(SCANNER.nextLine());
    }

    public static Pins inputFallenPins(String playerName) {
        System.out.printf(INPUT_FALLEN_PINS_MESSAGE, playerName);
        return Pins.create(Integer.parseInt(SCANNER.nextLine()));
    }
}
