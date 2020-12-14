package bowling.ui;

import bowling.domain.game.BowlingGame;
import bowling.domain.state.State;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    private static final String AREA_PER_FRAME = "      ";
    private static final String EMPTY = "";
    private static final String FRAME_EDGE = "|";
    private static final String NAME_HEADER = "NAME";
    private static final int DOUBLE_DIGIT_START_VALUE = 10;
    private static final String DOUBLE_DIGIT_FORMAT_PREFIX = "0";

    public static void showInitializedGame(BowlingGame game) {
        System.out.println(getHeaderAreaText());
        System.out.println(getInitValueAreaText(game));
    }

    public static void showDashBoard(BowlingGame game) {
        System.out.println(getHeaderAreaText());
        System.out.println(getCurrentValueAreaText(game));
    }

    private static String getHeaderAreaText() {
        return buildStartFrame(NAME_HEADER) + getHeaderTitlePerFrame();
    }

    private static String getInitValueAreaText(BowlingGame game) {
        return buildStartFrame(game.playersName()) + getEmptyValueTextPerFrame();
    }

    private static String getCurrentValueAreaText(BowlingGame game) {
        return buildStartFrame(game.playersName()) + getCurrentValueTextPerFrame(game.stateGroupedBy());
    }

    private static String getHeaderTitlePerFrame() {
        return IntStream.rangeClosed(1, 10)
            .mapToObj(index -> buildNoneStartFrame(getFormattedSequence(index)))
            .collect(Collectors.joining());
    }

    private static String getEmptyValueTextPerFrame() {
        return IntStream.rangeClosed(1, 10)
            .mapToObj(index -> buildNoneStartFrame(AREA_PER_FRAME))
            .collect(Collectors.joining());
    }

    private static String getCurrentValueTextPerFrame(Map<Integer, State> grouped) {
        return IntStream.rangeClosed(1, 10)
            .mapToObj(index -> buildNoneStartFrame(getValueText(grouped.get(index))))
            .collect(Collectors.joining());
    }

    private static String getValueText(State state) {
        if (state == null) {
            return AREA_PER_FRAME;
        }
        return state.reportState();
    }

    private static String buildStartFrame(String value) {
        String valueText = padToSameLength(value);
        return FRAME_EDGE + valueText + FRAME_EDGE;
    }

    private static String buildNoneStartFrame(String value) {
        String valueText = padToSameLength(value);
        return valueText + FRAME_EDGE;
    }

    private static String padToSameLength(String value) {
        if (value.equals(EMPTY)) {
            return AREA_PER_FRAME;
        }
        StringBuilder stringBuilder = new StringBuilder(AREA_PER_FRAME);
        int paddingStartIndex = getPaddingStartIndex(value);
        stringBuilder.replace(paddingStartIndex, paddingStartIndex + value.length(), value);
        return stringBuilder.toString();
    }

    private static int getPaddingStartIndex(String value) {
        return Math.round((AREA_PER_FRAME.length() - value.length()) / 2F);
    }

    private static String getFormattedSequence(int sequence) {
        if (sequence < DOUBLE_DIGIT_START_VALUE) {
            return DOUBLE_DIGIT_FORMAT_PREFIX + sequence;
        }
        return String.valueOf(sequence);
    }
}
