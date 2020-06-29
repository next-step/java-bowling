package bowling.view;

import java.util.Scanner;

public class InputView {
    private final static Scanner SCANNER = new Scanner(System.in);

    public static String inputPlayerName() {
        System.out.println("플레이어 이름은(3 english letters)?: ");
        return SCANNER.next();
    }

    public static int inputScore(int frameSize) {
        System.out.println(String.format("%d프레임 투구 : ", frameSize));
        return SCANNER.nextInt();
    }
}
