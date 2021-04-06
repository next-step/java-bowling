package bowling.view;

import bowling.domain.*;

import java.util.List;
import java.util.stream.IntStream;

public class ResultView {
    private static final String DEFAULT_BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String FRAME_EMPTY = "      |";
    private static final String SCORE_FIRST = "|      |";
    private static final String PIPE = "|";
    private static final int FRAME_MAX_SIZE = 10;
    private static final int DEFAULT_SIZE = 1;
    private static final int MINUS_ONE = 1;
    private static final int ZERO = 0;
    private static final boolean IS_NOT_FIRST = false;

    public void bowlingBoard(Frames frames) {
        System.out.println(DEFAULT_BOARD);
        System.out.println(printFrameBoard(frames));
        System.out.println(printScoreBoard(frames));
        System.out.println();
    }

    private String printScoreBoard(Frames frames) {
        StringBuilder builder = new StringBuilder();
        builder.append(SCORE_FIRST);

        IntStream.rangeClosed(DEFAULT_SIZE, FRAME_MAX_SIZE)
                .forEach(size -> builder.append(frameScore(size, frames)));

        return builder.toString();
    }

    private String frameScore(int size, Frames frames) {
        List<Score> scores = frames.scores();
        if (size <= scores.size()
                && !isScoresEnd(size, scores)) {
            return stringToScore(size, scores);
        }
        return FRAME_EMPTY;
    }

    private String stringToScore(int size, List<Score> scores) {
        int sum = scores.stream()
                    .limit(size)
                    .mapToInt(Score::score)
                    .sum();

        return String.format(" %3d  " + PIPE, sum);
    }

    private boolean isScoresEnd(int size, List<Score> scores) {
        return scores.stream()
                .limit(size)
                .anyMatch(score -> score.isFrameScoreEnd());
    }

    private String printFrameBoard(Frames frames) {
        StringBuilder builder = new StringBuilder();
        builder.append(stringToName(frames));

        IntStream.rangeClosed(DEFAULT_SIZE, FRAME_MAX_SIZE)
                .forEach(size -> builder.append(framePins(size, frames)));

        return builder.toString();
    }

    private String framePins(int size, Frames frames) {
        List<Frame> frameList = frames.frames();
        if (!frameList.isEmpty()
                && size <= frameList.size()) {
            return stringToFrame(frameList.get(index(size)));
        }
        return FRAME_EMPTY;
    }

    private String stringToFrame(Frame frame) {
        Pins pins = frame.pins();
        if (pins.getClass() == FinalPins.class) {
            return stringToFinal(pins);
        }
        return stringToNormal(pins);
    }

    private String stringToNormal(Pins pins) {
        List<Pin> pinList = pins.pins();
        StringBuilder builder = new StringBuilder();

        ScoreRule scoreRule = pins.scoreRule();
        if (ScoreRule.STRIKE == scoreRule) {
            return String.format("  %-2s  " + PIPE, scoreRule.symbol);
        }

        boolean isSpare = ScoreRule.SPARE == scoreRule;
        int maxLength = isSpare ? DEFAULT_SIZE : pinList.size();

        stringToPins(pinList, builder, ZERO, maxLength);

        if (isSpare) {
            builder.append(PIPE + scoreRule.symbol);
        }
        return String.format(" %-3s  " + PIPE, builder.toString());
    }

    private void stringToPins(List<Pin> pinList, StringBuilder builder, int startIndex, int maxLength) {
        for (int i = startIndex; i < maxLength; i++) {
            addBuilderPipe(builder);
            Pin pin = pinList.get(i);
            String symbol = pin.scoreRule(IS_NOT_FIRST).symbol;
            symbol = stringToSymbol(pin, symbol);
            builder.append(symbol);
        }
    }

    private String stringToSymbol(Pin pin, String symbol) {
        if (symbol.isEmpty()) {
            symbol = String.valueOf(pin.pin());
        }
        return symbol;
    }

    private void addBuilderPipe(StringBuilder builder) {
        if (builder.length() > ZERO) {
            builder.append(PIPE);
        }
    }

    public String stringToFinal(Pins pins) {
        List<Pin> pinList = pins.pins();
        StringBuilder builder = new StringBuilder();

        int result = stringToFinalStrike(pinList, builder);

        stringToPins(pinList, builder, result, pinList.size());

        return String.format(" %-5s" + PIPE, builder.toString());
    }

    private int stringToFinalStrike(List<Pin> pinList, StringBuilder builder) {
        int result;
        for (result = ZERO; result < pinList.size(); result++) {
            if (!pinList.get(result).isStrike()) {
                break;
            }
            addBuilderPipe(builder);
            builder.append(ScoreRule.STRIKE.symbol);
        }
        return result;
    }

    private String stringToName(Frames frames) {
        return String.format(PIPE + " %4s " + PIPE, frames.name());
    }

    private int index(int size) {
        return size - MINUS_ONE;
    }
}
