package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final String USER_REQUEST = "플레이어 이름은(3 english letters)?: ";
    private static final String PINS_REQUEST = "%d프레임 투구 : ";
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputUser() {
        System.out.print(USER_REQUEST);
        return scanner.nextLine();
    }

    public static int inputPins(final int frameNumber) {
        System.out.printf(PINS_REQUEST, frameNumber);
        return Integer.parseInt(scanner.nextLine());
    }
}
