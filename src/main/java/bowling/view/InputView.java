package bowling.view;

import bowling.game.dto.BowlingGameDto;

import java.io.PrintStream;
import java.util.Scanner;

public class InputView {

    private static final String REQUEST_NAME_MESSAGE = "플레이어 이름은(3 english letters)?: ";
    private static final String REQUEST_HOW_MANY_FELLED_MESSAGE = "%d프레임 투구 : ";
    private static final Scanner scanner = new Scanner(System.in);
    private static final PrintStream CONSOLE = System.out;

    private InputView() {
    }

    public static String askPlayerName() {
        CONSOLE.print(REQUEST_NAME_MESSAGE);
        return scanner.nextLine();
    }

    public static int askFelledPins(BowlingGameDto bowlingGameDto) {
        CONSOLE.print(String.format(REQUEST_HOW_MANY_FELLED_MESSAGE, bowlingGameDto.getCurrentFrame()));
        return scanner.nextInt();
    }

}
