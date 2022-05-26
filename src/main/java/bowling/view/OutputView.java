package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.frame.Frame;

import java.util.List;
import java.util.stream.IntStream;

public class OutputView {
    private static final int START_FRAME_NUMBER = 1;
    private static final int MAX_FRAME_NUMBER = 10;
    private static final String FRAME_HEADER = "| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |";
    private static final String PLAYER_FRAME = "|  %-3s |";
    private static final String SYMBOL_FRAME = "  %-5s |";
    private static final String BLANK = " ";

    private OutputView() {
    }

    public static void printBowlingGameResult(BowlingGame bowlingGame) {
        printFrameHeader();
        printPlayerAndScoreSymbols(bowlingGame);
        System.out.println();
    }

    private static void printFrameHeader() {
        System.out.println(FRAME_HEADER);
    }

    private static void printPlayerAndScoreSymbols(BowlingGame bowlingGame) {
        printPlayerName(bowlingGame.playerName());
        printScoreSymbols(bowlingGame.frames());
    }

    private static void printPlayerName(String playerName) {
        System.out.printf(String.format(PLAYER_FRAME, playerName));
    }

    private static void printScoreSymbols(List<Frame> frames) {
        frames.stream().forEach(frame -> System.out.printf(String.format(SYMBOL_FRAME, frame.symbol())));
        printBlankFrame(MAX_FRAME_NUMBER - frames.size());
    }

    private static void printBlankFrame(int leftFrameNumber) {
        IntStream.rangeClosed(START_FRAME_NUMBER, leftFrameNumber)
                .forEach(i -> System.out.printf(String.format(SYMBOL_FRAME, BLANK)));
    }
}