package bowling.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private static final String INPUT_HOW_MANY_PEOPLE = "How many people? ";
    private static final String INPUT_PLAYER_NAME = "플레이어 %d의 이름은(3 english letters)?: ";
    private static final String INPUT_PLAYER_TURN = "%s's turn : ";

    private String inputStringValue() {
        return scanner.nextLine();
    }

    private int inputIntValue() {
        int intValue = scanner.nextInt();
        scanner.nextLine();
        return intValue;
    }

    public List<String> inputPlayerNames() {
        System.out.print(INPUT_HOW_MANY_PEOPLE);
        int numberOfPlayers = inputIntValue();

        List<String> playerNames = new ArrayList<>();
        IntStream.rangeClosed(1, numberOfPlayers)
            .forEach(i -> playerNames.add(inputPlayerName(i)));

        return playerNames;
    }

    public String inputPlayerName(int orderNumber) {
        System.out.printf(INPUT_PLAYER_NAME, orderNumber);
        return inputStringValue();
    }

    public int inputPlayerTurnPinNumber(String playerName) {
        System.out.print(System.lineSeparator());
        System.out.printf(INPUT_PLAYER_TURN, playerName);
        return inputIntValue();
    }

    public void scannerClose() {
        scanner.close();
    }
}
