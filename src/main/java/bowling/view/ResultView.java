package bowling.view;

import bowling.model.frame.Frame;

import java.io.PrintStream;
import java.util.List;

public class ResultView {
    private static final int MAX_EMPTY_SECTION_COUNT = 10;
    private static final int GAP_BETWEEN_SIZE_AND_LAST_INDEX = 1;
    private static final String FRAME_NAME_AND_NUMBERS
            = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n";
    private static final String PLAYER_NAME = "|  %s |";
    private static final String BONUS_FALLEN_PIN_DISPLAY_FORMAT = "  %s   |";
    private static final String EMPTY_SECTION = "      |";
    private static final String NEW_LINE = "\n";
    private static final String EMPTY_STRING = "";
    private static final PrintStream PRINT_STREAM = System.out;

    public static void printBoard(String playerName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(FRAME_NAME_AND_NUMBERS);
        stringBuilder.append(String.format(PLAYER_NAME, playerName));
        stringBuilder.append(generateEmptySections(MAX_EMPTY_SECTION_COUNT));
        PRINT_STREAM.println(stringBuilder);
    }

    public static void printFrames(String playerName, List<Frame> frames) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(FRAME_NAME_AND_NUMBERS);
        stringBuilder.append(String.format(PLAYER_NAME, playerName));

        stringBuilder.append(generateFrameFallenPinDisplay(frames));
        stringBuilder.append(generateLastFrameBonusFallenPinDisplay(frames));

        int emptySectionCount = MAX_EMPTY_SECTION_COUNT - frames.size();
        stringBuilder.append(generateEmptySections(emptySectionCount));

        PRINT_STREAM.println(stringBuilder);
    }

    private static String generateFrameFallenPinDisplay(List<Frame> frames) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Frame frame : frames) {
            String displayFormat = FrameFallenPinStatus.findDisplayFormat(frame.getFallenPin());
            stringBuilder.append(String.format(displayFormat, frame.getFirstFallenPin(), frame.getSecondFallenPin()));
        }
        return stringBuilder.toString();
    }

    private static String generateLastFrameBonusFallenPinDisplay(List<Frame> frames) {
        if (frames == null || frames.isEmpty()) {
            return EMPTY_STRING;
        }

        int lastFrameIndex = frames.size() - GAP_BETWEEN_SIZE_AND_LAST_INDEX;
        Frame finalFrame = frames.get(lastFrameIndex);
        if (!finalFrame.isBonusPlay()) {
            return EMPTY_STRING;
        }

        return String.format(BONUS_FALLEN_PIN_DISPLAY_FORMAT, finalFrame.getBonusFallenPin());
    }

    private static String generateEmptySections(int emptySectionCount) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < emptySectionCount; i++) {
            stringBuilder.append(EMPTY_SECTION);
        }
        stringBuilder.append(NEW_LINE);
        return stringBuilder.toString();
    }
}
