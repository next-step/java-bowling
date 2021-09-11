package bowling.view;

import bowling.model.frame.FallenPin;
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
    private static final String STRIKE_SYMBOL = "X";
    private static final String GUTTER_SYMBOL = "-";
    private static final String SCORE_SECTION = "|      |";
    private static final String EMPTY_SECTION = "      |";
    private static final String NEW_LINE = "\n";
    private static final String EMPTY_STRING = "";
    private static final PrintStream PRINT_STREAM = System.out;

    public static void printBoard(String playerName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(FRAME_NAME_AND_NUMBERS);
        stringBuilder.append(String.format(PLAYER_NAME, playerName));
        stringBuilder.append(generateEmptySections(MAX_EMPTY_SECTION_COUNT));
        stringBuilder.append(SCORE_SECTION);
        stringBuilder.append(generateEmptySections(MAX_EMPTY_SECTION_COUNT));
        PRINT_STREAM.println(stringBuilder);
    }

    public static void printFrames(String playerName, List<Frame> frames) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(generatePlayerNameDisplay(playerName));
        stringBuilder.append(generateFrameFallenPinDisplay(frames));
        stringBuilder.append(generateLastFrameBonusFallenPinDisplay(frames));
        stringBuilder.append(generateEmptySectionsOfFallenPinDisplay(frames));
        stringBuilder.append(generateScoreDisplay(frames));
        stringBuilder.append(generateEmptySectionsOfScoreDisplay(frames));
        PRINT_STREAM.println(stringBuilder);
    }

    private static String generatePlayerNameDisplay(String playerName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(FRAME_NAME_AND_NUMBERS);
        stringBuilder.append(String.format(PLAYER_NAME, playerName));
        return stringBuilder.toString();
    }

    private static String generateFrameFallenPinDisplay(List<Frame> frames) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Frame frame : frames) {
            String displayFormat = FrameFallenPinStatus.findDisplayFormat(frame.fallenPin());
            String firstFallenPinSymbol = generateFallenPinSymbol(frame.firstFallenPin());
            String secondFallenPinSymbol = generateFallenPinSymbol(frame.secondFallenPin());

            stringBuilder.append(String.format(displayFormat, firstFallenPinSymbol, secondFallenPinSymbol));
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

        return String.format(BONUS_FALLEN_PIN_DISPLAY_FORMAT, generateFallenPinSymbol(finalFrame.bonusFallenPin()));
    }

    private static String generateFallenPinSymbol(FallenPin fallenPin) {
        if (fallenPin == null) {
            return EMPTY_STRING;
        }

        if (fallenPin.isMax()) {
            return STRIKE_SYMBOL;
        }

        if (fallenPin.isMin()) {
            return GUTTER_SYMBOL;
        }

        return fallenPin.toString();
    }

    private static String generateEmptySectionsOfFallenPinDisplay(List<Frame> frames) {
        int emptySectionCount = MAX_EMPTY_SECTION_COUNT - frames.size();
        return generateEmptySections(emptySectionCount);
    }

    private static String generateEmptySectionsOfScoreDisplay(List<Frame> frames) {
        long scoreDisplayCount = frames.stream()
                .filter(frame -> !frame.isWaitingNextPitching())
                .count();

        int emptySectionCount = MAX_EMPTY_SECTION_COUNT - (int) scoreDisplayCount;
        return generateEmptySections(emptySectionCount);
    }

    private static String generateScoreDisplay(List<Frame> frames) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SCORE_SECTION);
        for (Frame frame : frames) {
            stringBuilder.append(generateOneSectionScoreDisplay(frame));
        }
        return stringBuilder.toString();
    }

    private static String generateOneSectionScoreDisplay(Frame frame) {
        if (!frame.isBonusPlay() && frame.isWaitingNextPitching()) {
            return EMPTY_STRING;
        }

        StringBuilder stringBuilder = new StringBuilder();
        String displayFormat = FrameScoreDisplay.findDisplayFormat(frame.score());
        stringBuilder.append(String.format(displayFormat, frame.scoreValue()));
        return stringBuilder.toString();
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
