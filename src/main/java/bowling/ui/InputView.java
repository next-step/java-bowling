package bowling.ui;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {
    private final static String GET_PLAYER_COUNT_MESSAGE = "How many people?";
    private final static String GET_NAME_MESSAGE = "플레이어 %d 의 이름은? (3 english letters): ";
    private final static String SHOT_FORMAT = "%s's turn : ";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> getPlayers() {
        return IntStream.rangeClosed(1, getPlayerCount())
                .mapToObj(this::getPlayer)
                .collect(Collectors.toList());
    }

    private int getPlayerCount() {
        System.out.print(GET_PLAYER_COUNT_MESSAGE);
        return Integer.parseInt(scanner.nextLine());
    }

    private String getPlayer(int idx) {
        System.out.print(String.format(GET_NAME_MESSAGE, idx));
        return scanner.nextLine();
    }

    public int getShot(String currentPlayerName) {
        System.out.print(String.format(SHOT_FORMAT, currentPlayerName));
        return Integer.parseInt(scanner.nextLine());
    }
}
