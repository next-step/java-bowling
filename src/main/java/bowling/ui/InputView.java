package bowling.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String NUMBER_OF_PLAYER = "How many people? ";
    private static final String PLAYER_NAME = "플레이어 %d의 이름은(3 english letters)?: ";
    private static final String TURN = "\n\n%s's turn : ";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {

    }

    public static List<String> InputPlayers() {
        int numberOfPlayer = inputNumberOfPlayers();

        List<String> names = new ArrayList<>();
        for (int i = 1; i <= numberOfPlayer; i++) {
            names.add(inputPlayerName(i));
        }
        return names;
    }

    private static int inputNumberOfPlayers() {
        System.out.print(NUMBER_OF_PLAYER);
        return Integer.parseInt(scanner.nextLine());
    }

    private static String inputPlayerName(int numberOfPlayer) {
        System.out.printf(PLAYER_NAME, numberOfPlayer);
        return scanner.nextLine();
    }

    public static int nextFallenPin(String name) {
        System.out.printf(TURN, name);
        return Integer.parseInt(scanner.nextLine());
    }
}
