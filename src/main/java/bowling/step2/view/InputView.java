package bowling.step2.view;

import java.util.Scanner;

public class InputView {

    public static final String NAME_INPUT_TEXT = "플레이어 이름은(3 english letters)?: ";
    private static final Scanner sc = new Scanner(System.in);

    public static String inputPlayerName() {
        System.out.print(NAME_INPUT_TEXT);
        return sc.next();
    }
}
