package bowling.views;

import bowling.utils.StringUtils;

import java.util.Scanner;

public class InputView {
    private final static String PLAYER_NAME = "플레이어 이름은(3 english letters)? : ";
    private final static String PITCHING_NUMBER = " 프레임 투구 : ";
    private final static Scanner scanner = new Scanner(System.in);

    private InputView() {}

    private static void emptyValidation(String value) {
        if (StringUtils.isEmpty(value))
            throw new IllegalArgumentException("유효하지 않은 입력값 입니다.");
    }

    private static String value(String description) {
        System.out.print(description);
        String value = scanner.nextLine();
        emptyValidation(value);
        return value;
    }

    public static String playerName() {
        return value(PLAYER_NAME);
    }

    public static int pitchingNumber(int frameIndex) {
        frameIndex += 1;
        return Integer.parseInt(value(frameIndex + PITCHING_NUMBER));
    }
}
