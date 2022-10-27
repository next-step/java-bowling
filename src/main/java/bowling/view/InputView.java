package bowling.view;

import bowling.domain.Pins;
import bowling.domain.PlayerName;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String NAME_QUESTION = "플레이어 이름은(3 english letters)?: ";
    private static final String FALLEN_PINS_QUESTION_FORMAT = "%s프레임 투구 : ";

    public static void closeScan() {
        SCANNER.close();
    }

    public static PlayerName scanName() {
        System.out.print(NAME_QUESTION);
        return new PlayerName(SCANNER.nextLine());
    }

    public static Pins scanFallenPins(int frameNumber) {
        System.out.printf(FALLEN_PINS_QUESTION_FORMAT, frameNumber);
        return Pins.of(Integer.parseInt(SCANNER.nextLine()));
    }
}
