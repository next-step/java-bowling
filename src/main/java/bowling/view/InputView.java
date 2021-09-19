package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readPlayerName() {
        return getRawString("플레이어 이름은(3 english letters)?");
    }

    private static String getRawString(String message) {
        String raw = null;
        while (raw == null || raw.isEmpty()) {
            System.out.println(message);
            raw = scanner.nextLine();
        }
        return raw;
    }

    public static int readPinDownCount(int currentPlayingFrameIndex) {
        return Integer.parseInt(getRawString(currentPlayingFrameIndex + 1 + "프레임 투구 : "));
    }
}
