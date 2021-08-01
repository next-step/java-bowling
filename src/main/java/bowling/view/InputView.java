package bowling.view;

import bowling.dto.BowlingPlayerDto;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_PLAYER_NAME_STATEMENT = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_NUM_OF_DOWNED_PIN_FORMAT = "%d 프레임 투구 : ";

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {}

    public static String inputPlayerName() {
        printStatement(INPUT_PLAYER_NAME_STATEMENT);

        return SCANNER.nextLine()
                .trim();
    }

    public static int inputNumOfDownedPins(BowlingPlayerDto bowlingPlayerDto) {
        printStatement(String.format(INPUT_NUM_OF_DOWNED_PIN_FORMAT, bowlingPlayerDto.getCurrentFrameNumber()));

        return getInt();
    }

    private static void printStatement(String statement) {
        System.out.print(statement);
    }

    private static int getInt() {
        return Integer.parseInt(
                SCANNER.nextLine()
                        .trim()
        );
    }
}
