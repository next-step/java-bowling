package view;

import bowling.domain.Player;

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
    private static final String INPUT_PLAYER_COUNT_MESSAGE = "How many people? ";
    private static Scanner scanner = new Scanner(System.in);

    public static Player inputPlayerName() {
        System.out.println(INPUT_PLAYER_MESSAGE);
        return Player.of(scanner.next());
    }

    public static int inputFallenBowl() {
        return scanner.nextInt();
    }

    public static int inputPlayerCount() {
        System.out.println(INPUT_PLAYER_COUNT_MESSAGE);
        return scanner.nextInt();
    }
}
