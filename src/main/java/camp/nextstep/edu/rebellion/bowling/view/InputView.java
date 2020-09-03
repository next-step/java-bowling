package camp.nextstep.edu.rebellion.bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {}

    public static String getPlayer() {
        System.out.println("플레이어 이름을 입력해주세요 (영문자만, 최대 3글자) ");
        return getLine();
    }

    public static int getHitScore(int frame) {
        System.out.print("\n" + frame + " 프레임 투구 : ");
        return getInt();
    }

    private static int getInt() {
        if (SCANNER.hasNextInt()) {
            return SCANNER.nextInt();
        }
        throw new IllegalArgumentException("입력 값이 없거나 숫자형식이 아닙니다");
    }

    private static String getLine() {
        if (SCANNER.hasNextLine()) {
            return SCANNER.next().trim();
        }
        throw new IllegalArgumentException("입력 값이 없습니다");
    }
}
