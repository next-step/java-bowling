package bowling.view;

import java.util.Scanner;

public class UserInputView extends BaseView{

    private static final String USER_INPUT_MESSAGE = "플레이어 이름은?(3 english letters): ";

    private static final Scanner scanner = new Scanner(System.in);

    public static String display() {
        append(USER_INPUT_MESSAGE);
        printAndClear(PrintType.NOT_NEW_LINE);
        return scanner.next();
    }
}
