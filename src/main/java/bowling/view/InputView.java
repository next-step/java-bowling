package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_NAME_OF_PLAYER = "플레이어 이름은(3 english letters)?: ";

    private InputView() {
    }

    public static String inputNameOfPlayer() {
        System.out.print(INPUT_NAME_OF_PLAYER);
        return SCANNER.nextLine();
    }
}
