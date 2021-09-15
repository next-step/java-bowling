package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String ASK_PLAYER_NAME = "플레이어 이름은(3 english letters)? : ";
    private static final String PITCH = "%d 프레임 투구 : ";

    public static String playerName() {
        System.out.print(ASK_PLAYER_NAME);
        return SCANNER.nextLine();
    }

    public static String pitch(int frameIndex) {
        System.out.printf(PITCH, frameIndex);
        return SCANNER.nextLine();
    }
}
