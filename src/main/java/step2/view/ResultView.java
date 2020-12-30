package step2.view;

import step2.domain.Frame;
import step2.domain.Player;

import java.util.stream.IntStream;

public class ResultView {

    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int FRAME_LENGTH = 11;
    private static final String BAR_DELIMITER = "|";
    private static final String NAME = "NAME";
    private static final String EMPTY_SPACE = "      ";
    private static final String HALF_EMPTY_SPACE = "   ";
    private static final String ONE_EMPTY_SPACE = " ";
    private static final String TWO_EMPTY_SPACE = "  ";
    private static StringBuilder SCORES = new StringBuilder();

    private final Player player;

    public ResultView(Player player) {
        this.player = player;
    }

    public void printEmptyRecords() {
        printSignature();
        printEmptyScore(player);
        System.out.println();
    }

    private void printEmptyScore(Player player) {
        printUsername(player);

        IntStream.range(ONE, FRAME_LENGTH)
                .forEach(this::printEmptyScore);
    }

    private void printUsername(Player player) {
        System.out.println();
        System.out.print(BAR_DELIMITER);
        System.out.print(String.format("%5s ", player));
        System.out.print(BAR_DELIMITER);
    }

    private void printSignature() {
        System.out.print(BAR_DELIMITER);
        System.out.print(String.format("%5s ",NAME));
        System.out.print(BAR_DELIMITER);
        IntStream.range(ONE, FRAME_LENGTH)
                .forEach(this::printFrameNumbers);
    }

    private void printFrameNumbers(int number) {
        System.out.print(String.format("  %02d  ", number));
        System.out.print(BAR_DELIMITER);
    }

    private void printEmptyScore(int number) {
        System.out.print(EMPTY_SPACE);
        System.out.print(BAR_DELIMITER);
    }

    public void printScoreBoard(Frame frame, int round) {
        printSignature();
        printUsername(player);

        System.out.print(SCORES);

        StringBuilder result = printScore(frame);

        if (frame.isFinish()) {
            SCORES.append(result);
        }

        IntStream.range(round+ONE, FRAME_LENGTH)
                .forEach(this::printEmptyScore);
        System.out.println();
    }

    private StringBuilder printScore(Frame frame) {
        StringBuilder result = new StringBuilder();
        System.out.print(TWO_EMPTY_SPACE + frame.getState());
        result.append(TWO_EMPTY_SPACE + frame.getState());

        if (frame.getSize() == ONE) {
            System.out.print(HALF_EMPTY_SPACE);
            result.append(HALF_EMPTY_SPACE);
        }

        if (frame.getSize() == TWO) {
            System.out.print(ONE_EMPTY_SPACE);
            result.append(ONE_EMPTY_SPACE);
        }

        System.out.print(BAR_DELIMITER);
        result.append(BAR_DELIMITER);

        return result;
    }
}
