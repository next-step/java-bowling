package bowling.view;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static String inputPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
    }

    public static int inputDropPinCount(final int frameCount) {
        System.out.print(frameCount + "프레임 투구 : ");
        final int pinCount = scanner.nextInt();

        scanner.nextLine();
        return pinCount;
    }
}
