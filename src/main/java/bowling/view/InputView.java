package bowling.view;

import java.util.Scanner;

public class InputView {
    private static Scanner sc = new Scanner(System.in);

    public static String inputName() {
        System.out.println("플레이어 이름은(3 english letters)?: ");
        return sc.next();
    }

    public static int inputPins() {
        return sc.nextInt();
    }

}
