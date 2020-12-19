package bowling.ui;

import bowling.domain.frame.FrameResult;
import bowling.domain.player.Player;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    private static final String AREA_PER_FRAME = "      ";
    private static final String EMPTY = "";
    private static final String FRAME_EDGE = "|";
    private static final String NAME_HEADER = "NAME";
    private static final int DOUBLE_DIGIT_START_VALUE = 10;
    private static final String DOUBLE_DIGIT_FORMAT_PREFIX = "0";
    private static final int STRIKE_PIN = 10;
    private static final int GUTTER_PIN = 0;

    private OutputView() {
    }

    public static void showInitializedGame(Player player) {
        System.out.println(getHeaderAreaText());
        System.out.println(getInitValueAreaText(player.toString()));
        System.out.println(getInitScoreAreaText());
    }

    public static void showDashBoard(Player player, List<FrameResult> frameResults) {
        System.out.println(getHeaderAreaText());
        System.out.println(getCurrentValueAreaText(player.toString(), frameResults));
        System.out.println(getCurrentScoreAreaText(frameResults));
    }

    private static String getHeaderAreaText() {
        return buildStartFrame(NAME_HEADER) + getHeaderTitlePerFrame();
    }

    private static String getInitValueAreaText(String playerName) {
        return buildStartFrame(playerName) + getEmptyValueTextPerFrame();
    }

    private static String getInitScoreAreaText() {
        return buildStartFrame(AREA_PER_FRAME) + getEmptyTenFrames();
    }

    private static String getCurrentValueAreaText(String playerName, List<FrameResult> frameResults) {
        return buildStartFrame(playerName) + getCurrentValueTextPerFrame(frameResults);
    }

    private static String getCurrentScoreAreaText(List<FrameResult> frameResults) {
        return buildStartFrame(AREA_PER_FRAME) + getCurrentScorePerFrame(frameResults);
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

    private static String getCurrentValueTextPerFrame(List<FrameResult> frameResults) {
        return frameResults.stream()
            .map(frameResult -> buildNoneStartFrame(bowlingSymbol(frameResult)))
            .collect(Collectors.joining());
    }

    private static String getEmptyTenFrames() {
        return IntStream.rangeClosed(1, 10)
            .mapToObj(index -> buildNoneStartFrame(AREA_PER_FRAME))
            .collect(Collectors.joining());
    }

    private static String getCurrentScorePerFrame(List<FrameResult> frameResults) {
        List<Score> scores = frameResults.stream()
            .map(FrameResult::getScore).collect(Collectors.toList());
        StringBuilder scoreDisplays = new StringBuilder();

        int sum = 0;
        for (Score score : scores) {
            sum += score.getValue();
            scoreDisplays.append(score.getScoreType() != ScoreType.READY
                ? buildNoneStartFrame(String.valueOf(sum)) : buildNoneStartFrame(EMPTY));
        }

        return scoreDisplays.toString();
    }

    private static String bowlingSymbol(FrameResult frameResult) {
        List<Integer> downPins = frameResult.getDownPins();
        if (downPins.isEmpty()) {
            return EMPTY;
        }

        List<String> components = new ArrayList<>();

        int downPinIndex = 0;
        components.add(pinToSymbol(downPins.get(downPinIndex++)));

        ScoreType scoreType = frameResult.getScoreType();
        if (scoreType == ScoreType.SPARE) {
            components.add("/");
            downPinIndex++;
        }

        for (; downPinIndex < downPins.size(); downPinIndex++) {
            components.add(pinToSymbol(downPins.get(downPinIndex)));
        }

        if (scoreType == ScoreType.STRIKE_AND_SPARE) {
            components.set(2, "/");
        }
        return String.join("|", components);
    }

    private static String pinToSymbol(int downPin) {
        if (downPin == GUTTER_PIN) {
            return "-";
        }

        if (downPin == STRIKE_PIN) {
            return "X";
        }

        return String.valueOf(downPin);
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
