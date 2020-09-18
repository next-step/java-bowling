package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_NAME_PHRASE = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_FALLEN_PINS_PHRASE = "%d프레임 투구 : ";

    private static final Scanner scanner = new Scanner(System.in);

    public static String getUserName() {
        System.out.print(INPUT_NAME_PHRASE);
        return scanner.nextLine();
    }

    public static int getFallenPins(int frameNo) {
        System.out.printf(INPUT_FALLEN_PINS_PHRASE, frameNo);
        return scanner.nextInt();
    }

}
