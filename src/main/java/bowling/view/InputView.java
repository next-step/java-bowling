package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";

    private static Scanner scanner = new Scanner(System.in);

    public static String inputPlayerName(){
        System.out.print(INPUT_PLAYER_NAME);
        String playerName = scanner.next();

        return playerName;
    }

}
