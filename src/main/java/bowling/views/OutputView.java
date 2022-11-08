package bowling.views;

import bowling.domain.BowlingGameHitResult;
import bowling.dto.BowlingGameDto;
import bowling.dto.BowlingGameFrameDto;
import bowling.dto.PlayerDto;

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

    public static void printBowlingGame(PlayerDto player, BowlingGameDto game) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        System.out.printf("|  %s |", player.getName());
        game.getFrames()
                .forEach(OutputView::printBowlingGameFrame);
        System.out.print("\n\n");
    }

    private static void printBowlingGameFrame(BowlingGameFrameDto frame) {
        System.out.print(getFormatOfHits(frame.getHits(), frame.getResults()));
    }

    private static String getFormatOfHits(List<Integer> hits, List<BowlingGameHitResult> results) {
        if (hits.isEmpty()) {
            return EMPTY_FORMAT;
        }
        int leftEmptyCount = hits.size() < OFFSET_REFERENCE ? OFFSET_OF_SHORT_FORMAT : OFFSET_OF_LONG_FORMAT;
        StringBuilder stringBuilder = new StringBuilder(EMPTY.repeat(leftEmptyCount) + formatHit(hits.get(0), results.get(0)));
        IntStream.range(1, hits.size())
                .forEach(i -> stringBuilder.append(SPLITTER)
                        .append(formatHit(hits.get(i), results.get(i))));
        int rightEmptyCount = TOTAL_LENGTH - leftEmptyCount - hits.size() * 2;
        return stringBuilder.append(EMPTY.repeat(rightEmptyCount))
                .append(SPLITTER)
                .toString();
    }

    private static String formatHit(int hit, BowlingGameHitResult result) {
        if (result == BowlingGameHitResult.GUTTER) {
            return GUTTER;
        }

        if (result == BowlingGameHitResult.SPARE) {
            return SPARE;
        }

        if (result == BowlingGameHitResult.STRIKE) {
            return STRIKE;
        }

        return String.valueOf(hit);
    }

    public static void printError(Exception e) {
        System.err.println(e.getMessage());
    }

}
