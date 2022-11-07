package bowling.views;

import bowling.domain.BowlingGameFrame;
import bowling.dto.BowlingGameDto;
import bowling.dto.BowlingGameFrameDto;

import java.util.List;
import java.util.stream.IntStream;

public class OutputView {

    private static final String SPLITTER = "|";
    private static final String EMPTY = " ";
    private static final String EMPTY_FORMAT = "      |";
    private static final int OFFSET_REFERENCE = 3;
    private static final int OFFSET_OF_SHORT_FORMAT = 2;
    private static final int OFFSET_OF_LONG_FORMAT = 1;
    private static final int TOTAL_LENGTH = 7;
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String GUTTER = "-";

    private OutputView() {
    }

    public static void printBowlingGame(String participant, BowlingGameDto game) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        System.out.printf("|  %s |", participant);
        game.getFrames()
                .forEach(OutputView::printBowlingGameFrame);
        System.out.print("\n\n");
    }

    private static void printBowlingGameFrame(BowlingGameFrameDto frame) {
        System.out.print(getFormatOfHits(frame.getHits()));
    }

    private static String getFormatOfHits(List<Integer> hits) {
        if (hits.isEmpty()) {
            return EMPTY_FORMAT;
        }
        int leftEmptyCount = hits.size() < OFFSET_REFERENCE ? OFFSET_OF_SHORT_FORMAT : OFFSET_OF_LONG_FORMAT;
        StringBuilder stringBuilder = new StringBuilder(EMPTY.repeat(leftEmptyCount) + formatHit(hits.get(0), BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS));
        IntStream.range(1, hits.size())
                .forEach(i -> stringBuilder.append(SPLITTER)
                        .append(formatHit(hits.get(i), hits.get(i - 1))));
        int rightEmptyCount = TOTAL_LENGTH - leftEmptyCount - hits.size() * 2;
        return stringBuilder.append(EMPTY.repeat(rightEmptyCount))
                .append(SPLITTER)
                .toString();
    }

    private static String formatHit(int hit, int previousHit) {
        if (hit == BowlingGameFrame.MIN_NUMBER_OF_BOWLING_PINS) {
            return GUTTER;
        }

        if (hit + previousHit == BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS) {
            return SPARE;
        }

        if (hit == BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS) {
            return STRIKE;
        }

        return String.valueOf(hit);
    }

    public static void printError(Exception e) {
        System.err.println(e.getMessage());
    }

}
