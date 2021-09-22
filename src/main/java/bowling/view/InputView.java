package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String ASK_PLAYER_COUNT = "몇명의 플레이어가 참여하나요?";
    private static final String ASK_PLAYER_NAME = "플레이어 %s의 이름은? (3 english letters)? : ";
    private static final String PITCH = "%s's turn : ";

    public static String getPlayerCount() {
        System.out.println(ASK_PLAYER_COUNT);
        return SCANNER.nextLine();
    }

    public static String playerName(int playerCount) {
        System.out.printf(ASK_PLAYER_NAME, playerCount);
        return SCANNER.nextLine();
    }

    public static String pitch(String name) {
        System.out.printf(PITCH, name);
        return SCANNER.nextLine();
    }
}
