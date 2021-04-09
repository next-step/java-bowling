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

    public void bowlingBoard(Users users) {
        System.out.println(DEFAULT_BOARD);

        users.forEach(user -> {
                    System.out.print(nameToString(user.name()));
                    System.out.println(frameBoardString(user.frames()));
                    System.out.println(scoreBoardString(user.frames()));
                });

        System.out.println();
    }

    private String scoreBoardString(Frames frames) {
        StringBuilder builder = new StringBuilder();
        builder.append(SCORE_FIRST);

        IntStream.rangeClosed(DEFAULT_SIZE, FRAME_MAX_SIZE)
                .forEach(size -> builder.append(frameScore(size, frames)));

        return builder.toString();
    }

    private String frameScore(int size, Frames frames) {
        List<FrameScore> scores = frames.scores();
        if (size <= scores.size()
                && !isEndFrameScore(size, scores)) {
            return scoreToString(size, scores);
        }
        return FRAME_EMPTY;
    }

    private String scoreToString(int size, List<FrameScore> scores) {
        int sum = scores.stream()
                    .limit(size)
                    .mapToInt(FrameScore::aggregateScore)
                    .sum();

        return String.format(" %3d  " + PIPE, sum);
    }

    private boolean isEndFrameScore(int size, List<FrameScore> scores) {
        return scores.stream()
                .limit(size)
                .anyMatch(score -> score.isAggregateEnd());
    }

    private String frameBoardString(Frames frames) {
        StringBuilder builder = new StringBuilder();

        IntStream.rangeClosed(DEFAULT_SIZE, FRAME_MAX_SIZE)
                .forEach(size -> builder.append(framePins(size, frames)));

        return builder.toString();
    }

    private String framePins(int size, Frames frames) {
        List<Frame> frameList = frames.frames();
        if (!frameList.isEmpty()
                && size <= frameList.size()) {
            return frameToString(frameList.get(index(size)));
        }
        return FRAME_EMPTY;
    }

    private String frameToString(Frame frame) {
        Pins pins = frame.pins();
        if (pins.getClass() == FinalPins.class) {
            return finalToString(pins);
        }
        return normalToString(pins);
    }

    private String normalToString(Pins pins) {
        List<Pin> pinList = pins.pins();
        StringBuilder builder = new StringBuilder();

        ScoreRule scoreRule = pins.scoreRule();
        if (ScoreRule.STRIKE == scoreRule) {
            return String.format("  %-2s  " + PIPE, scoreRule.symbol);
        }

        boolean isSpare = ScoreRule.SPARE == scoreRule;
        int maxLength = isSpare ? DEFAULT_SIZE : pinList.size();

        pinsToString(pinList, builder, ZERO, maxLength);

        if (isSpare) {
            builder.append(PIPE + scoreRule.symbol);
        }
        return String.format(" %-3s  " + PIPE, builder.toString());
    }

    private void pinsToString(List<Pin> pinList, StringBuilder builder, int startIndex, int maxLength) {
        for (int i = startIndex; i < maxLength; i++) {
            addBuilderPipe(builder);
            Pin pin = pinList.get(i);
            String symbol = pin.scoreRule(IS_NOT_FIRST).symbol;
            symbol = symbolToString(pin, symbol);
            builder.append(symbol);
        }
    }

    private String symbolToString(Pin pin, String symbol) {
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

    public String finalToString(Pins pins) {
        List<Pin> pinList = pins.pins();
        StringBuilder builder = new StringBuilder();

        int result = finalStrikeToString(pinList, builder);

        pinsToString(pinList, builder, result, pinList.size());

        return String.format(" %-5s" + PIPE, builder.toString());
    }

    private int finalStrikeToString(List<Pin> pinList, StringBuilder builder) {
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

    private String nameToString(String name) {
        return String.format(PIPE + " %4s " + PIPE, name);
    }

    private int index(int size) {
        return size - MINUS_ONE;
    }
}
