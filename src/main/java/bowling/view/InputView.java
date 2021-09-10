package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String playerName() {
        System.out.print("플레이어 이름은(3 english letters)? : ");
        return SCANNER.next();
    }

    public static int fallenPins(int frameNumber) {
        System.out.print("%d프레임 투구 : ");
        return SCANNER.nextInt();
    }

}
