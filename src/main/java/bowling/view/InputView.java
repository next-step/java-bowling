package bowling.view;

import bowling.domain.Pitching;
import java.util.Scanner;

public class InputView {
    private static final String PLAYER_NAME_PROMPT = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_FALLEN_PINS_MESSAGE = "%d 프레임 투구 : ";

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputPlayerName() {
        System.out.println(PLAYER_NAME_PROMPT);
        return SCANNER.nextLine();
    }

    public static Pitching inputFallenPins(int currentFrameNumber) {
        System.out.printf(INPUT_FALLEN_PINS_MESSAGE, currentFrameNumber);
        return new Pitching(Integer.parseInt(SCANNER.nextLine()));
    }
}
