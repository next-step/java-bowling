package step2.view;

import step2.domain.Player;

import java.util.Scanner;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);
    private static final String PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";

    public Player getPlayerName() {
        System.out.print(PLAYER_NAME);
        return Player.from(scanner.nextLine());
    }
}
