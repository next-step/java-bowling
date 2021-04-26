package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String REQUEST_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final String FRAME_PITCH = "%d프레임 투구 : ";

    private InputView() {}

    public static String requestPlayerName() {
        System.out.print(REQUEST_PLAYER_NAME);
        return SCANNER.nextLine();
    }

    public static int requestFallingPins(int frameNo) {
        System.out.printf(FRAME_PITCH, frameNo);
        return Integer.parseInt(SCANNER.nextLine());
    }
}
