package bowling.view;

import bowling.domain.Ball;
import bowling.domain.Player;
import bowling.domain.State;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public final class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
        throw new AssertionError();
    }

    public static List<Player> players() {
        System.out.println("How many people? ");
        int size = SCANNER.nextInt();
        SCANNER.nextLine();
        return createPlayers(size);
    }

    private static List<Player> createPlayers(int size) {
        return IntStream.rangeClosed(1, size).mapToObj(index -> {
            System.out.printf("플레이어 %d의 이름은?(3 english letters): ", index);
            String name = SCANNER.nextLine();
            return new Player(name);
        }).collect(toList());
    }

    public static Ball fallenPins(String playerName) {
        System.out.printf("%s's turn : ", playerName);
        int pins = SCANNER.nextInt();
        return Ball.of(pins, State.READY);
    }
}
