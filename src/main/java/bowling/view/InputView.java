package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final int ONE = 1;

    private static final Scanner scanner = new Scanner(System.in);

    public static String inputPlayer() {
        System.out.println("플레이어 이름은(3 english letters)?: ");
        return scanner.next();
    }

    public static int relaseBowling(int frameIndex) {
        System.out.println(String.format("%d프레임 투구 :", frameIndex + ONE));
        return scanner.nextInt();
    }
}
