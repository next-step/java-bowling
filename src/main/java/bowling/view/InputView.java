package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String INPUT_USER_NAME_MESSAGE = "플레이어 %d의 이름은(3 english letters)?: ";
    private static final String INPUT_PLATER_COUNT_MESSAGE = "How many people?";
    private static final String INPUT_SCORE_MESSAGE = "%s's turn : ";

    private InputView() {
    }

    public static String printInputUserNameMessage(int index) {
        System.out.print(String.format(INPUT_USER_NAME_MESSAGE, index));
        return getStringFromUserInput();
    }

    public static int printInputFallenPinsMessage(String playerName) {
        System.out.print(String.format(INPUT_SCORE_MESSAGE, playerName));
        return getIntFromUserInput();
    }

    private static String getStringFromUserInput() {
        return SCANNER.nextLine();
    }

    private static int getIntFromUserInput() {
        return SCANNER.nextInt();
    }

    public static int printInputPlayerCountMessage() {
        System.out.print(" "+INPUT_PLATER_COUNT_MESSAGE);
        return Integer.parseInt(SCANNER.nextLine());
    }
}
