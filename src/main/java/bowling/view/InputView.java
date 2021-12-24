package bowling.view;

import bowling.Pin;
import bowling.Player;
import java.util.Scanner;

public class InputView {

    private static final String PLAYER_NAME_OF_INPUT_MESSAGE = "플레이어 이름은(3 english letters)?: ";
    private static final String FRAME_INPUT_MESSAGE_FORMAT = "%s프레임 투구 : ";
    private static final int INDEX_TO_ROUND_APPEND_NUMBER = 1;


    private final Scanner sc = new Scanner(System.in);

    public Player inputPlayerName() {
        System.out.print(PLAYER_NAME_OF_INPUT_MESSAGE);
        return new Player(sc.next());
    }

    public Pin inputBowlHitPin(int index) {
        System.out.printf(FRAME_INPUT_MESSAGE_FORMAT, indexToRoundString(index));

        return Pin.of(sc.nextInt());
    }

    private String indexToRoundString(int index) {
        return String.valueOf(index + INDEX_TO_ROUND_APPEND_NUMBER);
    }
}
