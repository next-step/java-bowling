package view;

import java.util.Scanner;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-20 16:52
 */
public class ConsoleInputView {
    private static final String INPUT_PLAYER_MESSAGE = "플레이어 이름은(3 english letters)?:";
    private static Scanner scanner = new Scanner(System.in);

    public static String inputPlayerName() {
        System.out.println(INPUT_PLAYER_MESSAGE);
        return scanner.nextLine();
    }

    public static int inputFallenBowl() {
        return scanner.nextInt();
    }
}
