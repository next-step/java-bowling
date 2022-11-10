package bowling.ui;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_PLAYER_NAME_DESCRIPTION = "플레이어 이름은(3 english letters)?:";
    private static final Scanner scanner= new Scanner(System.in);

    public static String inputPlayerName() {
        System.out.printf(INPUT_PLAYER_NAME_DESCRIPTION);
        String userName = scanner.nextLine();
        if (userName == null || userName.length() != 3) {
            throw new RuntimeException("플렐이어 이름 세글자로 해주세요.");
        }

        return userName;
    }
}
