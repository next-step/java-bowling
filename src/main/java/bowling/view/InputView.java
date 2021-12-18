package bowling.view;

import java.util.Scanner;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);

    public static int getIntValue(String message) {
        System.out.print(message);
        return scanner.nextInt();
    }

    public static String getStringValue(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}
