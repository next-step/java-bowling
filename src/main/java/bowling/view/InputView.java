package bowling.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private static final String INPUT_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_PITCH = "`s turn :";
    private static final String INPUT_PLAYER_COUNT = "How many people? ";

    private InputView() {
    }

    public static int getInputPitch(String name) {
        System.out.printf(name + INPUT_PITCH);
        return scanner.nextInt();
    }

    public static int getInputPlayerCount() {
        System.out.printf(INPUT_PLAYER_COUNT);
        return scanner.nextInt();
    }

    public static List<String> getInputPlayerNames(int count) {
        return IntStream.range(0, count)
                .mapToObj(s -> getInputPlayerName())
                .collect(Collectors.toList());
    }

    private static String getInputPlayerName() {
        System.out.print(INPUT_PLAYER_NAME);
        return scanner.next();
    }
}
