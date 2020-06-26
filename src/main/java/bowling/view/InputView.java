package bowling.view;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String getPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
    }

    public static int getHitPinCount(int frameNumber) {
        System.out.print(frameNumber + "프레임 투구 : ");
        return scanner.nextInt();
    }
}
