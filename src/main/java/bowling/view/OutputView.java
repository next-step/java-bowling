package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.frame.Frame;

import java.util.List;
import java.util.stream.IntStream;

public class OutputView {
    private static final int START_FRAME_NUMBER = 1;
    private static final int MAX_FRAME_NUMBER = 10;
    private static final String FRAME_HEADER = "|  NAME  |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |";
    private static final String PLAYER_FRAME = "|   %-5s|";
    private static final String SYMBOL_FRAME = "  %-5s |";
    private static final String SCORE_FRAME = "  %-5s |";
    private static final String BLANK_FRAME = "|        |";
    private static final String BLANK = " ";

    private OutputView() {
    }

    public static void printBowlingGameResult(BowlingGame bowlingGame) {
        printFrameHeader();
        printPlayerAndScoreSymbols(bowlingGame);
        printScore(bowlingGame);
        System.out.println();
    }

    private static void printScore(BowlingGame bowlingGame) {
        System.out.printf(BLANK_FRAME);
        List<Integer> accumulateScores = bowlingGame.accumulateScores();
        bowlingGame.accumulateScores().forEach(score -> System.out.printf(SCORE_FRAME, score));
        printBlankFrame(MAX_FRAME_NUMBER - accumulateScores.size());

    }

    private static void printFrameHeader() {
        System.out.println(FRAME_HEADER);
    }

    private static void printPlayerAndScoreSymbols(BowlingGame bowlingGame) {
        printPlayerName(bowlingGame.playerName());
        printScoreSymbols(bowlingGame.frames());
        System.out.println();
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