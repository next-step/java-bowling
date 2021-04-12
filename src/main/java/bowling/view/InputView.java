package bowling.view;

import bowling.dto.BowlingGameRequest;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static BowlingGameRequest bowlingGameRequest() {
        return new BowlingGameRequest(playerName());
    }

    private static String playerName() {
        System.out.print(MessageConstant.PLAYER_NAME_INPUT);
        return SCANNER.nextLine()
                .trim();
    }

    public static int pinCount(int frameNumber) {
        System.out.print(frameNumber + MessageConstant.PINS_COUNT);
        String count = SCANNER.nextLine()
                .trim();
        return Integer.parseInt(count);
    }
}
