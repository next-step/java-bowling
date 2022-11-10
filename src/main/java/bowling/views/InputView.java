package bowling.views;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String inputPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return SCANNER.nextLine();
}

    public static int inputNumberOfPinsHit(int numberOfFrame) {
        System.out.printf("%d프레임 투구 : ", numberOfFrame);
        return Integer.parseInt(SCANNER.nextLine());
    }

}
