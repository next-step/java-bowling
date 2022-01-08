package bowling.UI;

import bowling.domain.Pins;
import bowling.domain.Player;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_PLAYER_NAME_MESSAGE = "플레이어 이름은(3 english letters)? : ";
    private static final String INPUT_FALLEN_PINS_MESSAGE = "%d 프레임 투구 : ";

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {}

    public static Player inputPlayerName() {
        System.out.print(INPUT_PLAYER_NAME_MESSAGE);
        return new Player(SCANNER.next());
    }

    public static Pins inputFallenPins(int currentFrameIndex) {
        System.out.printf(INPUT_FALLEN_PINS_MESSAGE, currentFrameIndex);
        return new Pins(SCANNER.nextInt());
    }
}
