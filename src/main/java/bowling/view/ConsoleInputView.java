package bowling.view;

import bowling.domain.PlayerName;

import java.util.Scanner;

public class ConsoleInputView implements InputView {
    private static final String ERROR_MSG_PREFIX = "[ERROR] ";
    private static final String NUMBER_OF_PLAYERS_INPUT_MSG = "How many people? ";
    private static final String PLAYER_NAME_INPUT_MSG_FORMAT = "플레이어 %d의 이름은(3 english letters)?: ";
    private static final String KNOCK_DOWN_PINS_INPUT_MSG_SUFFIX = "'s turn : ";
    private final Scanner scanner;

    public ConsoleInputView() {
        scanner = new Scanner(System.in);
    }

    @Override
    public int getNumberOfPlayers() {
        System.out.print(NUMBER_OF_PLAYERS_INPUT_MSG);
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public String getPlayerName(int playerNo) {
        System.out.printf(PLAYER_NAME_INPUT_MSG_FORMAT, playerNo);
        return scanner.nextLine();
    }

    @Override
    public int getKnockDownPins(PlayerName playerName) {
        System.out.print(playerName.getValue() + KNOCK_DOWN_PINS_INPUT_MSG_SUFFIX);
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public void printError(RuntimeException e) {
        System.out.println(ERROR_MSG_PREFIX + e.getMessage());
    }
}
