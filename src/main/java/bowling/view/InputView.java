package bowling.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String PLAYER_COUNT_INPUT = "How many people? ";
    private static final String PLAYER_NAMES_INPUT = "플레이어 %d의 이름은(3 english letters)?: ";
    private static final String INPUT_PINS_MESSAGE = "%s's turn : ";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static int inputPlayerCount() {
        System.out.print(PLAYER_COUNT_INPUT);
        return Integer.parseInt(scanner.nextLine());
    }

    public static List<String> inputPlayerNames(int count) {
        List<String> names = new ArrayList<>();
        for (int playerNumber = 1; playerNumber <= count; playerNumber++) {
            System.out.printf((PLAYER_NAMES_INPUT) + "%n", playerNumber);
            names.add(scanner.nextLine());
        }
        return names;
    }

    public static int inputPins(String name) {
        System.out.printf(INPUT_PINS_MESSAGE, name);
        return Integer.parseInt(scanner.nextLine());
    }
}
