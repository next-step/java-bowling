package bowling;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputUserName() {
        System.out.print("플레이어 이름은(3 english letters)?:");
        return SCANNER.next();
    }

    public static int inputUserScore(int round) {
        System.out.printf("%d 프레임 투구 :", round);
        return SCANNER.nextInt();
    }
}
