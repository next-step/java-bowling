package bowling.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingConsoleInputView {

    private final Scanner scanner;

    public BowlingConsoleInputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public int enterPlayerNumber() {
        System.out.println("How many people?");
        return Integer.parseInt(scanner.nextLine());
    }

    public String enterPlayer() {
        System.out.println("플레이어 이름은(3 english letters)?:");
        return scanner.nextLine();
    }

    public List<String> enterPlayers(final int playerNumber) {
        return IntStream.range(0, playerNumber)
                .mapToObj(i -> enterPlayer())
                .collect(Collectors.toList());
    }

    public String enterScore(final String name) {
        System.out.printf("%s's turn : ", name);
        return scanner.nextLine();
    }
}
