package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {

    }

    public static String getPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
    }

    public static int getNumberOfPins() {
        System.out.println();
        System.out.print("프레임 투구 : ");
        return nextInt();
    }

    private static int nextInt() {
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

}
