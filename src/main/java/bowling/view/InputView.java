package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_COUNT = "%s프레임 투구 : ";

    private InputView() {
    }

    public static String inputString() {
        return SCANNER.next();
    }

    public static int inputInt() {
        return Integer.parseInt(inputString());
    }

    public static String inputName() {
        OutputVIew.print(INPUT_NAME);
        return inputString();
    }

    public static int inputCount(int round) {
        String message = String.format(INPUT_COUNT, round);
        OutputVIew.print(message);

        return inputInt();
    }
}
