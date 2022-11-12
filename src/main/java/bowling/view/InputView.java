package bowling.view;

import bowling.domain.PlayerName;

import java.util.Scanner;

public class InputView {

    private static final String PLAYER_NAME_MSG = "플레이어 이름은(3 english letters)?: ";
    private final Scanner scanner = new Scanner(System.in);

    public PlayerName getPlayName(){
        System.out.print(PLAYER_NAME_MSG);
        String name = scanner.nextLine();
        System.out.println();
        return new PlayerName(name);
    }
}
