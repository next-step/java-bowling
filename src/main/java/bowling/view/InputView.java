package bowling.view;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_COUNT_PLAYER_MESSAGE = "How many people?";
    private static final String INPUT_NAME_MESSAGE = "플레이어 %d의 이름은(3 english letters)?: ";
    private static final String INPUT_PLAYER_TURN_MESSAGE = "%s's turn : ";

    public static String inputNameOfPlayer(int index) {
        System.out.printf(INPUT_NAME_MESSAGE, index);
        return scanner.nextLine();
    }

    public static int inputPitchingCount(String playerName) {
        System.out.print(System.lineSeparator());
        System.out.printf(INPUT_PLAYER_TURN_MESSAGE, playerName);
        return Integer.parseInt(scanner.nextLine());
    }

    public static List<String> inputPlayer() {
        int countOfPlayer = inputCountOfPlayer();
        List<String> names = new ArrayList<>();
        IntStream.rangeClosed(1, countOfPlayer)
                 .forEach(i -> names.add(inputNameOfPlayer(i)));
        return names;
    }

    private static int inputCountOfPlayer() {
        System.out.println(INPUT_COUNT_PLAYER_MESSAGE);
        return Integer.parseInt(scanner.nextLine());
    }
}
