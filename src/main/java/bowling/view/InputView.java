package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {}

    public static String inputParticipationName() {

        System.out.println("플레이어 이름은(3 english letters)?:");
        final String name = SCANNER.next();
        validate(name);
        return name;
    }

    private static void validate(final String name) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("참가자 이름은 필수값입니다.");
        }
        if (name.length() >= 4) {
            throw new IllegalArgumentException("참가자 이름은 3자리 이하여야 합니다.");
        }
    }

    public static int inputScore(final int frame) {

        System.out.printf("%d프레임 투구 :", frame);
        try {
            final String score = SCANNER.next();
            return Integer.parseInt(score);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("숫자만 입력해야 합니다.");
        }
    }
}
