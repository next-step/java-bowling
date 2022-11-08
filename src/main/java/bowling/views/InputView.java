package bowling.views;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String PLAYER_NAME_REGEX = "^[A-Za-z]{3}$";

    private InputView() {
    }

    public static String inputPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        String playerName = SCANNER.nextLine();
        validatePlayerName(playerName);
        return playerName;
    }

    private static void validatePlayerName(String playerName) {
        if (!Pattern.matches(PLAYER_NAME_REGEX, playerName)) {
            throw new IllegalArgumentException("플레이어 이름은 영문자로 3자를 입력해야 합니다.");
        }
    }

    public static int inputNumberOfPinsHit(int numberOfFrame) {
        System.out.printf("%d프레임 투구 : ", numberOfFrame);
        return Integer.parseInt(SCANNER.nextLine());
    }

}
