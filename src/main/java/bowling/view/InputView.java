package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String PLAYER_NAME_GUIDE = "플레이어 이름은(3 english letters)?: ";
    private static final String BOWL_GUIDE = "\n%d 프레임 투구 : ";

    private InputView() {
    }

    public static String scanPlayerName() {
        System.out.printf(PLAYER_NAME_GUIDE);
        return scanner.nextLine();
    }

    public static String scanHitPins(int frameNumber) {
        System.out.printf(String.format(BOWL_GUIDE, frameNumber));
        return scanner.nextLine();
    }
}