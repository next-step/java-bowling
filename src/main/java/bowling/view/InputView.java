package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?:");
        return SCANNER.nextLine();
    }

    public static Integer inputPitching(long currentFrame) {
        System.out.print(currentFrame+"프레임 투구 : ");
        return Integer.parseInt(SCANNER.nextLine());
    }
}
