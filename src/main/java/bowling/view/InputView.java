package bowling.view;

import bowling.Player;
import java.util.Scanner;

public class InputView {

    private final Scanner sc = new Scanner(System.in);

    private static final String PLAYER_NAME_OF_INPUT_MESSAGE = "플레이어 이름은(3 english letters)?: ";

    public Player inputPlayerName() {
        System.out.println(PLAYER_NAME_OF_INPUT_MESSAGE);
        return new Player(sc.next());
    }

}
