package bowling.view;

import bowling.model.Player;

import java.util.Scanner;

public final class InputView {

    private static final String INPUT_PLAYER_NAME_MESSAGE = "플레이어 이름은(3 english letters)?: ";

    private final Scanner scanner = new Scanner(System.in);

    public Player inputPlayerName() {
        System.out.print(INPUT_PLAYER_NAME_MESSAGE);
        return Player.create(readLine());
    }

    private String readLine() {
        return scanner.nextLine();
    }

}
