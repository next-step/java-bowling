package bowling.ui;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String enterName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        String name = SCANNER.nextLine();
        return name;
    }

    public static int enterDownPin(int index) {
        System.out.print(index + "프레임 투구 : ");
        int downPin = SCANNER.nextInt();
        return downPin;
    }
}
