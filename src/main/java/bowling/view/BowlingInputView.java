package bowling.view;

import java.util.Scanner;

public class BowlingInputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String getPlayerNameWithPrompt(String message) {
        System.out.print(message);

        return SCANNER.nextLine();
    }

    public static int getBowlingScoreWithPrompt(String message) {
        System.out.print(message);

        return SCANNER.nextInt();
    }
}
