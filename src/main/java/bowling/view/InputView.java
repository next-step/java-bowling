package bowling.view;

import bowling.domain.PlayerName;
import bowling.frame.Round;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class InputView {

    public static final int ONE_INDEX = 1;

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<PlayerName> inputPlayerName() {
        List<PlayerName> playerNames = new ArrayList<>();

        int playerCount = inputPlayerCount();

        IntStream.rangeClosed(1, playerCount).forEach(number -> {
            System.out.print("플레이어 " + number + "의 이름은?(3 english letters): ");
            playerNames.add(PlayerName.from(inputScannerString()));
        });

        return playerNames;
    }

    private int inputPlayerCount() {
        System.out.print("How many People? : ");
        return Integer.parseInt(inputScannerString());
    }

    private String inputScannerString() {
        return scanner.nextLine();
    }

    public int inputShootScore(String currentPlayer) {
        System.out.print(currentPlayer + "'s turn : ");
        return Integer.parseInt(inputScannerString());
    }
}
