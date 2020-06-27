package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return SCANNER.nextLine();
    }

    public static int inputPinCount(int frameNumber) {
        System.out.print(String.format("%d프레임 투구 : ", frameNumber));
        return SCANNER.nextInt();
    }
}
