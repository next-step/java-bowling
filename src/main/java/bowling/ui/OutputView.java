package bowling.ui;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    private static final String AREA_PER_FRAME = "      ";
    private static final String EMPTY = "";
    private static final String FRAME_EDGE = "|";
    private static final String NAME_HEADER = "NAME";
    private static final int DOUBLE_DIGIT_START_VALUE = 10;
    private static final String DOUBLE_DIGIT_FORMAT_PREFIX = "0";
    public static final int MAX_FRAME_SIZE = 10;

    private OutputView() {
    }

    public static void showInitializedGame(Player player) {
        System.out.println(getHeaderAreaText());
        System.out.println(getInitValueAreaText(player.toString()));
    }

    public static void showDashBoard(Player player, Frames frames) {
        System.out.println(getHeaderAreaText());
        System.out.println(getCurrentValueAreaText(player.toString(), frames));
    }

    private static String getHeaderAreaText() {
        return buildStartFrame(NAME_HEADER) + getHeaderTitlePerFrame();
    }

    private static String getInitValueAreaText(String playerName) {
        return buildStartFrame(playerName) + getEmptyValueTextPerFrame();
    }

    private static String getCurrentValueAreaText(String playerName, Frames frames) {
        return buildStartFrame(playerName) + getCurrentValueTextPerFrame(frames);
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

    private static String getCurrentValueTextPerFrame(Frames frames) {
        int recordFrameSize = frames.size();
        String recordVal = IntStream.range(0, recordFrameSize)
            .mapToObj(index ->
                buildNoneStartFrame(frames.frameOfIndex(index).getScores()))
            .collect(Collectors.joining());

        if (recordFrameSize != MAX_FRAME_SIZE) {
            recordVal += IntStream.range(recordFrameSize, 10)
                .mapToObj(index -> buildNoneStartFrame(EMPTY))
                .collect(Collectors.joining());
        }
        return recordVal;
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
