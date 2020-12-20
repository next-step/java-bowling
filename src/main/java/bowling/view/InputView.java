package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final String PLAYER_NAME_PATTERN = "^[a-zA-Z]{3}$";

    private static Scanner scanner = new Scanner(System.in);

    public static String inputPlayerName(){
        System.out.print(INPUT_PLAYER_NAME);
        String playerName = scanner.next();

        validatePlayerName(playerName);

        return playerName;
    }

    private static void validatePlayerName(String playerName){
        if (!playerName.matches(PLAYER_NAME_PATTERN)) {
            throw new IllegalArgumentException("플레이어의 이름을 영문자 세글자로 입력하세요.");
        }
    }
}
