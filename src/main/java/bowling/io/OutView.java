package bowling.io;

import bowling.utils.Pretty;

import java.util.Arrays;

import static bowling.model.frame.Frame.SEPARATOR_OF_FRAME;
import static bowling.utils.Pretty.PARTITION;

public class OutView {

    private static final String MESSAGE_OF_HEADER = "|  NAME  |   01   |   02   |   03   |   04   |   05   |   06   |   07   |   08   |   09   |   10   |";
    private static final String MESSAGE_OF_GAME_OVER = "게임이 종료되었습니다";

    public static void printProgress(String currentStates) {
        System.out.println(MESSAGE_OF_HEADER);
        System.out.println(getCurrentScores(currentStates));
        System.out.println();
    }

    private static String getCurrentScores(String currentStates) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PARTITION);

        Arrays.stream(currentStates.split(SEPARATOR_OF_FRAME))
                .map(Pretty::alignCenter)
                .map(frame -> frame.concat(PARTITION))
                .forEach(stringBuilder::append);

        return stringBuilder.toString();
    }

    public static void printGameOver() {
        System.out.println(MESSAGE_OF_GAME_OVER);
    }
}
