package bowling;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static UserName inputUserName() {
        System.out.print("플레이어 이름은(3 english letters)?:");
        String input = SCANNER.next();
        return new UserName(input);
    }

    public static int inputUserScore(int round) {
        System.out.printf("%d 프레임 투구 :", round);
        return SCANNER.nextInt();
    }
}
