package bowling.view;

import bowling.utils.InputNumberUtil;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_PLAYER_NAME_MESSAGE = "플레이어 이름은(3 english letters)?: ";
    private static final Scanner sc = new Scanner(System.in);

    public static String inputPlayerName() {
        System.out.println(INPUT_PLAYER_NAME_MESSAGE);
        return sc.nextLine();
    }

    public static int inputPitchNumber() {
        String input = sc.nextLine();
        return InputNumberUtil.parseInt(input);
    }
}
