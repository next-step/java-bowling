package bowling.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsoleInputView implements InputView {

    private static final Scanner SCANNER = new Scanner(System.in).useDelimiter(System.lineSeparator() + "|\n");

    @Override
    public int receiveNumberOfPlayers() {
        printMessage("How many people? ");
        return getUserInputAsInteger();
    }

    @Override
    public List<String> receivePlayerNames(int numberOfPlayers) {
        return IntStream.rangeClosed(1, numberOfPlayers)
                        .mapToObj(i -> {
                            printMessage("플레이어 " + i + "의 이름은?(3 english letters): ");
                            return getUserInput();
                        })
                        .collect(Collectors.toList());
    }


    @Override
    public int receiveNumberOfKnockedDownPins(String playerName) {
        printMessage(playerName + "'s turn: ");
        return getUserInputAsInteger();
    }

    private String getUserInput() {
        return SCANNER.next();
    }

    private int getUserInputAsInteger() {
        return Integer.parseInt(getUserInput());
    }

    private void printMessage(String message) {
        System.out.print(message);
    }

}
