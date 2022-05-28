package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final String PLAYER_NAME_INPUT = "플레이어 이름은(3 english letters)?: ";
    private static final String FRAME_NUMBER_MESSAGE = "%d프레임 투구 : ";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String inputPlayerName() {
        System.out.print(PLAYER_NAME_INPUT);
        return scanner.nextLine();
    }

    public static int inputPins(int number) {
        System.out.printf(FRAME_NUMBER_MESSAGE, number);
        return Integer.parseInt(scanner.nextLine());
    }
}
