package bowling.view;

import java.util.Scanner;

public class InputView {
    private static Scanner sc = new Scanner(System.in);

    public static String inputName() {
        return sc.next();
    }

}
