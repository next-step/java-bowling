package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String INPUT_PLAYER_NAME = "플레이어 이름은(3 english letters)? : ";
    private static final String INPUT_PINS = "%d 프레임 투구 : ";

    public static String name() {
        System.out.print(INPUT_PLAYER_NAME);
        return SCANNER.nextLine();
    }

    public static int pins(int flameNumber) {
        System.out.print(String.format(INPUT_PINS, flameNumber));
        return Integer.parseInt(SCANNER.nextLine());
    }

}
