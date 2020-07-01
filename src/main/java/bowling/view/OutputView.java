package bowling.view;

import bowling.domain.Frame;
import bowling.domain.ResultFrame;
import bowling.domain.Round;

public class OutputView {
    private static final String EMPTY_FRAME_RESULT = " |     ";
    private static final String FRAME_DELIMITER = "|";
    private static final String FIRST_LINE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String GUTTER = "-";
    private static final int BOWLING_FRAMES = 10;

    public static void printSnapshot(final ResultFrame resultFrame) {
        printInfoLine();
        System.out.printf("%s %s ", FRAME_DELIMITER, resultFrame.getGameUser());
        for (int i = 1; i <= resultFrame.count(); i++) {
            printFrame(resultFrame.getFrame(i));
        }

        for (int i = resultFrame.count() + 1; i <= BOWLING_FRAMES; i++) {
            System.out.print(EMPTY_FRAME_RESULT);
        }
        System.out.println(FRAME_DELIMITER);
    }

    private static void printInfoLine() {
        System.out.println(FIRST_LINE);
    }

    private static void printFrame(final Frame frame) {
        System.out.printf(" %s  ", FRAME_DELIMITER);
        if (frame.isStrike()) {
            System.out.printf(" %s ", STRIKE);
            return;
        }
        String firstPins = extractPinsMark(frame, Round.FIRST_ROUND);
        if (!frame.isFinished()) {
            System.out.print(firstPins);
            return;
        }
        if (frame.isSpare()) {
            System.out.print(firstPins + FRAME_DELIMITER + SPARE);
            return;
        }
        String secondPins = extractPinsMark(frame, Round.SECOND_ROUND);
        System.out.print(firstPins + FRAME_DELIMITER + secondPins);
    }

    private static String extractPinsMark(final Frame frame, final Round firstRound) {
        return frame.getPinsOf(firstRound).isGutter() ? GUTTER : frame.getPinsOf(firstRound).toString();
    }


}
