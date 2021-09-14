package bowling.view;

import bowling.*;

import java.util.List;
import java.util.stream.IntStream;

import static bowling.CommonConstans.*;

public class ResultView {

    public static void bowlingBoard(Players players, Frames frames) { //, Frames frames) {
        System.out.println(DEFAULT_BOARD);

        players.forEach(player -> {
            System.out.print(nameToString(player.getName()));
//            System.out.println(frameBoardString(player.frames()));
//            System.out.println(scoreBoardString(player.frames()));
            System.out.println(frameBoardString(frames));
            System.out.println(scoreBoardString(frames));
        });

        System.out.println();
    }

    private static String scoreBoardString(Frames frames) {
        StringBuilder builder = new StringBuilder();
        builder.append(SCORE_FIRST);

        IntStream.rangeClosed(DEFAULT_SIZE, FRAME_MAX_SIZE)
                .forEach(size -> builder.append(frameScore(size, frames)));

        return builder.toString();
    }

    private static String frameScore(int size, Frames frames) {
        List<FrameScore> scores = frames.scores();
        if (size <= scores.size()
                && !isEndFrameScore(size, scores)) {
            return scoreToString(size, scores);
        }
        return FRAME_EMPTY;
    }

    private static String scoreToString(int size, List<FrameScore> scores) {
        int sum = scores.stream()
                .limit(size)
                .mapToInt(FrameScore::aggregateScore)
                .sum();

        return String.format(" %3d  " + PIPE, sum);
    }

    private static boolean isEndFrameScore(int size, List<FrameScore> scores) {
        return scores.stream()
                .limit(size)
                .anyMatch(score -> score.isAggregateEnd());
    }

    private static String frameBoardString(Frames frames) {
        StringBuilder builder = new StringBuilder();

        IntStream.rangeClosed(DEFAULT_SIZE, FRAME_MAX_SIZE)
                .forEach(size -> builder.append(framePins(size, frames)));

        return builder.toString();
    }

    private static String framePins(int size, Frames frames) {
        List<Frame> frameList = frames.frames();
        if (!frameList.isEmpty()
                && size <= frameList.size()) {
            return frameToString(frameList.get(index(size)));
        }
        return FRAME_EMPTY;
    }

    private static String frameToString(Frame frame) {
        Pins pins = frame.pins();
        if (pins.getClass() == FinalPins.class) {
            return finalToString(pins);
        }
        return normalToString(pins);
    }

    private static String normalToString(Pins pins) {
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

    private static void pinsToString(List<Pin> pinList, StringBuilder builder, int startIndex, int maxLength) {
        for (int i = startIndex; i < maxLength; i++) {
            addBuilderPipe(builder);
            Pin pin = pinList.get(i);
            String symbol = pin.scoreRule(IS_NOT_FIRST).symbol;
            symbol = symbolToString(pin, symbol);
            builder.append(symbol);
        }
    }

    private static String symbolToString(Pin pin, String symbol) {
        if (symbol.isEmpty()) {
            symbol = String.valueOf(pin.pin());
        }
        return symbol;
    }

    private static void addBuilderPipe(StringBuilder builder) {
        if (builder.length() > ZERO) {
            builder.append(PIPE);
        }
    }

    public static String finalToString(Pins pins) {
        List<Pin> pinList = pins.pins();
        StringBuilder builder = new StringBuilder();

        int result = finalStrikeToString(pinList, builder);

        pinsToString(pinList, builder, result, pinList.size());

        return String.format(" %-5s" + PIPE, builder.toString());
    }

    private static int finalStrikeToString(List<Pin> pinList, StringBuilder builder) {
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

    private static String nameToString(String name) {
        return String.format(PIPE + " %4s " + PIPE, name);
    }

    private static int index(int size) {
        return size - MINUS_ONE;
    }
}
