package view;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_PLAYER = "플레이어 이름은(3 english letters)? : ";
    private static final String ROLLING_BY_FRAME_COUNT_FORMAT = "%d프레임 투구 : ";
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputPlayer() {
        System.out.print(INPUT_PLAYER);
        return scanner.next();
    }

    public static int inputFrame(int frame) {
        System.out.print(String.format(ROLLING_BY_FRAME_COUNT_FORMAT, frame));
        return scanner.nextInt();
    }
}
