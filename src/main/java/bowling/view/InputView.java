package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String inputNames() {
        System.out.print("플레이어 이름은(3 english letters) ?: ");
        return scanner.nextLine();
    }

    public static int inputPitchingNumber(int index) {
        System.out.print(index + "프레임 투구 : ");
        return scanner.nextInt();
    }
}
