package bowling.view;

import bowling.domain.*;

import java.util.List;
import java.util.stream.IntStream;

public class ResultView {
    private static final String DEFAULT_BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String SCORE_EMPTY = "      |";
    private static final int MAX_SIZE = 10;
    private static final int DEFAULT_SIZE = 1;
    private static final int MINUS_ONE = 1;

    public void bowlingBoard(Frames frames) {
        System.out.println(DEFAULT_BOARD);

        StringBuilder builder = new StringBuilder();
        builder.append(stringToName(frames));
        builder.append(printFrameBoard(frames));

        System.out.println(builder.toString());
    }

    private String printFrameBoard(Frames frames) {
        StringBuilder builder = new StringBuilder();
        IntStream.rangeClosed(DEFAULT_SIZE, MAX_SIZE)
                .forEach(size -> builder.append(frameScore(size, frames)));

        return builder.toString();
    }

    private String frameScore(int size, Frames frames) {
        List<Frame> frameList = frames.frames();
        if (!frameList.isEmpty()
                && size <= frameList.size()) {
            return stringToFrame(frameList.get(index(size)));
        }
        return SCORE_EMPTY;
    }

    private String stringToFrame(Frame frame) {
        Pins pins = frame.pins();
        if (ScoreRule.STRIKE.equals(pins.scoreRule())) {
            return stringToStrike();
        }

        if (ScoreRule.SPARE.equals(pins.scoreRule())) {
            return String.format(" %5s", stringToSpare(pins));
        }

        return String.format(" %5s", stringToEtc(pins));
    }

    private String stringToEtc(Pins pins) {
        StringBuilder builder = new StringBuilder();
        return builder.toString();
    }

    private String stringToSpare(Pins pins) {
        List<Pin> pinList = pins.pins();
        StringBuilder builder = new StringBuilder();
        IntStream.rangeClosed(DEFAULT_SIZE, pinList.size())
                .forEach(index -> {
                    if (builder.length() != 0) {
                        builder.append("|");
                    }
                    String result = (index == pinList.size()) ?
                            ScoreRule.SPARE.symbol : String.valueOf(pinList.get(index).pin());

                    builder.append(result);
                });

        builder.append(" |");
        return builder.toString();
    }

    private String stringToStrike() {
        return String.format("  %s   |", ScoreRule.STRIKE.symbol);
    }

    private String stringToName(Frames frames) {
        return String.format("| %4s |", frames.name());
    }

    private int index(int size) {
        return size - MINUS_ONE;
    }
}
