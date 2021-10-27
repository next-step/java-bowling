package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_USER_SIZE_MESSAGE = "How many people? ";

    private static final String INPUT_USERNAME_MESSAGE = "플레이어 이름은(3 english letters)?: ";

    private static final String INPUT_FRAME_SHOT = "%d프레임 투구 : ";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static int inputUserSize() {
        System.out.println(INPUT_USER_SIZE_MESSAGE);
        return Integer.parseInt(scanner.nextLine());
    }

    public static String inputUsername() {
        System.out.print(INPUT_USERNAME_MESSAGE);
        return scanner.nextLine();
    }

    public static int inputFrameShot(int round) {
        System.out.printf(INPUT_FRAME_SHOT, round);
        return Integer.parseInt(scanner.nextLine());
    }

}
