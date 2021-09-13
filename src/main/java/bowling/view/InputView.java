package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static int numberOfPlayers() {
        System.out.print("How many people? ");
        return SCANNER.nextInt();
    }

    public static String playerName() {
        System.out.print("플레이어 이름은(3 english letters)? : ");
        return SCANNER.next();
    }

    public static int fallenPins(int frameNumber) {
        System.out.printf("%d프레임 투구 : ", frameNumber);
        return SCANNER.nextInt();
    }

}
