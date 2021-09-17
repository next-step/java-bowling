package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_USERNAME_MESSAGE = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_NEXT_FRAME_SHOT_MESSAGE = "%d프레임 투구 : ";

    private static final Scanner scanner = new Scanner(System.in);

    public InputView() {
    }

    public static String inputUsername() {
        System.out.print(INPUT_USERNAME_MESSAGE);
        return scanner.nextLine();
    }

    public static int inputNextFrameShot(int nowRound) {
        System.out.printf(INPUT_NEXT_FRAME_SHOT_MESSAGE, nowRound);
        return Integer.parseInt(scanner.nextLine());
    }

}
