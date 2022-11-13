package bowling.view;

import java.util.Scanner;

public class InputView {

    public static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String scanName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return SCANNER.nextLine();
    }

    public static int scanScore(int index) {
        System.out.printf("%d프레임 투구 : ", index);
        return SCANNER.nextInt();
    }
}
