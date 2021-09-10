package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String extractUsername() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return SCANNER.nextLine();
    }

    public static int extractFrameResult(int frameIndex) {
        System.out.println((frameIndex) + "프레임 투구 : ");
        return Integer.parseInt(SCANNER.nextLine());
    }
}
