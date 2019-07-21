package bowling.io;

import bowling.model.Player;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static int askCountOfPlayer() {
        System.out.println("How many people?");
        int countOfPlayer = scanner.nextInt();
        scanner.nextLine();
        return countOfPlayer;
    }

    public static List<String> askOfPlayerNames(int countOfPlayer) {
        return IntStream.rangeClosed(1, countOfPlayer)
                .mapToObj(InputView::askOfPlayerName)
                .collect(toList());
    }

    private static String askOfPlayerName(int num) {
        System.out.print(String.format("플레이어 %d의 이름은?(3 english letters)?", num));
        return scanner.nextLine();
    }

    public static int askCountOfDownPins(Player player) {
        System.out.print(String.format("%s's turn : ", player));
        int countOfDownPins = scanner.nextInt();
        scanner.nextLine();
        return countOfDownPins;
    }
}
